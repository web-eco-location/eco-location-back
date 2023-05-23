package kr.ac.kumoh.webkit.ecolocationback.service;

import kr.ac.kumoh.webkit.ecolocationback.repository.AreaGeneratorSourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenerateAmountService {
    private final AreaGeneratorSourceRepository areaGeneratorSourceRepository;

    // 연도를 입력받아 지역별로 발전량을 반환하는 메소드.

    // 지역을 입력 받아 발전원 별로 발전량을 표시한다. 18~23년도별 연평균 발전량을 반환하는 메소드.

}
