package com.fenris06.applicationmanager.service;

import com.fenris06.applicationmanager.dto.RequestApplicationDto;
import com.fenris06.applicationmanager.dto.ResponseApplicationDto;
import com.fenris06.applicationmanager.dto.UpdateListApplicationDto;
import com.fenris06.applicationmanager.model.AdminApplicationStatus;

import java.util.List;

public interface ApplicationService {
    ResponseApplicationDto createApplication(Long userId, RequestApplicationDto body);

    ResponseApplicationDto updateApplication(Long userId, Long applicationId, RequestApplicationDto body);

    List<ResponseApplicationDto> getUserApplications(Long userId, Integer from, Integer size, String sort);

    List<ResponseApplicationDto> getAllSentApplication(String userName, Integer from, Integer size, String sort);

    ResponseApplicationDto getApplicationById(Long applicationId);

    List<ResponseApplicationDto> updateApplicationStatus(UpdateListApplicationDto applicationDto);

    List<ResponseApplicationDto> getAdminApplications(List<AdminApplicationStatus> applicationStatuses, String userName, Integer from, Integer size, String sort);
}
