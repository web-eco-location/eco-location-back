package kr.ac.kumoh.webkit.ecolocationback.dto.response;

import kr.ac.kumoh.webkit.ecolocationback.entity.EnergyPotential;
import lombok.Data;

@Data
public class PotentialByRegionDto {
    private double GyeonggiDo = 0;
    private double GangwonDo = 0;
    private double ChungcheongDo = 0;
    private double JeollabukDo = 0;
    private double JeollanamDo = 0;
    private double GyeongsangbukDo = 0;
    private double GyeongsangnamDo = 0;
    private double  JejuIsland = 0;

    public void addPotentialByEntity(EnergyPotential item) {
        switch (item.getAreaName()) {
            case "경기도":
                this.GyeonggiDo += item.getForecastEnergyPotential();
                break;
            case "강원도":
                this.GangwonDo += item.getForecastEnergyPotential();
                break;
            case "충청도":
                this.ChungcheongDo += item.getForecastEnergyPotential();
                break;
            case "전라북도":
                this.JeollabukDo += item.getForecastEnergyPotential();
                break;
            case "전라남도":
                this.JeollanamDo += item.getForecastEnergyPotential();
                break;
            case "경상북도":
                this.GyeongsangbukDo += item.getForecastEnergyPotential();
                break;
            case "경상남도":
                this.GyeongsangnamDo += item.getForecastEnergyPotential();
                break;
            case "제주도":
                this.JejuIsland += item.getForecastEnergyPotential();
                break;
            default:
                // 예외 처리
                break;
        }
    }
}
