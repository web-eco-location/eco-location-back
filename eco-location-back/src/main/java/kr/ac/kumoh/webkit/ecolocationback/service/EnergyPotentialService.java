package kr.ac.kumoh.webkit.ecolocationback.service;

import kr.ac.kumoh.webkit.ecolocationback.dto.response.PotentialByRegionDto;
import kr.ac.kumoh.webkit.ecolocationback.dto.response.PotentialBySourceDto;
import kr.ac.kumoh.webkit.ecolocationback.entity.EnergyPotential;
import kr.ac.kumoh.webkit.ecolocationback.repository.EnergyPotentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<PotentialByRegionDto> getAllEnergyPotentialBySourceAndYear(String sourceType, LocalDateTime startTime, LocalDateTime endTime) {
        List<EnergyPotential> energyPotentialList = energyPotentialRepository.findByPowerTypeAndForecastTimeBetween(sourceType,startTime,endTime);
        List<PotentialByRegionDto> response = groupAndSumByArea(energyPotentialList);
        return response;
    }

    public List<PotentialByRegionDto> getAllEnergyPotentialByDate(LocalDateTime startTime, LocalDateTime endTime) {
        List<EnergyPotential> energyPotentialList = energyPotentialRepository.findByForecastTimeBetween(startTime,endTime);
        List<PotentialByRegionDto> response = groupAndSumByArea(energyPotentialList);

        return response;
    }

    public List<PotentialBySourceDto> getAllEnergyPotentialByDateAndRegion(LocalDateTime startTime, LocalDateTime endTime) {
        List<EnergyPotential> energyPotentialList = energyPotentialRepository.findByForecastTimeBetween(startTime, endTime);
        List<PotentialBySourceDto> response = groupAndSumByAreaAndPowerType(energyPotentialList);

        return response;
    }

    public List<PotentialBySourceDto> groupAndSumByAreaAndPowerType(List<EnergyPotential> energyPotentials) {
        List<PotentialBySourceDto> potentialBySourceList  = new ArrayList<>();

        // 지역별로 그룹화된 EnergyPotential 맵 생성
        Map<String, List<EnergyPotential>> groupedEnergyPotentials = energyPotentials.stream()
                .collect(Collectors.groupingBy(EnergyPotential::getAreaName));

// 맵의 각 엔트리에 대해 PotentialBySourceDto 계산
        for (Map.Entry<String, List<EnergyPotential>> entry : groupedEnergyPotentials.entrySet()) {
            String areaName = entry.getKey();
            List<EnergyPotential> potentials = entry.getValue();

            double solarEnergyPotential = 0;
            double windEnergyPotential = 0;

            // EnergyPotential 리스트를 반복하면서 solar와 wind 잠재량을 계산
            for (EnergyPotential potential : potentials) {
                if (potential.getPowerType().equals("1")) {
                    solarEnergyPotential += potential.getForecastEnergyPotential();
                } else if (potential.getPowerType().equals("2")) {
                    windEnergyPotential += potential.getForecastEnergyPotential();
                }
            }

            // PotentialBySourceDto 객체 생성 및 리스트에 추가
            PotentialBySourceDto potentialBySourceDto = new PotentialBySourceDto(areaName, solarEnergyPotential, windEnergyPotential);
            potentialBySourceList.add(potentialBySourceDto);
        }
        return potentialBySourceList;
    }

    /**
     * @param dtoList
     * @return
     * @description entity 리스트를 받아 같은 지역끼리 묶어서 잠재량 더하기(지역별 잠재량 계산식)
     */
    public List<PotentialByRegionDto> groupAndSumByArea(List<EnergyPotential> dtoList) {
        Map<String, Double> sumMap = new HashMap<>();

        // 지역별로 그룹화 된 map 생성
        for (EnergyPotential dto : dtoList) {
            String areaName = dto.getAreaName();
            double potentialAmount = dto.getForecastEnergyPotential();

            sumMap.put(areaName, sumMap.getOrDefault(areaName, 0.0) + potentialAmount);
        }

        // 더한 결과 리스트로 반환
        List<PotentialByRegionDto> resultList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : sumMap.entrySet()) {
            String areaName = entry.getKey();
            double totalPotentialAmount = entry.getValue();

            PotentialByRegionDto resultDto = new PotentialByRegionDto(areaName, totalPotentialAmount);
            resultList.add(resultDto);
        }

        return resultList;
    }
}
