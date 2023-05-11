package kr.ac.kumoh.webkit.ecolocationback.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "EngergyPotentials")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class EnergyPotential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EngergyPotential_id")
    private Long id;
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

    @Builder
    public EnergyPotential(String PWR_EXC_TP_CD, String AREA, String CRTN_TM, String FCST_TM, String LEAD_TM, String FCST_EP, String FCST_CAPA) {
        this.PWR_EXC_TP_CD = PWR_EXC_TP_CD;
        this.AREA = AREA;
        this.CRTN_TM = CRTN_TM;
        this.FCST_TM = FCST_TM;
        this.LEAD_TM = LEAD_TM;
        this.FCST_EP = FCST_EP;
        this.FCST_CAPA = FCST_CAPA;
    }

}
