package kr.ac.kumoh.webkit.ecolocationback.dto;

import lombok.Data;

@Data
public class RecycleRatioDto {

	// 기간(ex : 2023-05-23, 23년 5월이라는 뜻이고, 뒤의 23도 년도 표시임)
    private String time;
    
    // 지역
    private String area;
    
    // 발전원 : 원자력
    private String srcNucl;
    
    // 발전원 : 유연탄
    private String srcBcoal;

    // 발전원 : 무연탄
    private String srcHcoal;
    
    // 발전원 : 유류
    private String srcOil;
    
    // 발전원 : LNG
    private String srcLng;
    
    // 발전원 : 양수
    private String srcPump;
    
    // 발전원 : 연료전지
    private String srcFuelcell;
    
    // 발전원 : 석탄가스
    private String srcCoalgas;
    
    // 발전원 : 태양에너지
    private String srcSolar;
    
    // 발전원 : 풍력
    private String srcWind;
    
    // 발전원 : 수력
    private String srcWater;
    
    // 발전원 : 해양에너지
    private String srcSea;
    
    // 발전원 : 바이오
    private String srcBio;
    
    // 발전원 : 폐기물
    private String srcWaste;
    
    // 신재생에너지 발전설비 총합
    private String srcRecycleSum;
    
    // 그 외 발전설비 총합
    private String srcOther;
    
    // 발전설비 전체
    private String srcAll;
    
    // 신재생에너지 비율(퍼센트)
    private String recyclePercent;
	
}
