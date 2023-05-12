package kr.ac.kumoh.webkit.ecolocationback.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EnergyPotentialDto {
    // 전력 구분
    private String powerType;
    // 지역 구분
    private String areaName;
    // 생성 시간
    private String createTime_s;
    // 예측 시간
    private String forecastTime_s;
    // 선행시간
    private long leadTime;
    // 예측 에너지 잠재량
    private double forecastEnergyPotential;
    // 예측 설비용량
    private double forecastCapacity;
}
