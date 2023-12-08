package com.example.service_monitoring.adopter.in;

import com.example.service_monitoring.applicaition.port.in.ServiceInfoReadUseCase;
import com.example.service_monitoring.domain.ServiceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class MonitoringScheduler {

    private final ServiceInfoReadUseCase serviceInfoReadUseCase;

    @Async
    @Scheduled(fixedDelay = 60000, initialDelay = 1000) // 1분에 한 번씩 실행
    public void healthCheck() {
        System.out.println("check");
        for (ServiceInfo serviceInfo : serviceInfoReadUseCase.findAll()) {
            try {
                String response = WebClient.create(serviceInfo.getTestApi())
                    .get()
                    .retrieve()
                    .bodyToMono(String.class).block();
                System.out.println(serviceInfo.getName() + "  " + response);
                if ("N".equals(response)) {
                    // TODO: Slack noti
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                // TODO : Slack noti
            }
        }
    }

    public void saveTraffic() {
        // redis 조회 : traffic::서비스명-랜덤키
        // save
        // redis 삭제
    }

    public void pushServiceError() {

    }
}
