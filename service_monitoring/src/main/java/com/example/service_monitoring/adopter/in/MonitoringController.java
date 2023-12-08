package com.example.service_monitoring.adopter.in;

import com.example.service_monitoring.applicaition.port.in.ServiceInfoCreateUseCase;
import com.example.service_monitoring.domain.ServiceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MonitoringController {

    private final ServiceInfoCreateUseCase serviceInfoCreateUseCase;

    @PostMapping("/serviceInfo")
    public ResponseEntity<String> saveServiceInfo(@RequestBody ServiceInfo serviceInfo) {
        return ResponseEntity.ok(serviceInfoCreateUseCase.save(serviceInfo));
    }

}
