package kr.ac.kumoh.webkit.ecolocationback.repository;

import kr.ac.kumoh.webkit.ecolocationback.entity.AreaGeneratorSource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaGeneratorSourceRepository extends JpaRepository<AreaGeneratorSource, Long> {
	List<AreaGeneratorSource> findByArea(String Area);
	List<AreaGeneratorSource> findByTime(String Time);
}
