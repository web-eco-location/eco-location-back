package kr.ac.kumoh.webkit.ecolocationback.repository;

import kr.ac.kumoh.webkit.ecolocationback.entity.EnergyPotential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EnergyPotentialRepository extends JpaRepository<EnergyPotential, Long> {

    // 사용자가 원하는 기간을 지정하여 보내면 지정된 시간대 사이의 잠재 발전량 데이터를 전부 전송한다.
    List<EnergyPotential> findByForecastTimeBetween(LocalDateTime firstForecastTime, LocalDateTime secondForecastTime);
    List<EnergyPotential> findByPowerTypeAndForecastTimeBetween(String powerType,LocalDateTime startTime, LocalDateTime endTime);
}

