package kr.ac.kumoh.webkit.ecolocationback.batch;

import kr.ac.kumoh.webkit.ecolocationback.dto.AreaGeneratorSourceDto;
import kr.ac.kumoh.webkit.ecolocationback.entity.AreaGeneratorSource;
import kr.ac.kumoh.webkit.ecolocationback.entity.document.AreaGeneratorSourceDocument;
import kr.ac.kumoh.webkit.ecolocationback.repository.AreaGeneratorSourceRepository;
import kr.ac.kumoh.webkit.ecolocationback.repository.AreaGeneratorSourceSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class AreaGeneratorSourceCsvWriter implements ItemWriter<AreaGeneratorSourceDto>{

    private final AreaGeneratorSourceRepository AreaGeneratorSourceRepository;
	private final AreaGeneratorSourceSearchRepository areaGeneratorSourceSearchRepository;

    // 월간 지역별 발전원별 발전설비 CSV 데이터 DB 삽입

    @Override
    public void write(List<? extends AreaGeneratorSourceDto> list) throws Exception {
        List<AreaGeneratorSource> areaGeneratorSourceList = new ArrayList<>();
        
        list.forEach(getTempAreaGeneratorSource->{
        	LocalDate date = LocalDate.parse(getTempAreaGeneratorSource.getDate(), DateTimeFormatter.ofPattern("yy-MM-dd"));
        	
        	AreaGeneratorSource areaGeneratorSource = AreaGeneratorSource.builder()
        			.date(date)
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
            areaGeneratorSourceList.add(areaGeneratorSource);
        });

        AreaGeneratorSourceRepository.saveAll(areaGeneratorSourceList);

		List<AreaGeneratorSourceDocument> areaGeneratorSourceDocumentList = areaGeneratorSourceList.stream().map(AreaGeneratorSourceDocument::fromEntity).collect(Collectors.toList());
		areaGeneratorSourceSearchRepository.saveAll(areaGeneratorSourceDocumentList);
    }

}
