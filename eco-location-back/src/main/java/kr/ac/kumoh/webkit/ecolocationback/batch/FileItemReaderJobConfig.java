package kr.ac.kumoh.webkit.ecolocationback.batch;

import kr.ac.kumoh.webkit.ecolocationback.dto.EnergyPotentialDto;
import kr.ac.kumoh.webkit.ecolocationback.dto.GeneratorDto;
import kr.ac.kumoh.webkit.ecolocationback.entity.Generator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class FileItemReaderJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CsvReader csvReader;
    private final CsvWriter csvWriter;

    // 재생 에너지 잠재량
    private final JobBuilderFactory jobBuilderFactory_EP;
    private final StepBuilderFactory stepBuilderFactory_EP;
    private final CsvWriter_EP csvWriter_ep;

    private static final int chunkSize = 1000;

    @Bean
    public Job csvFileItemReaderJob() {
        return jobBuilderFactory.get("csvFileItemReaderJob")
                .start(csvFileItemReaderStep())
                .build();
    }

    @Bean
    public Step csvFileItemReaderStep() {
        return stepBuilderFactory.get("csvFileItemReaderStep")
                .<GeneratorDto, GeneratorDto>chunk(chunkSize)
                .reader(csvReader.csvFileItemReader())
                .writer(csvWriter)
                .build();
    }

    @Bean
    public Job csvFileItemReaderJob_EP() {
        return jobBuilderFactory_EP.get("csvFileItemReaderJob_EP")
                .start(csvFileItemReaderStep_EP())
                .build();
    }

    @Bean
    public Step csvFileItemReaderStep_EP(){
        return stepBuilderFactory_EP.get("csvFileItemReaderStep_EP")
                .<EnergyPotentialDto, EnergyPotentialDto>chunk(chunkSize)
                .reader(csvReader.csvFileItemReader_Potential())
                .writer(csvWriter_ep)
                .build();
    }
}
