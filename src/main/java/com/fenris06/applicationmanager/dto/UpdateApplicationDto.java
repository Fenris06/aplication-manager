package com.fenris06.applicationmanager.dto;

import com.fenris06.applicationmanager.model.Status;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class UpdateApplicationDto {
    @NotNull(message = "Field applicationId can't be null")
    @Min(value = 1, message = "Field applicationId must be minimum 1")
    private Long applicationId;
    @NotNull(message = "Field status can't be null")  //TODO добавить проверку статусов
    private Status status; //TODO посмотреть работают ли аннотации

}
