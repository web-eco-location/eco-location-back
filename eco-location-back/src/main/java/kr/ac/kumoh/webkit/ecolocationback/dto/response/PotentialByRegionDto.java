package kr.ac.kumoh.webkit.ecolocationback.dto.response;

import kr.ac.kumoh.webkit.ecolocationback.entity.EnergyPotential;
import lombok.Data;

@Data
public class PotentialByRegionDto {
    private String areaName;
    private double potentialAmount;

    public PotentialByRegionDto(String areaName, double potentialAmount) {
        this.areaName = areaName;
        this.potentialAmount = potentialAmount;
    }

    public void addRegionPotentialByEntity(EnergyPotential item) {
        String areaName = item.getAreaName();
        double forecastEnergyPotential = item.getForecastEnergyPotential();

        this.areaName=areaName;
        this.potentialAmount = forecastEnergyPotential;
    }
}
