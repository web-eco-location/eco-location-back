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
    
    public List<AreaGeneratorSource> getAllAreaGeneratorSource() {
        return areaGeneratorSourceRepository.findAll();
    }

    public AreaGeneratorSource saveAreaGeneratorSource(AreaGeneratorSource areaGeneratorSource) {
        return areaGeneratorSourceRepository.save(areaGeneratorSource);
    }

    public List<AreaGeneratorSource> saveAreaGeneratorSources(List<AreaGeneratorSource> areaGeneratorSource) {
        return areaGeneratorSourceRepository.saveAll(areaGeneratorSource);
    }
    
    public List<AreaGeneratorSource> getAreaGeneratorSourcesByArea(String Area) {
    	return areaGeneratorSourceRepository.findByArea(Area);
    }
    
    //포매터로 String으로 오는 데이터를 LocalDate로 바꿔준다.
    public List<AreaGeneratorSource> getAreaGeneratorSourcesByTime(String Time) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM");
    	LocalDate time = LocalDate.parse(Time + "-01", formatter);
    	
    	return areaGeneratorSourceRepository.findByTime(time);
    }
    
    public List<AreaGeneratorSource> getAreaGeneratorSourcesBetweenTime(String startTime, String endTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM");
        LocalDate startDate = YearMonth.parse(startTime, formatter).atDay(1);
        LocalDate endDate = YearMonth.parse(endTime, formatter).atEndOfMonth();

        return areaGeneratorSourceRepository.findByTimeBetween(startDate, endDate);
    }
    
    public List<AreaGeneratorSource> getAreaGeneratorSourcesByAreaAndBetweenTime(String area, String startTime, String endTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM");
        LocalDate startDate = YearMonth.parse(startTime, formatter).atDay(1);
        LocalDate endDate = YearMonth.parse(endTime, formatter).atEndOfMonth();

        return areaGeneratorSourceRepository.findByAreaAndTimeBetween(area, startDate, endDate);
    }
}
