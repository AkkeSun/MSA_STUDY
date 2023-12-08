package com.example.service_monitoring.adopter.out;

import static com.example.service_monitoring.infrastructure.Constants.SERVICE_ERROR_COLLECTION_NAME;
import static com.example.service_monitoring.infrastructure.utils.DateUtil.getNowDate;

import com.example.service_monitoring.applicaition.port.out.ServiceErrorCreatePort;
import com.example.service_monitoring.domain.ServiceError;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceErrorPersistenceAdapter implements ServiceErrorCreatePort {

    private final MongoTemplate mongoTemplate;

    @Override
    public void saveAll(List<ServiceError> serverErrorList) {
        mongoTemplate.insert(serverErrorList, SERVICE_ERROR_COLLECTION_NAME + getNowDate());
    }
}
