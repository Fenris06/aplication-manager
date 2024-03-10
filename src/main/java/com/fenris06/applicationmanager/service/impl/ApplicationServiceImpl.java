package com.fenris06.applicationmanager.service.impl;

import com.fenris06.applicationmanager.dto.RequestApplicationDto;
import com.fenris06.applicationmanager.dto.ResponseApplicationDto;
import com.fenris06.applicationmanager.dto.UpdateApplicationDto;
import com.fenris06.applicationmanager.dto.UpdateListApplicationDto;
import com.fenris06.applicationmanager.exception.ArgumentException;
import com.fenris06.applicationmanager.exception.NotFoundException;
import com.fenris06.applicationmanager.mapper.ApplicationMapper;
import com.fenris06.applicationmanager.model.*;
import com.fenris06.applicationmanager.repository.ApplicationRepository;
import com.fenris06.applicationmanager.repository.UserRepository;
import com.fenris06.applicationmanager.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Validated
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseApplicationDto createApplication(Long userId, RequestApplicationDto body) {
        User user = checkUser(userId);
        Application application = ApplicationMapper.fromDto(body);
        application.setUser(user);
        return ApplicationMapper.toDto(applicationRepository.save(application));
    }

    @Override
    public ResponseApplicationDto updateApplication(Long userId, Long applicationId, RequestApplicationDto body) {
        checkUser(userId);
        Application application = checkApplication(applicationId);
        checkOwner(application, userId);
        checkApplicationUserStatus(application);
        updateFields(application, body); // TODO валидация полей
        return ApplicationMapper.toDto(applicationRepository.save(application));
    }

    @Override
    public List<ResponseApplicationDto> getUserApplications(Long userId, Integer from, Integer size, String sort) {
        PageRequest pageRequest = PageRequest.of(from, size);
        return switch (sort) {
            case "ASC" -> applicationRepository.findApplicationsByUserAsc(userId, pageRequest).stream()
                    .map(ApplicationMapper::toDto)
                    .collect(Collectors.toList());
            case "DESC" -> applicationRepository.findApplicationsByUserDESC(userId, pageRequest).stream()
                    .map(ApplicationMapper::toDto)
                    .collect(Collectors.toList());
            default -> throw new ArgumentException(String.format("Type of sort = %s unsupported", sort));
        };
    }


    @Override
    @Transactional(readOnly = true)
    public List<ResponseApplicationDto> getAllSentApplication(String userName, Integer from, Integer size, String sort) {
        PageRequest pageRequest = PageRequest.of(from, size);
        if (!userName.isEmpty()) {
            return getApplicationByUserName(userName, List.of(Status.SENT), pageRequest, sort);
        } else {
            return getApplicationWithoutUser(List.of(Status.SENT), pageRequest, sort);
        }
    }

    @Override
    public ResponseApplicationDto getApplicationById(Long applicationId) {
        Application application = checkApplication(applicationId);
        checkApplicationOperatorStatus(application.getStatus());
        return ApplicationMapper.toDto(application);
    }


    @Override
    public List<ResponseApplicationDto> updateApplicationStatus(UpdateListApplicationDto applicationDto) {
        checkUpdateStatus(applicationDto);
        Map<Long, Application> applicationMap = findUpdateApplications(applicationDto);
        List<Application> updateStatus = updateApplicationStatusByOperator(applicationMap, applicationDto);
        return applicationRepository.saveAll(updateStatus).stream()
                .map(ApplicationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResponseApplicationDto> getAdminApplications(Set<Status> applicationStatuses, String userName, Integer from, Integer size, String sort) {
        PageRequest pageRequest = PageRequest.of(from, size); // TODO поправить пагинацию
        List<Status> statusList = checkAdminStatusList(applicationStatuses); //TODO подумать над проверкой листа со статусами для админа
        if (!userName.isEmpty()) {
            return getApplicationByUserName(userName, statusList, pageRequest, sort);
        } else {
            return getApplicationWithoutUser(statusList, pageRequest, sort);
        }
    }

    private List<Status> checkAdminStatusList(Set<Status> applicationStatuses) {
        if (applicationStatuses.contains(Status.DRAFT)) {
            throw new ArgumentException("You can use status DRAFT. You can use status, ACCEPTED, REJECTED, SENT");
        }
        return applicationStatuses.stream()
                .toList();
    }

    private List<Application> updateApplicationStatusByOperator(Map<Long, Application> applicationMap, UpdateListApplicationDto applicationDto) {
        applicationDto.getUpdateApplicationDtoList().forEach(a -> {
            if (applicationMap.containsKey(a.getApplicationId())) {
                Application application = applicationMap.get(a.getApplicationId());
                application.setStatus(a.getStatus());
                applicationMap.put(a.getApplicationId(), application);
            }
        });
        return applicationMap.values().stream()
                .toList();
    }

    private Map<Long, Application> findUpdateApplications(UpdateListApplicationDto applicationDto) {
        List<Long> ids = applicationDto.getUpdateApplicationDtoList().stream()
                .map(UpdateApplicationDto::getApplicationId)
                .toList();
        return applicationRepository.findByIdInAndStatus(ids, Status.SENT).stream()
                .collect(Collectors.toMap(Application::getId, application -> application));
    }

    private List<ResponseApplicationDto> getApplicationWithoutUser(List<Status> statusList, PageRequest pageRequest, String sort) {
        return switch (sort) {
            case "ASC" -> applicationRepository.findApplicationsByStatusAsc(statusList, pageRequest).stream()
                    .map(ApplicationMapper::toDto)
                    .collect(Collectors.toList());
            case "DESC" -> applicationRepository.findApplicationsByStatusDesc(statusList, pageRequest).stream()
                    .map(ApplicationMapper::toDto)
                    .collect(Collectors.toList());
            default -> throw new ArgumentException(String.format("Type of sort = %s unsupported", sort));
        };
    }

    private List<ResponseApplicationDto> getApplicationByUserName(String userName, List<Status> statusList, PageRequest pageRequest, String sort) {
        return switch (sort) {
            case "ASC" ->
                    applicationRepository.findApplicationsByUserNameAndStatusAsc(userName, statusList, pageRequest).stream()
                            .map(ApplicationMapper::toDto).collect(Collectors.toList());
            case "DESC" ->
                    applicationRepository.findApplicationsByUserNameAndStatusDesc(userName, statusList, pageRequest).stream().map(ApplicationMapper::toDto)
                            .collect(Collectors.toList());
            default -> throw new ArgumentException(String.format("Type of sort = %s unsupported", sort));
        };
    }

    private void updateFields(Application application, RequestApplicationDto body) {
        Optional.ofNullable(body.getName()).ifPresent(application::setName);
        Optional.ofNullable(body.getDescription()).ifPresent(application::setDescription);
        Optional.ofNullable(body.getPhoneNumber()).ifPresent(application::setPhoneNumber);
        Optional.ofNullable(body.getStatus()).ifPresent(application::setStatus);
    }


    private void checkApplicationUserStatus(Application application) {
        if (!application.getStatus().equals(Status.DRAFT)) {
            throw new ArgumentException(String.format(
                    "You can change application only if status of application DRAFT!" +
                            " Status of this application %s", application.getStatus())
            );
        }
    }

    private void checkOwner(Application application, Long userId) {
        if (!Objects.equals(application.getUser().getId(), userId)) {
            throw new ArgumentException("You are not owner of this application! Only application owner can change application");
        }
    }

    private User checkUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User id = %d not found", userId)));
    }

    private Application checkApplication(Long applicationId) {
        return applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException(String.format("Application id = %s not found", applicationId)));
    }

    private void checkUpdateStatus(UpdateListApplicationDto applicationDto) {
        applicationDto.getUpdateApplicationDtoList().forEach(a -> {
            if (a.getApplicationId() == null || a.getApplicationId() < 1) {
                throw new ArgumentException("Filed id must be min 1");
            }
            if (a.getStatus() == null || Status.SENT.equals(a.getStatus()) || Status.DRAFT.equals(a.getStatus())) {
                throw new ArgumentException("Filed id status be  ACCEPTED or REJECTED");
            }
        });
    }

    private void checkApplicationOperatorStatus(Status status) {
        if (!Status.SENT.equals(status)) {
            throw new ArgumentException("Filed id status be  must by SENT");
        }
    }

}
