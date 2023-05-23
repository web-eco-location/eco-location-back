package kr.ac.kumoh.webkit.ecolocationback.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "generators")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity(name="Generator")
public class Generator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "generator_id")
    private Long id;

    // 회사명
    private String companyName;
    // 발전기명
    private String generatorName;
    // 호기
    private String generatorNum;
    // 설비용량
    private double generateAmount;
    // 회원구분
    private String memberType;
    // 급전방식
    private String powerSupply;
    // 발전원
    private String powerSource;
    // 발전종류
    private String generationType;
    // 사업구분
    private String businessType;
    // 광역지역
    private String wideArea;
    // 세부지역
    private String detailArea;

    @Builder
    public Generator(String companyName, String generatorName, String generatorNum, double generateAmount, String memberType, String powerSupply, String powerSource, String generationType, String businessType, String wideArea, String detailArea) {
        this.companyName = companyName;
        this.generatorName = generatorName;
        this.generatorNum = generatorNum;
        this.generateAmount = generateAmount;
        this.memberType = memberType;
        this.powerSupply = powerSupply;
        this.powerSource = powerSource;
        this.generationType = generationType;
        this.businessType = businessType;
        this.wideArea = wideArea;
        this.detailArea = detailArea;
    }
}
