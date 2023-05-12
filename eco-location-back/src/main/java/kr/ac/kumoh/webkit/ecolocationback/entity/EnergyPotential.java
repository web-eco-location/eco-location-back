package kr.ac.kumoh.webkit.ecolocationback.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private LocalDateTime createTime;
    // 예측 시간
    private LocalDateTime forecastTime;
    // 선행시간
    private Long leadTime;
    // 예측 에너지 잠재량
    private double forecastEnergyPotential;
    // 예측 설비용량
    private double forecastCapacity;

    @Builder
    public EnergyPotential(String powerType, String areaName, String createTime, String forecastTime,
                           Long leadTime, double forecastEnergyPotential, double forecastCapacity) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");

        LocalDateTime createTime_s = LocalDateTime.parse(createTime, formatter);
        LocalDateTime forecastTime_s = LocalDateTime.parse(forecastTime, formatter);

        this.powerType = powerType;
        this.areaName = areaName;
        this.createTime = createTime_s;
        this.forecastTime = forecastTime_s;
        this.leadTime = leadTime;
        this.forecastEnergyPotential = forecastEnergyPotential;
        this.forecastCapacity = forecastCapacity;
    }
}