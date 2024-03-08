package com.fenris06.applicationmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UserAuthorizationDto {
    @Size(min = 3, max = 50, message = "The name field must be a minimum of 3 and a maximum of 50 characters long")
    @NotBlank(message = "The name field can't be empty or have only space")
    private String username;
    @Size(min = 3, max = 50, message = "The password field must be a minimum of 3 and a maximum of 50 characters long")
    @NotBlank(message = "The password field can't be empty or have only space")
    private String password;

}
