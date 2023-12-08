package com.example.service_monitoring.adopter.out;

import static com.example.service_monitoring.infrastructure.Constants.SERVICE_INFO_COLLECTION_NAME;

import com.example.service_monitoring.applicaition.port.out.ServiceInfoCreatePort;
import com.example.service_monitoring.applicaition.port.out.ServiceInfoReadPort;
import com.example.service_monitoring.domain.ServiceInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceInfoPersistenceAdapter implements ServiceInfoCreatePort, ServiceInfoReadPort {

    private final MongoTemplate mongoTemplate;

    @Override
    public void save(ServiceInfo serviceInfo) {
        mongoTemplate.save(serviceInfo, SERVICE_INFO_COLLECTION_NAME);
    }

    @Override
    public List<ServiceInfo> findAll() {
        return mongoTemplate.findAll(ServiceInfo.class, SERVICE_INFO_COLLECTION_NAME);
    }
}
