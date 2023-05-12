package kr.ac.kumoh.webkit.ecolocationback.batch;

import kr.ac.kumoh.webkit.ecolocationback.dto.RecycleRatioDto;
import kr.ac.kumoh.webkit.ecolocationback.entity.RecycleRatio;
import kr.ac.kumoh.webkit.ecolocationback.repository.RecycleRatioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CsvWriter_RR implements ItemWriter<RecycleRatioDto>{

    private final RecycleRatioRepository recycleRatioRepository;

    // 재생 에너지 잠재량 CSV 데이터 DB 삽입

    @Override
    public void write(List<? extends RecycleRatioDto> list) throws Exception {
        List<RecycleRatio> RecycleRatioList = new ArrayList<>();

        list.forEach(getTempRecycleRatio->{
        	RecycleRatio recycleratio = RecycleRatio.builder()
        			.time(getTempRecycleRatio.getTime())
        			.area(getTempRecycleRatio.getArea())
        			.srcNucl(getTempRecycleRatio.getSrcNucl())
        			.srcBcoal(getTempRecycleRatio.getSrcBcoal())
        			.srcHcoal(getTempRecycleRatio.getSrcHcoal())
        			.srcOil(getTempRecycleRatio.getSrcOil())
        			.srcLng(getTempRecycleRatio.getSrcLng())
        			.srcPump(getTempRecycleRatio.getSrcPump())
        			.srcFuelcell(getTempRecycleRatio.getSrcFuelcell())
        			.srcCoalgas(getTempRecycleRatio.getSrcCoalgas())
        			.srcSolar(getTempRecycleRatio.getSrcSolar())
        			.srcWind(getTempRecycleRatio.getSrcWind())
        			.srcWater(getTempRecycleRatio.getSrcWater())
        			.srcSea(getTempRecycleRatio.getSrcSea())
        			.srcBio(getTempRecycleRatio.getSrcBio())
        			.srcWaste(getTempRecycleRatio.getSrcWaste())
        			.srcRecycleSum(getTempRecycleRatio.getSrcRecycleSum())
        			.srcOther(getTempRecycleRatio.getSrcOther())
        			.srcAll(getTempRecycleRatio.getSrcAll())
        			.recyclePercent(getTempRecycleRatio.getRecyclePercent())
        			.build();
            RecycleRatioList.add(recycleratio);
        });

        recycleRatioRepository.saveAll(new ArrayList<RecycleRatio>(RecycleRatioList));
    }

}
