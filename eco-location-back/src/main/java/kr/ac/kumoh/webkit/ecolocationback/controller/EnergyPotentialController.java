package kr.ac.kumoh.webkit.ecolocationback.controller;

import kr.ac.kumoh.webkit.ecolocationback.entity.EnergyPotential;
import kr.ac.kumoh.webkit.ecolocationback.service.EnergyPotentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/energy-potential")
public class EnergyPotentialController {

    @Autowired
    private EnergyPotentialService energyPotentialService;

    // 잠재 발전량 데이터 전부 전송
    @GetMapping
    public List<EnergyPotential> getAllEnergyPotential(){
        return energyPotentialService.getAllEnergyPotential();
    }

    // 예측 시간에 맞춰서 데이터 전송 (시간대 1시간 단위 구분용)
    @GetMapping(params = "forecastTime")
    public List<EnergyPotential> getEPByForecastTime(@RequestParam("forecastTime") String forecastTime){
        return energyPotentialService.getByForecastTime(forecastTime);
    }
}
