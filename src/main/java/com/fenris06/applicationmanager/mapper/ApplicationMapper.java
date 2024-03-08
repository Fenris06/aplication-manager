package com.fenris06.applicationmanager.mapper;

import com.fenris06.applicationmanager.dto.RequestApplicationDto;
import com.fenris06.applicationmanager.dto.ResponseApplicationDto;
import com.fenris06.applicationmanager.model.Application;

import java.time.LocalDateTime;

public class ApplicationMapper {

    public static Application fromDto(RequestApplicationDto requestDto) {
        Application application = new Application();
        application.setName(requestDto.getName());
        application.setDescription(requestDto.getDescription());
        application.setPhoneNumber(requestDto.getPhoneNumber());
        application.setCreateDate(LocalDateTime.now());
        application.setStatus(requestDto.getStatus());
        return application;
    }

    public static ResponseApplicationDto toDto(Application application) {
        ResponseApplicationDto responseApplicationDto = new ResponseApplicationDto();
        responseApplicationDto.setId(application.getId());
        responseApplicationDto.setName(application.getName());
        responseApplicationDto.setDescription(application.getDescription());
        responseApplicationDto.setPhoneNumber(application.getPhoneNumber());
        responseApplicationDto.setCreateDate(application.getCreateDate());
        responseApplicationDto.setStatus(application.getStatus());
        responseApplicationDto.setOwner(application.getUser().getUsername());
        return responseApplicationDto;
    }
}
