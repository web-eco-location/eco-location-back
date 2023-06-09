package kr.ac.kumoh.webkit.ecolocationback.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AreaGeneratorSourceDto {

	// 기간(ex : 23-05-01, 23년 5월이라는 뜻)
    private String date;
    
    // 지역(ex : 경북,서울 등 광역지역만 됨)
    private String area;
    
    // 발전원 : 원자력
    private double srcNucl;
    
    // 발전원 : 유연탄
    private double srcBcoal;

    // 발전원 : 무연탄
    private double srcHcoal;
    
    // 발전원 : 유류
    private double srcOil;
    
    // 발전원 : LNG
    private double srcLng;
    
    // 발전원 : 양수
    private double srcPump;
    
    // 발전원 : 연료전지
    private double srcFuelcell;
    
    // 발전원 : 석탄가스
    private double srcCoalgas;
    
    // 발전원 : 태양에너지
    private double srcSolar;
    
    // 발전원 : 풍력
    private double srcWind;
    
    // 발전원 : 수력
    private double srcWater;
    
    // 발전원 : 해양에너지
    private double srcSea;
    
    // 발전원 : 바이오
    private double srcBio;
    
    // 발전원 : 폐기물
    private double srcWaste;
    
    // 신재생에너지 발전설비 총합
    private double srcRecycleSum;
    
    // 그 외 발전설비 총합
    private double srcOther;
    
    // 발전설비 전체
    private double srcAll;
    
    // 신재생에너지 비율(퍼센트)
    // 기존 에너지로 분류되는 발전원 : 원자력, 유연탄, 무연탄, 유류, LNG, 양수
    // 신재생에너지로 분류되는 발전원 : 연료전지, 석탄가스, 풍력, 수력, 해양에너지, 바이오, 폐기물
    // 비율 계산식 : ( 신재생에너지 발전설비 총합 / 전체 발전설비 총합 ) * 100
    private double recyclePercent;
	
}
