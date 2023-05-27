package kr.ac.kumoh.webkit.ecolocationback.scheduler;

import kr.ac.kumoh.webkit.ecolocationback.service.DatabaseInitializerService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class DataBaseScheduler {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DatabaseInitializerService initializerService;
    private final JobLauncher jobLauncher;

    public DataBaseScheduler(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                             DatabaseInitializerService initializerService, JobLauncher jobLauncher) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.initializerService = initializerService;
        this.jobLauncher = jobLauncher;
    }


    // 지정 시간마다 실행
    // 테스트 용으로 cron = "0 0/10 * * * *"으로 지정하면 3:10, 3:20, ... 10분 텀으로 실행
    // cron = "0 0 1 * * ?" / 매일 새벽 1시에 실행
    @Scheduled(cron = "0 0 1 * * ?")
    public void initializeDatabase() throws Exception {
        Job job = jobBuilderFactory.get("synchronizeRecentFileJob")
                .incrementer(new RunIdIncrementer())
                .flow(synchronizeRecentFileStep())
                .end()
                .build();

        // Job 실행
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        System.out.println("JobExecution status: " + jobExecution.getStatus());
    }

    @Bean
    public Step synchronizeRecentFileStep() {
        return stepBuilderFactory.get("synchronizeRecentFileStep")
                .tasklet((stepContribution, chunkContext) -> {
                    initializerService.initializeDatabase();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}