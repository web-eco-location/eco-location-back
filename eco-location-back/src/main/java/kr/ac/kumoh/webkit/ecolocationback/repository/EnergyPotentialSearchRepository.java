package kr.ac.kumoh.webkit.ecolocationback.repository;

import kr.ac.kumoh.webkit.ecolocationback.dto.response.PotentialBySourceDto;
import kr.ac.kumoh.webkit.ecolocationback.entity.EnergyPotential;
import kr.ac.kumoh.webkit.ecolocationback.entity.document.EnergyPotentialDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EnergyPotentialSearchRepository extends ElasticsearchRepository<EnergyPotentialDocument, String> {

    @Query("{\"bool\": {\"must\": [{\"range\": {\"forecastTime\": {\"gte\": \"?0\", \"lte\": \"?1\"}}}]}}")
    Iterable<EnergyPotentialDocument> findByForecastTimeBetween(LocalDateTime firstForecastTime, LocalDateTime secondForecastTime);
    Iterable<EnergyPotentialDocument> findByPowerTypeAndForecastTimeBetween(String powerType,LocalDateTime startTime, LocalDateTime endTime);
    @Query("\"query\": {\"bool\": {\"filter\": [{\"range\": {\"forecastTime\": {\"gte\": \"?0\",\"lte\": \"?1\"}}}]}}, \"aggs\": {\"areaName\": {\"terms\": {\"field\": \"areaName.keyword\",\"size\": 10}, \"aggs\": {\"filtered\": {\"filter\": {\"bool\": {\"should\": [{\"term\": {\"powerType\": \"1\"}},{\"term\": {\"powerType\": \"2\"}}]}}, \"aggs\": {\"energyPotentialSum\": {\"sum\": {\"field\": \"forecastEnergyPotential\"}}}}}}}}")
    List<PotentialBySourceDto> getAllEnergyPotentialByDateAndRegion(@Param("0") LocalDateTime startTime, @Param("1") LocalDateTime endTime);
}

