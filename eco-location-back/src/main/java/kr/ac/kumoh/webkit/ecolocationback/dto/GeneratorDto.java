package kr.ac.kumoh.webkit.ecolocationback.dto;

import lombok.Data;

@Data
public class GeneratorDto {
    // 회사명
    private String companyName;
    // 발전기명
    private String generatorName;
    // 호기
    private String generatorNum;
    // 설비용량
    private String generateAmount;
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
}
