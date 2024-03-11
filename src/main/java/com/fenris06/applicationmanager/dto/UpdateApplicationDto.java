package com.fenris06.applicationmanager.dto;

import com.fenris06.applicationmanager.model.Status;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class UpdateApplicationDto {
    private Long applicationId;
    private Status status;

}
