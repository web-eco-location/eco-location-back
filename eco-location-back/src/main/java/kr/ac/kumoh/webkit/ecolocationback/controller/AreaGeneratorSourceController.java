package kr.ac.kumoh.webkit.ecolocationback.controller;

import java.time.LocalDate;
import java.util.List;

import kr.ac.kumoh.webkit.ecolocationback.dto.response.RenewableEnergyRateDto;
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
    
    @GetMapping("/allAGS")
    public List<AreaGeneratorSource> getAreaGeneratorSource(){
        return areaGeneratorSourceService.getAllAreaGeneratorSource();
    }
    
    @GetMapping(params = "Area")
    public List<AreaGeneratorSource> getGeneratorsByArea(@RequestParam("Area") String Area) {
        return areaGeneratorSourceService.getAreaGeneratorSourcesByArea(Area);
    }
    
    @GetMapping(params = "Date")
    public List<AreaGeneratorSource> getGeneratorsByDate(@RequestParam("Date") String Date) {
        return areaGeneratorSourceService.getAreaGeneratorSourcesByDate(Date);
    }

    @GetMapping
    public ResponseEntity<List<RenewableEnergyRateDto>> getRenewableEnergyRateByDate(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        List<RenewableEnergyRateDto> response = areaGeneratorSourceService.getRenewableEnergyRateByDate(startDate, endDate);
        return ResponseEntity.ok().body(response);
    }
    
    @GetMapping(params = {"Area", "StartDate", "EndDate"})
    public List<AreaGeneratorSource> getAreaGeneratorSourcesByAreaAndBetweenDate(@RequestParam("Area") String Area,
                                                                                  @RequestParam("StartDate") String StartDate,
                                                                                  @RequestParam("EndDate") String EndDate) {
        return areaGeneratorSourceService.getAreaGeneratorSourcesByAreaAndBetweenDate(Area, StartDate, EndDate);
    }


}