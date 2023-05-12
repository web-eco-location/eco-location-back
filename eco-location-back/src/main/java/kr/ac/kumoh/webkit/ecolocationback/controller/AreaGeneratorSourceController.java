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
    
    @GetMapping(params = "Area")
    public List<AreaGeneratorSource> getGeneratorsByArea(@RequestParam("Area") String Area) {
        return areaGeneratorSourceService.getAreaGeneratorSourcesByArea(Area);
    }
    
    @GetMapping(params = "Time")
    public List<AreaGeneratorSource> getGeneratorsByTime(@RequestParam("Time") String Time) {
        return areaGeneratorSourceService.getAreaGeneratorSourcesByTime(Time);
    }

}