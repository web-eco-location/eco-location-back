package kr.ac.kumoh.webkit.ecolocationback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kumoh.webkit.ecolocationback.entity.Generator;
import kr.ac.kumoh.webkit.ecolocationback.service.GeneratorService;

@RestController
@RequestMapping("/generators")
public class GeneratorController {

    @Autowired
    private GeneratorService generatorService;

    @GetMapping
    public List<Generator> getAllGenerators() {
        return generatorService.getAllGenerators();
    }
    
    @GetMapping(params = "detailArea")
    public List<Generator> getGeneratorsByDetailArea(@RequestParam("detailArea") String detailArea) {
        return generatorService.getGeneratorsByDetailArea(detailArea);
    }
    
    @GetMapping(params = "powerSource")
    public List<Generator> getGeneratorsByPowerSource(@RequestParam("powerSource") String powerSource) {
        return generatorService.getGeneratorsByPowerSource(powerSource);
    }

}