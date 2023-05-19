package kr.ac.kumoh.webkit.ecolocationback.dto.response;

import lombok.Data;

@Data
public class ComparePotentialAndGenerateDto {
    private String areaName;
    private double potentialAmount;
    private double generateAmount;

    public ComparePotentialAndGenerateDto(String areaName, double forecastEnergyPotential, double i) {
        this.areaName = areaName;
        this.potentialAmount = forecastEnergyPotential;
        this.generateAmount = i;
    }
}
