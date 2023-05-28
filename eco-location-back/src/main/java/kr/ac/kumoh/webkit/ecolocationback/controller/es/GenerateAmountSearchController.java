package kr.ac.kumoh.webkit.ecolocationback.controller.es;


import kr.ac.kumoh.webkit.ecolocationback.dto.response.GenerateAmountByAreaDto;
import kr.ac.kumoh.webkit.ecolocationback.dto.response.GenerateAmountByYearDto;
import kr.ac.kumoh.webkit.ecolocationback.service.GenerateAmountSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/es/generate-amount")
public class GenerateAmountSearchController {
    private final GenerateAmountSearchService generateAmountSearchService;


    @GetMapping("/average")
    public ResponseEntity<List<GenerateAmountByYearDto>> getGenerators(@RequestParam("area") String detailArea) {
        List<GenerateAmountByYearDto> response = generateAmountSearchService.getGenerateAmountAverageByArea(detailArea);

        return ResponseEntity.ok().body(response);
    }
    @GetMapping(params = "date")
    public List<GenerateAmountByAreaDto> getGenerateAmountArea(@RequestParam("date") String date){
        try {
            return generateAmountSearchService.GenerateAmountAreaByYear(date);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
