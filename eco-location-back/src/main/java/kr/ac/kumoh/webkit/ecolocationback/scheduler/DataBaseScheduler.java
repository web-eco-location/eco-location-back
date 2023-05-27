package kr.ac.kumoh.webkit.ecolocationback.scheduler;

import kr.ac.kumoh.webkit.ecolocationback.service.DatabaseInitializerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataBaseScheduler {
    private final DatabaseInitializerService initializerService;

    public DataBaseScheduler(DatabaseInitializerService initializerService) {
        this.initializerService = initializerService;
    }

    // 지정 시간마다 실행
    // 테스트 용으로 cron = "0 0/10 * * * *"으로 지정하면 3:10, 3:20, ... 10분 텀으로 실행
    // cron = "0 0 1 * * ?" / 매일 새벽 1시에 실행
    @Scheduled(cron = "0 0 1 * * ?")
    public void initializeDatabase() throws Exception {
        initializerService.initializeDatabase();
    }

}