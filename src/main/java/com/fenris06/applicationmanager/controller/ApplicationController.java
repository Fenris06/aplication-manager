package com.fenris06.applicationmanager.controller;

import com.fenris06.applicationmanager.dto.RequestApplicationDto;
import com.fenris06.applicationmanager.dto.ResponseApplicationDto;

import com.fenris06.applicationmanager.dto.UpdateListApplicationDto;
import com.fenris06.applicationmanager.model.Status;
import com.fenris06.applicationmanager.service.ApplicationService;
import com.fenris06.applicationmanager.utils.validationgroup.Create;
import com.fenris06.applicationmanager.utils.validationgroup.Update;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
@Validated
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/users/{userId}")
    @Validated(Create.class)
    public ResponseApplicationDto createApplication(@PathVariable("userId") @Min(1) Long userId,
                                                    @RequestBody @Valid RequestApplicationDto body) {
        return applicationService.createApplication(userId, body);
    }

    @PatchMapping("/users/{userId}/update/{applicationId}")
    @Validated(Update.class)
    public ResponseApplicationDto updateApplication(@PathVariable("userId") @Min(1) Long userId,
                                                    @PathVariable("applicationId") @Min(1) Long applicationId,
                                                    @RequestBody @Valid RequestApplicationDto body) {
        return applicationService.updateApplication(userId, applicationId, body);
    }

    @GetMapping("/users/{userId}")
    public List<ResponseApplicationDto> getUserApplications(@PathVariable("userId") @Min(1) Long userId,
                                                            @RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) Integer from,
                                                            @RequestParam(name = "size", required = false, defaultValue = "5") @Min(1) @Max(5) Integer size,
                                                            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
        return applicationService.getUserApplications(userId, from, size, sort);
    }

    @GetMapping("/operator")
    public List<ResponseApplicationDto> getAllSentApplication(@RequestParam(name = "userName", required = false, defaultValue = "") String userName,
                                                              @RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) Integer from,
                                                              @RequestParam(name = "size", required = false, defaultValue = "5") @Min(1) @Max(5) Integer size,
                                                              @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
        return applicationService.getAllSentApplication(userName, from, size, sort);
    }

    @GetMapping("/operator/{applicationId}")
    public ResponseApplicationDto getApplicationById(@PathVariable("applicationId") @Min(1) Long applicationId) {
        return applicationService.getApplicationById(applicationId);
    }

    @PatchMapping("/operator/update")
    public List<ResponseApplicationDto> updateApplicationStatus(@RequestBody @Valid UpdateListApplicationDto applicationDto) {
        return applicationService.updateApplicationStatus(applicationDto);
    }

    @GetMapping("/admin")
    public List<ResponseApplicationDto> getAdminApplications(@RequestParam(name = "status", required = false, defaultValue = "SENT") @Size(min = 1, max = 3) Set<Status> statusList,
                                                             @RequestParam(name = "userName", required = false, defaultValue = "") String userName,
                                                             @RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) Integer from,
                                                             @RequestParam(name = "size", required = false, defaultValue = "5") @Min(1) @Max(5) Integer size,
                                                             @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
        return applicationService.getAdminApplications(statusList, userName, from, size, sort);
    }
}
