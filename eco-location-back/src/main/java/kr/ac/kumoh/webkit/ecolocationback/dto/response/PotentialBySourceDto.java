package kr.ac.kumoh.webkit.ecolocationback.dto.response;

import kr.ac.kumoh.webkit.ecolocationback.entity.EnergyPotential;
import lombok.Data;

@Data
public class PotentialBySourceDto {
    private double potentialOfSolar = 0;
    private double potentialOfWind = 0;

    public void addPotentialByEntity(EnergyPotential item) {
        switch (item.getPowerType()) {
            case "1":
                this.potentialOfSolar += item.getForecastEnergyPotential();
                break;
            case "2":
                this.potentialOfWind += item.getForecastEnergyPotential();
                break;
            default:
                // 예외 처리
                break;
        }
    }
}
