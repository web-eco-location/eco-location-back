package kr.ac.kumoh.webkit.ecolocationback.controller.es;

import kr.ac.kumoh.webkit.ecolocationback.dto.response.RenewableEnergyRateDto;
import kr.ac.kumoh.webkit.ecolocationback.entity.AreaGeneratorSource;
import kr.ac.kumoh.webkit.ecolocationback.service.AreaGeneratorSourceSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/es/areageneratorsource")
@RequiredArgsConstructor
public class AreaGeneratorSourceSearchController {

    private final AreaGeneratorSourceSearchService areaGeneratorSourceSearchService;
    
    @GetMapping(params = "Area")
    public List<AreaGeneratorSource> getGeneratorsByArea(@RequestParam("Area") String area) {
        return areaGeneratorSourceSearchService.getAreaGeneratorSourcesByArea(area);
    }
    
    @GetMapping(params = "Date")
    public List<AreaGeneratorSource> getGeneratorsByDate(@RequestParam("Date") String Date) {
        return areaGeneratorSourceSearchService.getAreaGeneratorSourcesByDate(Date);
    }

    @GetMapping
    public ResponseEntity<List<RenewableEnergyRateDto>> getRenewableEnergyRateByDate(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        List<RenewableEnergyRateDto> response = areaGeneratorSourceSearchService.getRenewableEnergyRateByDate(startDate, endDate);
        return ResponseEntity.ok().body(response);
    }
    
    @GetMapping(params = {"Area", "StartDate", "EndDate"})
    public List<AreaGeneratorSource> getAreaGeneratorSourcesByAreaAndBetweenDate(@RequestParam("Area") String Area,
                                                                                  @RequestParam("StartDate") String StartDate,
                                                                                  @RequestParam("EndDate") String EndDate) {
        return areaGeneratorSourceSearchService.getAreaGeneratorSourcesByAreaAndBetweenDate(Area, StartDate, EndDate);
    }


}