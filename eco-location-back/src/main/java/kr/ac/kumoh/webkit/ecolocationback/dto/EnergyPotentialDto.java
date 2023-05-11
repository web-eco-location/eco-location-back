package kr.ac.kumoh.webkit.ecolocationback.dto;

import lombok.Data;

@Data
public class EnergyPotentialDto {
    // 전력 구분
    private String PWR_EXC_TP_CD;
    // 지역 구분
    private String AREA;
    // 생성 시간
    private String CRTN_TM;
    // 예측 시간
    private String FCST_TM;
    // 선행시간
    private String LEAD_TM;
    // 예측 에너지 잠재량
    private String FCST_EP;
    // 예측 설비용량
    private String FCST_CAPA;
}
