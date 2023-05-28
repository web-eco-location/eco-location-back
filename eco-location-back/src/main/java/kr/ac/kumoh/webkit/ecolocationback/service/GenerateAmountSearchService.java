package kr.ac.kumoh.webkit.ecolocationback.service;

import kr.ac.kumoh.webkit.ecolocationback.dto.response.GenerateAmountByAreaDto;
import kr.ac.kumoh.webkit.ecolocationback.dto.response.GenerateAmountByYearDto;
import kr.ac.kumoh.webkit.ecolocationback.entity.AreaGeneratorSource;
import kr.ac.kumoh.webkit.ecolocationback.entity.document.AreaGeneratorSourceDocument;
import kr.ac.kumoh.webkit.ecolocationback.entity.document.GeneratorDocument;
import kr.ac.kumoh.webkit.ecolocationback.repository.AreaGeneratorSourceSearchRepository;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Sum;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class GenerateAmountSearchService {

    private final RestHighLevelClient elasticsearchClient;
    private final AreaGeneratorSourceSearchRepository areaGeneratorSourceSearchRepository;

    // 연도를 입력받아 지역별로 발전량을 반환하는 메소드.
    @PersistenceContext
    private EntityManager entityManager;
    public List<GenerateAmountByAreaDto> GenerateAmountAreaByYear(String date) throws IOException {
        SearchRequest searchRequest = new SearchRequest("area_generator_source");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // Filter by date
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.wildcardQuery("date", date + "*"));
        boolQueryBuilder.mustNot(QueryBuilders.termQuery("area", "소계"));
        searchSourceBuilder.query(boolQueryBuilder);

        // Aggregation by area
        TermsAggregationBuilder areaAggregation = AggregationBuilders.terms("area").field("area").size(10);
        SumAggregationBuilder srcSolarSumAggregation = AggregationBuilders.sum("srcSolarSum").field("srcSolar");
        SumAggregationBuilder srcWindSumAggregation = AggregationBuilders.sum("srcWindSum").field("srcWind");

        areaAggregation.subAggregation(srcSolarSumAggregation).subAggregation(srcWindSumAggregation);
        searchSourceBuilder.aggregation(areaAggregation);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);

        List<GenerateAmountByAreaDto> resultList = new ArrayList<>();

        // Parse aggregation results
        Terms areaTerms = searchResponse.getAggregations().get("area");
        for (Terms.Bucket areaBucket : areaTerms.getBuckets()) {
            String area = areaBucket.getKeyAsString();

            double solarAmount = ((Sum) areaBucket.getAggregations().get("srcSolarSum")).getValue();
            double windAmount = ((Sum) areaBucket.getAggregations().get("srcWindSum")).getValue();

            GenerateAmountByAreaDto generateAmountByAreaDto = new GenerateAmountByAreaDto(area, solarAmount, windAmount);
            resultList.add(generateAmountByAreaDto);
        }

        if (resultList.size() == 0) {
            throw new NoSuchElementException("해당하는 년도의 데이터가 없습니다");
        }

        return resultList;
    }


    // 지역을 입력 받아 발전원 별로 발전량을 표시한다. 18~23년도별 연평균 발전량을 반환하는 메소드.
    public List<GenerateAmountByYearDto> getGenerateAmountAverageByArea(String areaName) {
        List<AreaGeneratorSourceDocument> generatorAmountDocumentList = StreamSupport.stream(areaGeneratorSourceSearchRepository.findByArea(areaName).spliterator(), false)
                .collect(Collectors.toList());
        List<AreaGeneratorSource> sourceList = generatorAmountDocumentList.stream().map(AreaGeneratorSource::fromDocument).collect(Collectors.toList());
        if (sourceList.size() == 0) throw new NoSuchElementException("해당하는 지역의 데이터가 없습니다");

        List<GenerateAmountByYearDto> result = GenerateAmountByYearDto.calculateAveragesByYear(sourceList);

        return result;
    }
}
