package kr.ac.kumoh.webkit.ecolocationback.controller;

import kr.ac.kumoh.webkit.ecolocationback.service.GenerateAmountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/generate-amount")
public class GenerateAmountController {
    private final GenerateAmountService generateAmountService;


}
