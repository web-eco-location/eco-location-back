package kr.ac.kumoh.webkit.ecolocationback.repository;

import kr.ac.kumoh.webkit.ecolocationback.entity.EnergyPotential;
import kr.ac.kumoh.webkit.ecolocationback.entity.document.EnergyPotentialDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EnergyPotentialSearchRepository extends ElasticsearchRepository<EnergyPotentialDocument, Long> {

    Iterable<EnergyPotentialDocument> findByForecastTimeBetween(LocalDateTime firstForecastTime, LocalDateTime secondForecastTime);
    Iterable<EnergyPotentialDocument> findByPowerTypeAndForecastTimeBetween(String powerType,LocalDateTime startTime, LocalDateTime endTime);
}

