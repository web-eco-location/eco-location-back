package kr.ac.kumoh.webkit.ecolocationback.batch;

import kr.ac.kumoh.webkit.ecolocationback.dto.EnergyPotentialDto;
import kr.ac.kumoh.webkit.ecolocationback.entity.EnergyPotential;
import kr.ac.kumoh.webkit.ecolocationback.repository.EnergyPotentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CsvWriter_EP implements ItemWriter<EnergyPotentialDto>{

    private final EnergyPotentialRepository energyPotentialRepository;

    // 재생 에너지 잠재량 CSV 데이터 DB 삽입

    @Override
    public void write(List<? extends EnergyPotentialDto> list) throws Exception {
        List<EnergyPotential> potentialsList = new ArrayList<>();

        list.forEach(getTempPotential->{
            EnergyPotential potential = EnergyPotential.builder()
                    .PWR_EXC_TP_CD(getTempPotential.getPWR_EXC_TP_CD())
                    .AREA(getTempPotential.getAREA())
                    .CRTN_TM(getTempPotential.getCRTN_TM())
                    .FCST_TM(getTempPotential.getFCST_TM())
                    .LEAD_TM(getTempPotential.getLEAD_TM())
                    .FCST_EP(getTempPotential.getFCST_EP())
                    .FCST_CAPA(getTempPotential.getFCST_CAPA())
                    .build();
            potentialsList.add(potential);
        });

        energyPotentialRepository.saveAll(new ArrayList<EnergyPotential>(potentialsList));
    }

}
