package com.example.service_monitoring.applicaition.port.out;

import com.example.service_monitoring.domain.ServiceError;
import java.util.List;

public interface ServiceErrorCreatePort {

    void saveAll(List<ServiceError> serverErrorList);
}
