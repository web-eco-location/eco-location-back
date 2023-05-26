package kr.ac.kumoh.webkit.ecolocationback.dto.response;

import lombok.Data;

@Data
public class GenerateAmountByAreaDto {
    private String area;
    private double solarAmount;
    private double windAmount;
    private double totalAmount;

    public GenerateAmountByAreaDto(String area, double solarAmount, double windAmount){
        this.area = area;
        this.solarAmount = solarAmount;
        this.windAmount = windAmount;
        this.totalAmount = solarAmount + windAmount;
    }
}
