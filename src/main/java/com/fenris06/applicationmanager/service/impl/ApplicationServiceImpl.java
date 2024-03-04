package com.fenris06.applicationmanager.service.impl;

import com.fenris06.applicationmanager.dto.RequestApplicationDto;
import com.fenris06.applicationmanager.dto.ResponseApplicationDto;
import com.fenris06.applicationmanager.exception.ArgumentException;
import com.fenris06.applicationmanager.exception.NotFoundException;
import com.fenris06.applicationmanager.mapper.ApplicationMapper;
import com.fenris06.applicationmanager.model.Application;
import com.fenris06.applicationmanager.model.Status;
import com.fenris06.applicationmanager.model.User;
import com.fenris06.applicationmanager.repository.ApplicationRepository;
import com.fenris06.applicationmanager.repository.UserRepository;
import com.fenris06.applicationmanager.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
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
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException(String.format("Application id = %s not found", applicationId)));
        checkOwner(application, userId);
        checkApplicationStatus(application);
        updateFields(application, body);
        return ApplicationMapper.toDto(applicationRepository.save(application));
    }

    @Override
    public List<ResponseApplicationDto> getUserApplications(Long userId, Integer from, Integer size, String sort) {
        PageRequest pageRequest = PageRequest.of(from / size, size);
        return switch (sort) {
            case "ASC" -> applicationRepository.findByUser_IdOrderByCreateDateAsc(userId, pageRequest).stream()
                    .map(ApplicationMapper::toDto)
                    .collect(Collectors.toList());
            case "DESC" -> applicationRepository.findByUser_IdOrderByCreateDateDesc(userId, pageRequest).stream()
                    .map(ApplicationMapper::toDto)
                    .collect(Collectors.toList());
            default -> throw new ArgumentException(String.format("Type of sort = %s not supported", sort));
        };
    }

    private void updateFields(Application application, RequestApplicationDto body) {
        Optional.ofNullable(body.getName()).ifPresent(application::setName);
        Optional.ofNullable(body.getDescription()).ifPresent(application::setDescription);
        Optional.ofNullable(body.getPhoneNumber()).ifPresent(application::setPhoneNumber);
        Optional.ofNullable(body.getStatus()).ifPresent(application::setStatus);
        //TODO подумать над датой обновления
    }


    private void checkApplicationStatus(Application application) {
        if (application.getStatus().equals(Status.DRAFT)) {
            throw new ArithmeticException(String.format(
                    "You can change application only if status of application DRAFT!" +
                            " Status of this application %s", application.getStatus())
            );
        }
    }

    private void checkOwner(Application application, Long userId) {//TODO после добавления секюрити возможно убрать проверку
        if (!Objects.equals(application.getUser().getId(), userId)) {
            throw new ArithmeticException("You are not owner of this application! Only application owner can change application");
        }
    }

    private User checkUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User id = %d not found", userId)));
    }


}
