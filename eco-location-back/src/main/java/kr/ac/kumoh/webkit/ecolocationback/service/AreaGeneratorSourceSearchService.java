package kr.ac.kumoh.webkit.ecolocationback.service;

import kr.ac.kumoh.webkit.ecolocationback.dto.response.RenewableEnergyRateDto;
import kr.ac.kumoh.webkit.ecolocationback.entity.AreaGeneratorSource;
import kr.ac.kumoh.webkit.ecolocationback.entity.document.AreaGeneratorSourceDocument;
import kr.ac.kumoh.webkit.ecolocationback.entity.document.EnergyPotentialDocument;
import kr.ac.kumoh.webkit.ecolocationback.repository.AreaGeneratorSourceSearchRepository;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AreaGeneratorSourceSearchService {
    private final RestHighLevelClient elasticSearchClient;
    private final AreaGeneratorSourceSearchRepository areaGeneratorSourceSearchRepository;

    // 지역 이름요청을 보내면 해당하는 지역의 발전원별 발전설비 및 신재생에너지 전환율 데이터를 전부 전송한다.
    public List<AreaGeneratorSource> getAreaGeneratorSourcesByArea(String area) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("area", area));
        sourceBuilder.query(queryBuilder);

        SearchRequest searchRequest = new SearchRequest("area_generator_source_index");
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse;
        try {
            searchResponse = elasticSearchClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            // Handle exception
            return Collections.emptyList();
        }
        SearchHits hits = searchResponse.getHits();

        List<AreaGeneratorSource> results = Arrays.stream(hits.getHits())
                .map(hit -> {
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    return AreaGeneratorSource.builder()
                            .date(LocalDate.parse(sourceAsMap.get("date").toString()))
                            .area(sourceAsMap.get("area").toString())
                            .srcNucl(Double.parseDouble(sourceAsMap.get("srcNucl").toString()))
                            .srcBcoal(Double.parseDouble(sourceAsMap.get("srcBcoal").toString()))
                            .srcHcoal(Double.parseDouble(sourceAsMap.get("srcHcoal").toString()))
                            .srcOil(Double.parseDouble(sourceAsMap.get("srcOil").toString()))
                            .srcLng(Double.parseDouble(sourceAsMap.get("srcLng").toString()))
                            .srcPump(Double.parseDouble(sourceAsMap.get("srcPump").toString()))
                            .srcFuelcell(Double.parseDouble(sourceAsMap.get("srcFuelcell").toString()))
                            .srcCoalgas(Double.parseDouble(sourceAsMap.get("srcCoalgas").toString()))
                            .srcSolar(Double.parseDouble(sourceAsMap.get("srcSolar").toString()))
                            .srcWind(Double.parseDouble(sourceAsMap.get("srcWind").toString()))
                            .srcWater(Double.parseDouble(sourceAsMap.get("srcWater").toString()))
                            .srcSea(Double.parseDouble(sourceAsMap.get("srcSea").toString()))
                            .srcBio(Double.parseDouble(sourceAsMap.get("srcBio").toString()))
                            .srcWaste(Double.parseDouble(sourceAsMap.get("srcWaste").toString()))
                            .srcRecycleSum(Double.parseDouble(sourceAsMap.get("srcRecycleSum").toString()))
                            .srcOther(Double.parseDouble(sourceAsMap.get("srcOther").toString()))
                            .srcAll(Double.parseDouble(sourceAsMap.get("srcAll").toString()))
                            .recyclePercent(Double.parseDouble(sourceAsMap.get("recyclePercent").toString()))
                            .build();
                })
                .collect(Collectors.toList());

        return results;
    }

    // 사용자가 시간(연월) 데이터를 통해 GET요청을 보내면 해당하는 시간의 발전원별 발전설비 및 신재생에너지 전환율 데이터를 전부 전송한다.
    // 포매터로 String으로 오는 데이터를 LocalDate로 바꿔준다.
    public List<AreaGeneratorSource> getAreaGeneratorSourcesByDate(String Date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM");
        LocalDate date = YearMonth.parse(Date, formatter).atDay(1);

        List<AreaGeneratorSourceDocument> areaGeneratorSourceDocumentList = StreamSupport.stream(areaGeneratorSourceSearchRepository.findByDate(date).spliterator(), false)
                .collect(Collectors.toList());

        return areaGeneratorSourceDocumentList.stream().map(AreaGeneratorSource::fromDocument).collect(Collectors.toList());
    }

    // 사용자가 특정 지역, 시작시간, 끝시간 데이터를 통해 GET요청을 보내면 해당하는 지역, 기간의 발전원별 발전설비 및 신재생에너지 전환율 데이터를 전부 전송한다.
    public List<AreaGeneratorSource> getAreaGeneratorSourcesByAreaAndBetweenDate(String Area, String StartDate, String EndDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM");
        LocalDate startDate = YearMonth.parse(StartDate, formatter).atDay(1);
        LocalDate endDate = YearMonth.parse(EndDate, formatter).atEndOfMonth();
        List<AreaGeneratorSourceDocument> areaGeneratorSourceDocumentList = StreamSupport.stream(areaGeneratorSourceSearchRepository.findByAreaAndDateBetween(Area, startDate, endDate).spliterator(), false)
                .collect(Collectors.toList());

        return areaGeneratorSourceDocumentList.stream().map(AreaGeneratorSource::fromDocument).collect(Collectors.toList());
    }

    public List<RenewableEnergyRateDto> getRenewableEnergyRateByDate(LocalDate start, LocalDate end){
        List<AreaGeneratorSourceDocument> areaGeneratorSourceDocumentList = StreamSupport.stream(areaGeneratorSourceSearchRepository.findByDateBetween(start, end).spliterator(), false)
                .collect(Collectors.toList());
        List<AreaGeneratorSource> areaGeneratorSourceList = areaGeneratorSourceDocumentList.stream().map(AreaGeneratorSource::fromDocument).collect(Collectors.toList());
        if (areaGeneratorSourceList.size() == 0) throw new NoSuchElementException("해당 날짜의 데이터가 없습니다.");

        List<RenewableEnergyRateDto> result = RenewableEnergyRateDto.calculateRenewableEnergyRates(areaGeneratorSourceList);

        return result;
    }
}
