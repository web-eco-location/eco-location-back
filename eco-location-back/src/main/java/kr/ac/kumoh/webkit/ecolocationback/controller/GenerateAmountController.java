package kr.ac.kumoh.webkit.ecolocationback.controller;


import kr.ac.kumoh.webkit.ecolocationback.dto.response.GenerateAmountByYearDto;
import kr.ac.kumoh.webkit.ecolocationback.service.GenerateAmountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import kr.ac.kumoh.webkit.ecolocationback.dto.response.GenerateAmountByAreaDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/generate-amount")
public class GenerateAmountController {
    private final GenerateAmountService generateAmountService;


    @GetMapping("/average")
    public ResponseEntity<List<GenerateAmountByYearDto>> getGenerators(@RequestParam("area") String detailArea){
        List<GenerateAmountByYearDto> response = generateAmountService.getGenerateAmountAverageByArea(detailArea);

        return ResponseEntity.ok().body(response);

    @GetMapping(params = "date")
    public List<GenerateAmountByAreaDto> getGenerateAmountArea(@RequestParam("date") String date){
        return generateAmountService.GenerateAmountAreaByYear(date);

    }
}
