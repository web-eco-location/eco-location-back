package kr.ac.kumoh.webkit.ecolocationback.service;

import kr.ac.kumoh.webkit.ecolocationback.dto.response.PotentialByRegionDto;
import kr.ac.kumoh.webkit.ecolocationback.entity.EnergyPotential;
import kr.ac.kumoh.webkit.ecolocationback.repository.EnergyPotentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnergyPotentialService {

    @Autowired
    private EnergyPotentialRepository energyPotentialRepository;

    // 잠재 발전량 데이터 전부 전송
    public List<EnergyPotential> getAllEnergyPotential(){
        return energyPotentialRepository.findAll();
    }

    // 사용자가 원하는 기간을 지정하여 보내면 지정된 시간대 사이의 잠재 발전량 데이터를 전부 전송한다.
    public List<EnergyPotential> getEPByForecastTimeBetween(LocalDateTime firstForecastTime, LocalDateTime secondForecastTime){
        return energyPotentialRepository.findByForecastTimeBetween(firstForecastTime, secondForecastTime);
    }

    public PotentialByRegionDto getAllEnergyPotentialBySourceAndYear(String sourceType, LocalDateTime startTime, LocalDateTime endTime) {
         List<EnergyPotential> energyPotentialList = energyPotentialRepository.findByPowerTypeAndForecastTimeBetween(sourceType,startTime,endTime);
        PotentialByRegionDto response = new PotentialByRegionDto();
        for (EnergyPotential item:
             energyPotentialList) {
            response.addPotentialByEntity(item);
        }
        return response;
    }

    public PotentialByRegionDto getAllEnergyPotentialByDate(LocalDateTime startTime, LocalDateTime endTime) {
        List<EnergyPotential> energyPotentialList = energyPotentialRepository.findByForecastTimeBetween(startTime,endTime);
        PotentialByRegionDto response = new PotentialByRegionDto();
        for (EnergyPotential item:
                energyPotentialList) {
            response.addPotentialByEntity(item);
        }
        return response;
    }
}
