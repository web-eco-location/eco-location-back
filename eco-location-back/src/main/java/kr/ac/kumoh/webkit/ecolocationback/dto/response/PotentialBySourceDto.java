package kr.ac.kumoh.webkit.ecolocationback.dto.response;

import lombok.Data;

@Data
public class PotentialBySourceDto {
    private String areaName;
    private double solarEnergyPotential;
    private double windEnergyPotential;

    public PotentialBySourceDto(String areaName, double solarEnergyPotential, double windEnergyPotential) {
        this.areaName = areaName;
        this.solarEnergyPotential = solarEnergyPotential;
        this.windEnergyPotential = windEnergyPotential;
    }
}
