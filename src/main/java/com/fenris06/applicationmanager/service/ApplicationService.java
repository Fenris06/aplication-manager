package com.fenris06.applicationmanager.service;

import com.fenris06.applicationmanager.dto.RequestApplicationDto;
import com.fenris06.applicationmanager.dto.ResponseApplicationDto;
import com.fenris06.applicationmanager.dto.UpdateListApplicationDto;
import com.fenris06.applicationmanager.model.Status;

import java.util.List;
import java.util.Set;

public interface ApplicationService {
    ResponseApplicationDto createApplication(String username, RequestApplicationDto body);

    ResponseApplicationDto updateApplication(String username, Long applicationId, RequestApplicationDto body);

    List<ResponseApplicationDto> getUserApplications(String username, Integer from, Integer size, String sort);

    List<ResponseApplicationDto> getAllSentApplication(String userName, Integer from, Integer size, String sort);

    ResponseApplicationDto getApplicationById(Long applicationId);

    List<ResponseApplicationDto> updateApplicationStatus(UpdateListApplicationDto applicationDto);

    List<ResponseApplicationDto> getAdminApplications(Set<Status> applicationStatuses, String userName, Integer from, Integer size, String sort);
}
