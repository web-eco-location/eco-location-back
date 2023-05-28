package kr.ac.kumoh.webkit.ecolocationback.service;

import kr.ac.kumoh.webkit.ecolocationback.dto.response.GeneratorCountDto;
import kr.ac.kumoh.webkit.ecolocationback.dto.response.RefineGeneratorDto;
import kr.ac.kumoh.webkit.ecolocationback.entity.Generator;
import kr.ac.kumoh.webkit.ecolocationback.entity.document.GeneratorDocument;
import kr.ac.kumoh.webkit.ecolocationback.repository.GeneratorSearchRepository;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Sum;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class GeneratorSearchService {
    private final RestHighLevelClient elasticsearchClient;

    private final GeneratorSearchRepository generatorSearchRepository;

    public List<Generator> getGeneratorsByDetailArea(String detailArea){
        List<GeneratorDocument> generatorDocumentList = StreamSupport.stream(generatorSearchRepository.findByDetailArea(detailArea).spliterator(), false)
                .collect(Collectors.toList());
    	return generatorDocumentList.stream().map(Generator::fromDocument).collect(Collectors.toList());
    }
    
    public List<Generator> getGeneratorsByPowerSource(String powerSource){
        List<GeneratorDocument> generatorDocumentList = StreamSupport.stream(generatorSearchRepository.findByPowerSource(powerSource).spliterator(), false)
                .collect(Collectors.toList());
    	return generatorDocumentList.stream().map(Generator::fromDocument).collect(Collectors.toList());
    }


    public GeneratorCountDto getGeneratorsCountByAreaAndPower(String detailArea) {
        List<GeneratorDocument> generatorDocumentList = StreamSupport.stream(generatorSearchRepository.findByDetailArea(detailArea).spliterator(), false)
                .collect(Collectors.toList());
        List<Generator> generatorList = generatorDocumentList.stream().map(Generator::fromDocument).collect(Collectors.toList());

        GeneratorCountDto generatorCountDto = new GeneratorCountDto();
        for (Generator generator : generatorList) {
            generatorCountDto.count(generator);
        }

        return generatorCountDto;
    }

    public List<RefineGeneratorDto> GeneratorRefine() throws IOException {
        SearchRequest searchRequest = new SearchRequest("generators");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // Filter by powerSource values
        searchSourceBuilder.query(QueryBuilders.termsQuery("powerSource",
                "바이오에너지", "수력에너지", "연료전지", "태양에너지", "풍력에너지"));

        // Aggregation by wideArea and powerSource
        TermsAggregationBuilder wideAreaAggregation = AggregationBuilders.terms("wideArea").field("wideArea").size(10);
        TermsAggregationBuilder powerSourceAggregation = AggregationBuilders.terms("powerSource").field("powerSource").size(10);
        SumAggregationBuilder generateAmountSumAggregation = AggregationBuilders.sum("generateAmountSum").field("generateAmount");

        wideAreaAggregation.subAggregation(powerSourceAggregation.subAggregation(generateAmountSumAggregation));
        searchSourceBuilder.aggregation(wideAreaAggregation);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);

        List<RefineGeneratorDto> resultList = new ArrayList<>();

        // Parse aggregation results
        Terms wideAreaTerms = searchResponse.getAggregations().get("wideArea");
        for (Terms.Bucket wideAreaBucket : wideAreaTerms.getBuckets()) {
            String wideArea = wideAreaBucket.getKeyAsString();

            Terms powerSourceTerms = wideAreaBucket.getAggregations().get("powerSource");
            for (Terms.Bucket powerSourceBucket : powerSourceTerms.getBuckets()) {
                String powerSource = powerSourceBucket.getKeyAsString();

                double generateAmountSum = ((Sum) powerSourceBucket.getAggregations().get("generateAmountSum")).getValue();

                RefineGeneratorDto refineGeneratorDto = new RefineGeneratorDto(wideArea, powerSource, generateAmountSum);
                resultList.add(refineGeneratorDto);
            }
        }

        return resultList;
    }
}
