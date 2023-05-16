package kr.ac.kumoh.webkit.ecolocationback.batch;

import kr.ac.kumoh.webkit.ecolocationback.dto.EnergyPotentialDto;
import kr.ac.kumoh.webkit.ecolocationback.dto.GeneratorDto;
import kr.ac.kumoh.webkit.ecolocationback.dto.AreaGeneratorSourceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@RequiredArgsConstructor
public class CsvReader {
    @Bean
    public ItemReader<? extends GeneratorDto> csvFileItemReader() {
        String[] headers = new String[]{"companyName","generatorName", "generatorNum", "generateAmount", "memberType", "powerSupply", "powerSource", "generationType", "businessType", "wideArea", "detailArea"};
        return getFileItemReaderFromCsvFile("/csv/generators.csv",headers, GeneratorDto.class);
    }

    // 재생 에너지 잠재량 데이터 가져오기
    @Bean
    public ItemReader<? extends EnergyPotentialDto> csvFileItemReader_Potential() {
        String[] headers = new String[]{"powerType","areaName", "createTime", "forecastTime", "leadTime", "forecastEnergyPotential", "forecastCapacity"};
        return getFileItemReaderFromCsvFile("/csv/totalEnergyPotential.csv",headers, EnergyPotentialDto.class);
    }

    // 월간 지역별 발전원별 발전설비 CSV 데이터 가져오기
    @Bean
    public ItemReader<? extends AreaGeneratorSourceDto> csvFileItemReader_AreaGeneratorSource() {
        String[] headers = new String[]{"date", "area", "srcNucl", "srcBcoal", "srcHcoal", "srcOil", "srcLnc", "srcPump", "srcFuelcell", "srcCoalgas", "srcSolar", "srcWind", "srcWater", "srcSea", "srcBio", "srcWaste", "srcRecycleSum", "srcOther", "srcAll", "recyclePercent"};
        return getFileItemReaderFromCsvFile("/csv/AreaGeneratorSource.csv",headers, AreaGeneratorSourceDto.class);
    }

    private static <T> FlatFileItemReader<T> getFileItemReaderFromCsvFile(String path, String[] headers, Class<T> targetType) {
        /* file read */
        FlatFileItemReader<T> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource(path));
        flatFileItemReader.setLinesToSkip(1); // header line skip
        flatFileItemReader.setEncoding("UTF-8"); // encoding

        /* read하는 데이터를 내부적으로 LineMapper을 통해 Mapping */
        DefaultLineMapper<T> defaultLineMapper = new DefaultLineMapper<>();

        /* delimitedLineTokenizer : setNames를 통해 각각의 데이터의 이름 설정 */
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(",");
        delimitedLineTokenizer.setNames(headers);
        delimitedLineTokenizer.setStrict(true);
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

        /* beanWrapperFieldSetMapper : Tokenizer에서 가지고온 데이터들을 VO로 바인드하는 역할 */
        BeanWrapperFieldSetMapper<T> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(targetType);

        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        /* lineMapper 지정 */
        flatFileItemReader.setLineMapper(defaultLineMapper);

        return flatFileItemReader;
    }
}