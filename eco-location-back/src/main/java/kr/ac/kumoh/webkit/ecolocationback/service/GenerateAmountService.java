package kr.ac.kumoh.webkit.ecolocationback.service;

import kr.ac.kumoh.webkit.ecolocationback.dto.response.GenerateAmountByYearDto;
import kr.ac.kumoh.webkit.ecolocationback.entity.AreaGeneratorSource;
import kr.ac.kumoh.webkit.ecolocationback.dto.response.GenerateAmountByAreaDto;
import kr.ac.kumoh.webkit.ecolocationback.repository.AreaGeneratorSourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GenerateAmountService {

    @Autowired
    private final AreaGeneratorSourceRepository areaGeneratorSourceRepository;

    // 연도를 입력받아 지역별로 발전량을 반환하는 메소드.
    @PersistenceContext
    private EntityManager entityManager;
    public List<GenerateAmountByAreaDto> GenerateAmountAreaByYear(String date){
        TypedQuery<GenerateAmountByAreaDto> query = entityManager.createQuery(
                "select NEW kr.ac.kumoh.webkit.ecolocationback.dto.response.GenerateAmountByAreaDto" +
                        "(a.area, sum(a.srcSolar) as solarAmount, sum(a.srcWind) as windAmount) " +
                        "FROM kr.ac.kumoh.webkit.ecolocationback.entity.AreaGeneratorSource a " +
                        "where a.date like '" + date + "%' group by a.area"
        , GenerateAmountByAreaDto.class);
        List<GenerateAmountByAreaDto> resultList = query.getResultList();

        if (resultList.size() == 0) throw new NoSuchElementException("해당하는 년도의 데이터가 없습니다");

        return resultList;
    }


    // 지역을 입력 받아 발전원 별로 발전량을 표시한다. 18~23년도별 연평균 발전량을 반환하는 메소드.
    public List<GenerateAmountByYearDto> getGenerateAmountAverageByArea(String areaName) {
        List<AreaGeneratorSource> sourceList = areaGeneratorSourceRepository.findByArea(areaName);
        if (sourceList.size() == 0) throw new NoSuchElementException("해당하는 지역의 데이터가 없습니다");

        List<GenerateAmountByYearDto> result = GenerateAmountByYearDto.calculateAveragesByYear(sourceList);

        return result;
    }
}
