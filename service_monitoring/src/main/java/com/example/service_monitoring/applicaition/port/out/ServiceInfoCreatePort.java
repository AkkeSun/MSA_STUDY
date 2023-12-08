package com.example.service_monitoring.applicaition.port.out;

import com.example.service_monitoring.domain.ServiceInfo;

public interface ServiceInfoCreatePort {

    void save(ServiceInfo serviceInfo);

}
