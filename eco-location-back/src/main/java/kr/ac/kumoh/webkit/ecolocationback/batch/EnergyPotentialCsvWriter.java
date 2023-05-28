package kr.ac.kumoh.webkit.ecolocationback.batch;

import kr.ac.kumoh.webkit.ecolocationback.dto.EnergyPotentialDto;
import kr.ac.kumoh.webkit.ecolocationback.entity.EnergyPotential;
import kr.ac.kumoh.webkit.ecolocationback.entity.document.EnergyPotentialDocument;
import kr.ac.kumoh.webkit.ecolocationback.repository.EnergyPotentialRepository;
import kr.ac.kumoh.webkit.ecolocationback.repository.EnergyPotentialSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class EnergyPotentialCsvWriter implements ItemWriter<EnergyPotentialDto>{

    private final EnergyPotentialRepository energyPotentialRepository;
    private final EnergyPotentialSearchRepository energyPotentialSearchRepository;

    // 재생 에너지 잠재량 CSV 데이터 DB 삽입

    @Override
    public void write(List<? extends EnergyPotentialDto> list) throws Exception {
        List<EnergyPotential> potentialsList = new ArrayList<>();

        list.forEach(getTempPotential->{
            EnergyPotential potential = EnergyPotential.builder()
                    .powerType(getTempPotential.getPowerType())
                    .areaName(getTempPotential.getAreaName())
                    .createTime_s(getTempPotential.getCreateTime_s())
                    .forecastTime_s(getTempPotential.getForecastTime_s())
                    .leadTime(getTempPotential.getLeadTime())
                    .forecastEnergyPotential(getTempPotential.getForecastEnergyPotential())
                    .forecastCapacity(getTempPotential.getForecastCapacity())
                    .build();
            potentialsList.add(potential);
        });

        energyPotentialRepository.saveAll(potentialsList);

        List<EnergyPotentialDocument> energyPotentialDocumentList = potentialsList.stream().map(EnergyPotentialDocument::fromEntity).collect(Collectors.toList());
        energyPotentialSearchRepository.saveAll(energyPotentialDocumentList);
    }

}
