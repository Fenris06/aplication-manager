package com.fenris06.applicationmanager.controller;

import com.fenris06.applicationmanager.dto.RequestApplicationDto;
import com.fenris06.applicationmanager.dto.ResponseApplicationDto;

import com.fenris06.applicationmanager.service.ApplicationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
@Validated
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/{userId}")
    public ResponseApplicationDto createApplication(@PathVariable("userId") @Min(1) Long userId,
                                                    @RequestBody @Valid RequestApplicationDto body) {
        return applicationService.createApplication(userId, body);//TODO узнать про отправление заявок
    }

    @PatchMapping("/{userId}/update/{applicationId}")
    public ResponseApplicationDto updateApplication(@PathVariable("userId") @Min(1) Long userId,
                                                    @PathVariable("applicationId") @Min(1) Long applicationId,
                                                    @RequestBody @Valid RequestApplicationDto body) {
        return applicationService.updateApplication(userId, applicationId, body);
    }

    @GetMapping("/{userId}")
    public List<ResponseApplicationDto> getUserApplications(@PathVariable("userId") @Min(1) Long userId,
                                                            @RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) Integer from,
                                                            @RequestParam(name = "size", required = false, defaultValue = "5") @Min(1) @Max(5) Integer size,
                                                            @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
        return applicationService.getUserApplications(userId, from, size, sort);
    }
}
