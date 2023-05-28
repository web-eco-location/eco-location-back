package kr.ac.kumoh.webkit.ecolocationback.controller.es;

import kr.ac.kumoh.webkit.ecolocationback.dto.response.GeneratorCountDto;
import kr.ac.kumoh.webkit.ecolocationback.dto.response.RefineGeneratorDto;
import kr.ac.kumoh.webkit.ecolocationback.entity.Generator;
import kr.ac.kumoh.webkit.ecolocationback.service.GeneratorSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/es/generators")
@RequiredArgsConstructor
public class GeneratorSearchController {

    private final GeneratorSearchService generatorSearchService;
    
    @GetMapping(params = "detailArea")
    public List<Generator> getGeneratorsByDetailArea(@RequestParam("detailArea") String detailArea) {
        return generatorSearchService.getGeneratorsByDetailArea(detailArea);
    }
    
    @GetMapping(params = "powerSource")
    public List<Generator> getGeneratorsByPowerSource(@RequestParam("powerSource") String powerSource) {
        return generatorSearchService.getGeneratorsByPowerSource(powerSource);
    }

    @GetMapping("/count")
    public ResponseEntity<GeneratorCountDto> getGenerators(@RequestParam("detailArea") String detailArea){
        GeneratorCountDto generatorCountDto = generatorSearchService.getGeneratorsCountByAreaAndPower(detailArea);

        return ResponseEntity.ok().body(generatorCountDto);
    }

    @GetMapping("/refine")
    public List<RefineGeneratorDto> getRefineGenerators(){
        try {
            return generatorSearchService.GeneratorRefine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}