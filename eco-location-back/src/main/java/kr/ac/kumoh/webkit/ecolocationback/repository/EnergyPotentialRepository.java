package kr.ac.kumoh.webkit.ecolocationback.repository;

import kr.ac.kumoh.webkit.ecolocationback.entity.EnergyPotential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnergyPotentialRepository extends JpaRepository<EnergyPotential, Long> {

    List<EnergyPotential> findByForecastTime(String forecastTime);

}

