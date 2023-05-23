package kr.ac.kumoh.webkit.ecolocationback.dto.response;

import kr.ac.kumoh.webkit.ecolocationback.entity.AreaGeneratorSource;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class RenewableEnergyRateDto {
    private String areaName;
    private double renewableEnergyPercent;

    private RenewableEnergyRateDto(String areaName, double renewableEnergyPercent) {
        this.areaName = areaName;
        this.renewableEnergyPercent = renewableEnergyPercent;
    }

    public static List<RenewableEnergyRateDto> calculateRenewableEnergyRates(List<AreaGeneratorSource> areaGeneratorSources) {
        Map<String, Double> totalEnergyMap = new HashMap<>();
        Map<String, Double> renewableEnergyMap = new HashMap<>();

        // 각 지역별 총 에너지량과 신재생에너지량 계산
        for (AreaGeneratorSource areaGeneratorSource : areaGeneratorSources) {
            String areaName = areaGeneratorSource.getArea();
            double totalEnergy = totalEnergyMap.getOrDefault(areaName, 0.0) + areaGeneratorSource.getSrcAll();
            double renewableEnergy = renewableEnergyMap.getOrDefault(areaName, 0.0) + areaGeneratorSource.getSrcRecycleSum();
            totalEnergyMap.put(areaName, totalEnergy);
            renewableEnergyMap.put(areaName, renewableEnergy);
        }

        List<RenewableEnergyRateDto> renewableEnergyRates = new ArrayList<>();

        // 지역별 신재생에너지 비율 계산
        for (String areaName : totalEnergyMap.keySet()) {
            double totalEnergy = totalEnergyMap.get(areaName);
            double renewableEnergy = renewableEnergyMap.get(areaName);
            double renewableEnergyPercent = renewableEnergy / totalEnergy;
            RenewableEnergyRateDto renewableEnergyRateDto = new RenewableEnergyRateDto(areaName, renewableEnergyPercent);
            renewableEnergyRates.add(renewableEnergyRateDto);
        }

        return renewableEnergyRates;
    }
}
