package kr.ac.kumoh.webkit.ecolocationback.dto.response;

import lombok.Data;

@Data
public class RefineGeneratorDto {
    private String wideArea;
    private String powerSource;
    private double amount;

    public RefineGeneratorDto(String wideArea, String powerSource, double amount){
        this.wideArea = wideArea;
        this.powerSource = powerSource;
        this.amount = amount;
    }
}
