package kr.ac.kumoh.webkit.ecolocationback.service;

import kr.ac.kumoh.webkit.ecolocationback.entity.EnergyPotential;
import kr.ac.kumoh.webkit.ecolocationback.repository.EnergyPotentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnergyPotentialService {

    @Autowired
    private EnergyPotentialRepository energyPotentialRepository;

    // 잠재 발전량 데이터 전부 전송
    public List<EnergyPotential> getAllEnergyPotential(){
        return energyPotentialRepository.findAll();
    }

    // 예측 시간에 맞춰서 데이터 전송 (시간대 1시간 단위 구분용)
    public List<EnergyPotential> getByForecastTime(String forecastTime){
        return energyPotentialRepository.findByForecastTime(forecastTime);
    }

}
