package com.fenris06.applicationmanager.dto;

import com.fenris06.applicationmanager.model.Status;

import com.fenris06.applicationmanager.utils.annotation.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RequestApplicationDto {
    private Long id;
    @NotBlank(message = "Field name can't be empty or have only spase")
    @Size(min = 3, max = 100, message = "Field name must be a minimum of 3 characters and a maximum of 100 characters")
    private String name;
    @NotBlank(message = "Field description can't be empty or have only spase")
    @Size(min = 3, max = 500, message = "Field description must be a minimum of 3 characters and a maximum of 500 characters")
    private String description;
    @NotNull(message = "Field phoneNumber can't be null")
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", message = "Field phone must have phone number characters")
    private String phoneNumber;
    @NotNull
    @UserStatus(message = "Field status can be DRAFT OR SENT")
    private Status status;
}
