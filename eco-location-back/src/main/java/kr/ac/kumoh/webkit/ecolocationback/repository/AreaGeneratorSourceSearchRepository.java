package kr.ac.kumoh.webkit.ecolocationback.repository;

import kr.ac.kumoh.webkit.ecolocationback.entity.AreaGeneratorSource;
import kr.ac.kumoh.webkit.ecolocationback.entity.document.AreaGeneratorSourceDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.time.LocalDate;
import java.util.List;

public interface AreaGeneratorSourceSearchRepository extends ElasticsearchRepository<AreaGeneratorSourceDocument, String> {
    @Query("{\"bool\": {\"must\": [{\"match\": {\"area\": \"?0\"}}]}}")
    Iterable<AreaGeneratorSourceDocument> findByArea(String area);
    @Query("{\"bool\": {\"must\": [{\"match\": {\"date\": \"?0\"}}]}}")
    Iterable<AreaGeneratorSourceDocument> findByDate(LocalDate Date);
    @Query("{\"bool\": {\"must\": [{\"range\": {\"date\": {\"gte\": \"?0\", \"lte\": \"?1\"}}}]}}")
    Iterable<AreaGeneratorSourceDocument> findByDateBetween(LocalDate StartDate, LocalDate EndDate);
    @Query("{\"bool\": {\"must\": [{\"match\": {\"date\": \"?0\"}, {\"range\": {\"date\": {\"gte\": \"?1\", \"lte\": \"?2\"}}]}}")
    Iterable<AreaGeneratorSourceDocument> findByAreaAndDateBetween(String Area, LocalDate StartDate, LocalDate EndDate);
}
