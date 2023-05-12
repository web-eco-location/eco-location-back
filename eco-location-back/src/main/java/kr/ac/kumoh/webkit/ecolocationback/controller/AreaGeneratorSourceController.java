package kr.ac.kumoh.webkit.ecolocationback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kumoh.webkit.ecolocationback.entity.AreaGeneratorSource;
import kr.ac.kumoh.webkit.ecolocationback.service.AreaGeneratorSourceService;

@RestController
@RequestMapping("/areageneratorsource")
public class AreaGeneratorSourceController {

    @Autowired
    private AreaGeneratorSourceService areaGeneratorSourceService;
    
    // 지역별 발전원별 발전설비 데이터 전부 전송
    @GetMapping("/allAGS")
    public List<AreaGeneratorSource> getAreaGeneratorSource(){
        return areaGeneratorSourceService.getAllAreaGeneratorSource();
    }
     
    // 사용자가 지역 데이터를 통해 GET요청을 보내면 해당하는 지역의 발전원별 발전설비 및 신재생에너지 전환율 데이터를 전부 전송한다.
    @GetMapping(params = "Area")
    public List<AreaGeneratorSource> getGeneratorsByArea(@RequestParam("Area") String Area) {
        return areaGeneratorSourceService.getAreaGeneratorSourcesByArea(Area);
    }
    
    // 사용자가 시간(연월) 데이터를 통해 GET요청을 보내면 해당하는 시간의 발전원별 발전설비 및 신재생에너지 전환율 데이터를 전부 전송한다.
    @GetMapping(params = "Time")
    public List<AreaGeneratorSource> getGeneratorsByTime(@RequestParam("Time") String Time) {
        return areaGeneratorSourceService.getAreaGeneratorSourcesByTime(Time);
    }

}