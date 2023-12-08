package com.example.service_monitoring.applicaition.service;

import com.example.service_monitoring.applicaition.port.in.ServiceInfoCreateUseCase;
import com.example.service_monitoring.applicaition.port.in.ServiceInfoReadUseCase;
import com.example.service_monitoring.applicaition.port.out.ServiceInfoCreatePort;
import com.example.service_monitoring.applicaition.port.out.ServiceInfoReadPort;
import com.example.service_monitoring.domain.ServiceInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class InfoService implements ServiceInfoCreateUseCase, ServiceInfoReadUseCase {

    private final ServiceInfoCreatePort serviceInfoCreatePort;
    private final ServiceInfoReadPort serviceInfoReadPort;

    @Override
    public String save(ServiceInfo serviceInfo) {
        try {
            String errMsg = serviceInfo.validation();
            if (StringUtils.hasText(errMsg)) {
                return errMsg;
            }
            serviceInfoCreatePort.save(serviceInfo);
            return "Y";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<ServiceInfo> findAll() {
        return serviceInfoReadPort.findAll();
    }
}
