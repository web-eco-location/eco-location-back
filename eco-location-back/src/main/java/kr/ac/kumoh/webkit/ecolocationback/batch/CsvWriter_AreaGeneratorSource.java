package kr.ac.kumoh.webkit.ecolocationback.batch;

import kr.ac.kumoh.webkit.ecolocationback.dto.AreaGeneratorSourceDto;
import kr.ac.kumoh.webkit.ecolocationback.entity.AreaGeneratorSource;
import kr.ac.kumoh.webkit.ecolocationback.repository.AreaGeneratorSourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CsvWriter_AreaGeneratorSource implements ItemWriter<AreaGeneratorSourceDto>{

    private final AreaGeneratorSourceRepository AreaGeneratorSourceRepository;

    // 월간 지역별 발전원별 발전설비 CSV 데이터 DB 삽입

    @Override
    public void write(List<? extends AreaGeneratorSourceDto> list) throws Exception {
        List<AreaGeneratorSource> AreaGeneratorSourceList = new ArrayList<>();

        list.forEach(getTempAreaGeneratorSource->{
        	AreaGeneratorSource areaGeneratorSource = AreaGeneratorSource.builder()
        			.time(getTempAreaGeneratorSource.getTime())
        			.area(getTempAreaGeneratorSource.getArea())
        			.srcNucl(getTempAreaGeneratorSource.getSrcNucl())
        			.srcBcoal(getTempAreaGeneratorSource.getSrcBcoal())
        			.srcHcoal(getTempAreaGeneratorSource.getSrcHcoal())
        			.srcOil(getTempAreaGeneratorSource.getSrcOil())
        			.srcLng(getTempAreaGeneratorSource.getSrcLng())
        			.srcPump(getTempAreaGeneratorSource.getSrcPump())
        			.srcFuelcell(getTempAreaGeneratorSource.getSrcFuelcell())
        			.srcCoalgas(getTempAreaGeneratorSource.getSrcCoalgas())
        			.srcSolar(getTempAreaGeneratorSource.getSrcSolar())
        			.srcWind(getTempAreaGeneratorSource.getSrcWind())
        			.srcWater(getTempAreaGeneratorSource.getSrcWater())
        			.srcSea(getTempAreaGeneratorSource.getSrcSea())
        			.srcBio(getTempAreaGeneratorSource.getSrcBio())
        			.srcWaste(getTempAreaGeneratorSource.getSrcWaste())
        			.srcRecycleSum(getTempAreaGeneratorSource.getSrcRecycleSum())
        			.srcOther(getTempAreaGeneratorSource.getSrcOther())
        			.srcAll(getTempAreaGeneratorSource.getSrcAll())
        			.recyclePercent(getTempAreaGeneratorSource.getRecyclePercent())
        			.build();
            AreaGeneratorSourceList.add(areaGeneratorSource);
        });

        AreaGeneratorSourceRepository.saveAll(new ArrayList<AreaGeneratorSource>(AreaGeneratorSourceList));
    }

}
