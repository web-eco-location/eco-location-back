package kr.ac.kumoh.webkit.ecolocationback;

import kr.ac.kumoh.webkit.ecolocationback.repository.AreaGeneratorSourceSearchRepository;
import kr.ac.kumoh.webkit.ecolocationback.repository.EnergyPotentialSearchRepository;
import kr.ac.kumoh.webkit.ecolocationback.repository.GeneratorSearchRepository;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
@EnableJpaRepositories(excludeFilters = @ComponentScan.Filter(
		type = FilterType.ASSIGNABLE_TYPE,
		classes = {AreaGeneratorSourceSearchRepository.class, EnergyPotentialSearchRepository.class,GeneratorSearchRepository.class}))
public class EcoLocationBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcoLocationBackApplication.class, args);
	}
}