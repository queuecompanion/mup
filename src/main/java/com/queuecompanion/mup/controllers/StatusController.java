package com.queuecompanion.mup.controllers;

import com.queuecompanion.mup.dto.response.StatusResponse;
import com.queuecompanion.mup.services.StatusService;
import com.queuecompanion.mup.util.HttpConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(HttpConstants.STATUS_PATH)
public class StatusController {
    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    // TODO: authorize access
    @GetMapping
    public ResponseEntity<StatusResponse> getServiceStatus() {
        return ResponseEntity.ok(new StatusResponse(statusService.getUptimeMillis()));
    }
}
