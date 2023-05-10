package kr.ac.kumoh.webkit.ecolocationback.batch;

import kr.ac.kumoh.webkit.ecolocationback.dto.GeneratorDto;
import kr.ac.kumoh.webkit.ecolocationback.entity.Generator;
import kr.ac.kumoh.webkit.ecolocationback.repository.GeneratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CsvWriter implements ItemWriter<GeneratorDto> {

    private final GeneratorRepository generatorRepository;

    @Override
    public void write(List<? extends GeneratorDto> list) throws Exception {
        List<Generator> generatorList = new ArrayList<>();

        // dto -> entity
        list.forEach(getTempGenerator->{
            Generator generator = Generator.builder()
                    .companyName(getTempGenerator.getCompanyName())
                    .generatorName(getTempGenerator.getGeneratorName())
                    .generatorNum(getTempGenerator.getGeneratorNum())
                    .generateAmount(getTempGenerator.getGenerateAmount())
                    .memberType(getTempGenerator.getMemberType())
                    .powerSupply(getTempGenerator.getPowerSupply())
                    .powerSource(getTempGenerator.getPowerSource())
                    .generationType(getTempGenerator.getGenerationType())
                    .businessType(getTempGenerator.getBusinessType())
                    .wideArea(getTempGenerator.getWideArea())
                    .detailArea(getTempGenerator.getDetailArea())
                    .build();
            generatorList.add(generator);
        });

        generatorRepository.saveAll(new ArrayList<Generator>(generatorList));
    }
}