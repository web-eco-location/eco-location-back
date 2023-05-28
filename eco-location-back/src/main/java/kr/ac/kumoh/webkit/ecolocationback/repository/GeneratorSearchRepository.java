package kr.ac.kumoh.webkit.ecolocationback.repository;

import kr.ac.kumoh.webkit.ecolocationback.entity.Generator;
import kr.ac.kumoh.webkit.ecolocationback.entity.document.GeneratorDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneratorSearchRepository extends ElasticsearchRepository<GeneratorDocument, Long> {
	
	Iterable<GeneratorDocument> findByDetailArea(String detailArea);

	Iterable<GeneratorDocument> findByPowerSource(String powerSource);
}
