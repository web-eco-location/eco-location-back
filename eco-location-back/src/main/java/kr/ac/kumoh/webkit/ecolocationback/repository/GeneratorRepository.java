package kr.ac.kumoh.webkit.ecolocationback.repository;

import kr.ac.kumoh.webkit.ecolocationback.entity.Generator;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneratorRepository extends JpaRepository<Generator, Long> {
	
	List<Generator> findByDetailArea(String detailArea);
	List<Generator> findByPowerSource(String powerSource);
}
