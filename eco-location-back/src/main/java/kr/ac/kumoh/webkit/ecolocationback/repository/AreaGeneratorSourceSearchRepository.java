package kr.ac.kumoh.webkit.ecolocationback.repository;

import kr.ac.kumoh.webkit.ecolocationback.entity.AreaGeneratorSource;
import kr.ac.kumoh.webkit.ecolocationback.entity.document.AreaGeneratorSourceDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.time.LocalDate;
import java.util.List;

public interface AreaGeneratorSourceSearchRepository extends ElasticsearchRepository<AreaGeneratorSourceDocument, Long> {
    Iterable<AreaGeneratorSourceDocument> findByArea(String area);
    Iterable<AreaGeneratorSourceDocument> findByDate(LocalDate Date);
    Iterable<AreaGeneratorSourceDocument> findByDateBetween(LocalDate StartDate, LocalDate EndDate);
    Iterable<AreaGeneratorSourceDocument> findByAreaAndDateBetween(String Area, LocalDate StartDate, LocalDate EndDate);
}
