package com.fenris06.applicationmanager.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class UpdateListApplicationDto {
    @NotNull(message = "Field updateApplicationDtoList can't be null")
    @Size(min = 1, max = 5, message = "Field updateApplicationDtoList must be size min 1 and max 5")
    private List<UpdateApplicationDto> updateApplicationDtoList = new ArrayList<>();
}
