package com.fenris06.applicationmanager.service;

import com.fenris06.applicationmanager.dto.RequestApplicationDto;
import com.fenris06.applicationmanager.dto.ResponseApplicationDto;

import java.util.List;

public interface ApplicationService {
    ResponseApplicationDto createApplication(Long userId, RequestApplicationDto body);

    ResponseApplicationDto updateApplication(Long userId, Long applicationId, RequestApplicationDto body);

    List<ResponseApplicationDto> getUserApplications(Long userId, Integer from, Integer size, String sort);

    List<ResponseApplicationDto> getAllSentApplication(String userName, Integer from, Integer size, String sort);

}
