package kr.ac.kumoh.webkit.ecolocationback.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "EnergyPotentials")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class EnergyPotential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EnergyPotential_id")
    private Long id;
    // 전력 구분
    private String powerType;
    // 지역 구분
    private String areaName;
    // 생성 시간
    private String createTime;
    // 예측 시간
    private String forecastTime;
    // 선행시간
    private String leadTime;
    // 예측 에너지 잠재량
    private double forecastEnergyPotential;
    // 예측 설비용량
    private double forecastCapacity;

    @Builder
    public EnergyPotential(String powerType, String areaName, String createTime, String forecastTime, String leadTime, double forecastEnergyPotential, double forecastCapacity) {
        this.powerType = powerType;
        this.areaName = areaName;
        this.createTime = createTime;
        this.forecastTime = forecastTime;
        this.leadTime = leadTime;
        this.forecastEnergyPotential = forecastEnergyPotential;
        this.forecastCapacity = forecastCapacity;
    }
}