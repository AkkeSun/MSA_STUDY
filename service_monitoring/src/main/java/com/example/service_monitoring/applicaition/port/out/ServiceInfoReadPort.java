package com.example.service_monitoring.applicaition.port.out;

import com.example.service_monitoring.domain.ServiceInfo;
import java.util.List;

public interface ServiceInfoReadPort {

    List<ServiceInfo> findAll();
}
