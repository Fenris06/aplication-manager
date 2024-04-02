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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/users")
    @Validated(Create.class)
    public ResponseApplicationDto createApplication(@AuthenticationPrincipal UserDetails userDetails,
                                                    @RequestBody @Valid RequestApplicationDto body) {
        log.debug("POST /users/username {}, body {}", userDetails.getUsername(), body);
        return applicationService.createApplication(userDetails.getUsername(), body);
    }

    @PatchMapping("/users/update/{applicationId}")
    @Validated(Update.class)
    public ResponseApplicationDto updateApplication(@AuthenticationPrincipal UserDetails userDetails,
                                                    @PathVariable("applicationId") @Min(1) Long applicationId,
                                                    @RequestBody @Valid RequestApplicationDto body) {
        log.debug("PATCH /users/username {}, body {}", userDetails.getUsername(), body);
        return applicationService.updateApplication(userDetails.getUsername(), applicationId, body);
    }

    @GetMapping("/users")
    public List<ResponseApplicationDto> getUserApplications(@AuthenticationPrincipal UserDetails userDetails,
                                                            @RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) Integer from,
                                                            @RequestParam(name = "size", required = false, defaultValue = "5") @Min(1) @Max(5) Integer size,
                                                            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
        log.debug("GET /users username {}, from {}, size {}, sort {}", userDetails.getUsername(), from, size, sort);
        return applicationService.getUserApplications(userDetails.getUsername(), from, size, sort);
    }

    @GetMapping("/operator")
    public List<ResponseApplicationDto> getAllSentApplication(@RequestParam(name = "userName", required = false, defaultValue = "") String userName,
                                                              @RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) Integer from,
                                                              @RequestParam(name = "size", required = false, defaultValue = "5") @Min(1) @Max(5) Integer size,
                                                              @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
        log.debug("GET /operator userName {}, from {}, size {}, sort {}", userName, from, size, sort);
        return applicationService.getAllSentApplication(userName, from, size, sort);
    }

    @GetMapping("/operator/{applicationId}")
    public ResponseApplicationDto getApplicationById(@PathVariable("applicationId") @Min(1) Long applicationId) {
        log.debug("GET /operator/applicationId applicationId {}", applicationId);
        return applicationService.getApplicationById(applicationId);
    }

    @PatchMapping("/operator/update")
    public List<ResponseApplicationDto> updateApplicationStatus(@RequestBody @Valid UpdateListApplicationDto applicationDto) {
        log.debug("PATCH /operator/update body {}", applicationDto);
        return applicationService.updateApplicationStatus(applicationDto);
    }

    @GetMapping("/admin")
    public List<ResponseApplicationDto> getAdminApplications(@RequestParam(name = "status", required = false, defaultValue = "SENT") @Size(min = 1, max = 3) Set<Status> statusList,
                                                             @RequestParam(name = "userName", required = false, defaultValue = "") String userName,
                                                             @RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) Integer from,
                                                             @RequestParam(name = "size", required = false, defaultValue = "5") @Min(1) @Max(5) Integer size,
                                                             @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
        log.debug("GET /admin status {}, userName {}, from {}, size {}, sort {}", statusList, userName, from, size, sort);
        return applicationService.getAdminApplications(statusList, userName, from, size, sort);
    }
}
