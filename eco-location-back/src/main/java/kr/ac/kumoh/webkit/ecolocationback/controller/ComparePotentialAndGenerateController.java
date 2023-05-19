package kr.ac.kumoh.webkit.ecolocationback.controller;

import kr.ac.kumoh.webkit.ecolocationback.dto.response.ComparePotentialAndGenerateDto;
import kr.ac.kumoh.webkit.ecolocationback.entity.EnergyPotential;
import kr.ac.kumoh.webkit.ecolocationback.service.ComparePotentialAndGenerateService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/compare")
@RequiredArgsConstructor
public class ComparePotentialAndGenerateController {
    private final ComparePotentialAndGenerateService comparePotentialAndGenerateService;

    /**
     * @param start
     * @param end
     * @return 지역별로 해당 기간 사이의 신재생 에너지 잠재량과 발전량을 반환.
     */
    @GetMapping
    public List<ComparePotentialAndGenerateDto> getComparePotentialAndGenerateDto(
            @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
            @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end){
        return comparePotentialAndGenerateService.comparePotentialAndGenerate(start, end);
    }
}
