package com.example.service_monitoring.applicaition.port.in;

import com.example.service_monitoring.domain.ServiceInfo;
import java.util.List;

public interface ServiceInfoReadUseCase {

    List<ServiceInfo> findAll();
}
