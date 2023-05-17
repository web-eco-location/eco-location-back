package kr.ac.kumoh.webkit.ecolocationback.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kumoh.webkit.ecolocationback.entity.AreaGeneratorSource;
import kr.ac.kumoh.webkit.ecolocationback.repository.AreaGeneratorSourceRepository;

@Service
public class AreaGeneratorSourceService {

    @Autowired
    private AreaGeneratorSourceRepository areaGeneratorSourceRepository;
    
    // 지역별 발전원별 발전설비 데이터 전부 전송
    public List<AreaGeneratorSource> getAllAreaGeneratorSource() {
        return areaGeneratorSourceRepository.findAll();
    }

    public AreaGeneratorSource saveAreaGeneratorSource(AreaGeneratorSource areaGeneratorSource) {
        return areaGeneratorSourceRepository.save(areaGeneratorSource);
    }

    public List<AreaGeneratorSource> saveAreaGeneratorSources(List<AreaGeneratorSource> areaGeneratorSource) {
        return areaGeneratorSourceRepository.saveAll(areaGeneratorSource);
    }
    
    // 사용자가 지역 데이터를 통해 GET요청을 보내면 해당하는 지역의 발전원별 발전설비 및 신재생에너지 전환율 데이터를 전부 전송한다.
    // localhost:8080/areageneratorsource?Area=서울
    public List<AreaGeneratorSource> getAreaGeneratorSourcesByArea(String Area) {
    	return areaGeneratorSourceRepository.findByArea(Area);
    }
    
    // 사용자가 시간(연월) 데이터를 통해 GET요청을 보내면 해당하는 시간의 발전원별 발전설비 및 신재생에너지 전환율 데이터를 전부 전송한다.
    // 포매터로 String으로 오는 데이터를 LocalDate로 바꿔준다.
    // localhost:8080/areageneratorsource?Date=23-01
    public List<AreaGeneratorSource> getAreaGeneratorSourcesByDate(String Date) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM");
    	LocalDate date = YearMonth.parse(Date, formatter).atDay(1);
    	
    	return areaGeneratorSourceRepository.findByDate(date);
    }
    
    // 사용자가 시작시간, 끝시간 데이터를 통해 GET요청을 보내면 해당하는 기간의 발전원별 발전설비 및 신재생에너지 전환율 데이터를 전부 전송한다.
    // 아래 주석과 같이 요청을 보내면 된다.
    // localhost:8080/areageneratorsource?StartDate=23-01&EndDate=23-03
    public List<AreaGeneratorSource> getAreaGeneratorSourcesBetweenDate(String StartDate, String EndDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM");
        LocalDate startDate = YearMonth.parse(StartDate, formatter).atDay(1);
        LocalDate endDate = YearMonth.parse(EndDate, formatter).atEndOfMonth();

        return areaGeneratorSourceRepository.findByDateBetween(startDate, endDate);
    }
    
    // 사용자가 특정 지역, 시작시간, 끝시간 데이터를 통해 GET요청을 보내면 해당하는 지역, 기간의 발전원별 발전설비 및 신재생에너지 전환율 데이터를 전부 전송한다.
    // 아래 주석과 같이 요청을 보내면 된다.
    // localhost:8080/areageneratorsource?Area=서울&StartDate=23-01&EndDate=23-03
    public List<AreaGeneratorSource> getAreaGeneratorSourcesByAreaAndBetweenDate(String Area, String StartDate, String EndDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM");
        LocalDate startDate = YearMonth.parse(StartDate, formatter).atDay(1);
        LocalDate endDate = YearMonth.parse(EndDate, formatter).atEndOfMonth();

        return areaGeneratorSourceRepository.findByAreaAndDateBetween(Area, startDate, endDate);
    }
}
