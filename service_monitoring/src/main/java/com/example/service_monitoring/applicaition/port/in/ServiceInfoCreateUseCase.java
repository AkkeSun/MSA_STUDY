package com.example.service_monitoring.applicaition.port.in;

import com.example.service_monitoring.domain.ServiceInfo;

public interface ServiceInfoCreateUseCase {

    String save(ServiceInfo serviceInfo);
}
