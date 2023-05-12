package kr.ac.kumoh.webkit.ecolocationback.batch;

import kr.ac.kumoh.webkit.ecolocationback.dto.EnergyPotentialDto;
import kr.ac.kumoh.webkit.ecolocationback.dto.GeneratorDto;
import kr.ac.kumoh.webkit.ecolocationback.dto.AreaGeneratorSourceDto;
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

    // 발전소 정보
    private final GeneratorInfoCsvWriter generatorInfoCsvWriter;

    // 재생 에너지 잠재량
    private final EnergyPotentialCsvWriter energyPotentialCsvWriter;

    // 재생에너지 전환율
    private final AreaGeneratorSourceCsvWriter areaGeneratorSourceCsvWriter;
    
    private static final int chunkSize = 1000;

    @Bean
    public Job generatorInfoCsvFileItemReaderJob() {
        return jobBuilderFactory.get("generatorInfoCsvFileItemReaderJob")
                .start(generatorInfoCsvFileItemReaderStep())
                .build();
    }

    @Bean
    public Step generatorInfoCsvFileItemReaderStep() {
        return stepBuilderFactory.get("generatorInfoCsvFileItemReaderStep")
                .<GeneratorDto, GeneratorDto>chunk(chunkSize)
                .reader(csvReader.csvFileItemReader())
                .writer(generatorInfoCsvWriter)
                .build();
    }

    @Bean
    public Job energyPotentialCsvFileItemReaderJob() {
        return jobBuilderFactory.get("energyPotentialCsvFileItemReaderJob")
                .start(energyPotentialCsvFileItemReaderStep())
                .build();
    }

    @Bean
    public Step energyPotentialCsvFileItemReaderStep(){
        return stepBuilderFactory.get("energyPotentialCsvFileItemReaderStep")
                .<EnergyPotentialDto, EnergyPotentialDto>chunk(chunkSize)
                .reader(csvReader.csvFileItemReader_Potential())
                .writer(energyPotentialCsvWriter)
                .build();
    }
    
    @Bean
    public Job areaGeneratorSourceCsvFileItemReaderJob() {
        return jobBuilderFactory.get("areaGeneratorSourceCsvFileItemReaderJob")
                .start(areaGeneratorSourceCsvFileItemReaderStep())
                .build();
    }

    @Bean
    public Step areaGeneratorSourceCsvFileItemReaderStep(){
        return stepBuilderFactory.get("areaGeneratorSourceCsvFileItemReaderStep")
                .<AreaGeneratorSourceDto, AreaGeneratorSourceDto>chunk(chunkSize)
                .reader(csvReader.csvFileItemReader_AreaGeneratorSource())
                .writer(areaGeneratorSourceCsvWriter)
                .build();
    }
}
