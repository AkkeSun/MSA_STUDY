package com.example.service_monitoring.applicaition.service;

import com.example.service_monitoring.applicaition.port.in.ServerErrorCreateUseCase;
import com.example.service_monitoring.applicaition.port.out.ServiceErrorCreatePort;
import com.example.service_monitoring.domain.ServiceError;
import com.example.service_monitoring.infrastructure.utils.JsonUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ErrorService implements ServerErrorCreateUseCase {

    private final JsonUtil jsonUtil;
    private final ServiceErrorCreatePort serverErrorCreatePort;

    @Override
    public void saveAll(ConsumerRecords<String, String> records) {
        List<ServiceError> errorList = new ArrayList<>(100);
        for (ConsumerRecord<String, String> record : records) {
            errorList.add(jsonUtil.jsonToObject(record.value(), ServiceError.class));
        }
        serverErrorCreatePort.saveAll(errorList);
    }
}
