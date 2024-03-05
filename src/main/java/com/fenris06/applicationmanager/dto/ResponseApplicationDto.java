package com.fenris06.applicationmanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fenris06.applicationmanager.model.Status;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
public class ResponseApplicationDto {
    private Long id;
    private String name;
    private String description;
    private String phoneNumber;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    private Status status;
    private String owner;
}
