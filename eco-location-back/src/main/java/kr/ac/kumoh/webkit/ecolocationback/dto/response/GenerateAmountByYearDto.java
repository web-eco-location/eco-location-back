package kr.ac.kumoh.webkit.ecolocationback.dto.response;

import kr.ac.kumoh.webkit.ecolocationback.entity.AreaGeneratorSource;
import lombok.Data;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class GenerateAmountByYearDto {
    private int year;
    private double generateSolarAmountAverage;
    private double generateWindAmountAverage;

    public GenerateAmountByYearDto(Year year, List<AreaGeneratorSource> sources) {
        this.year = year.getValue();
        calculateAverages(sources);
    }

    private void calculateAverages(List<AreaGeneratorSource> sources) {
        double solarSum = 0.0;
        double windSum = 0.0;

        for (AreaGeneratorSource source : sources) {
            solarSum += source.getSrcSolar();
            windSum += source.getSrcWind();
        }

        this.generateSolarAmountAverage = solarSum / sources.size();
        this.generateWindAmountAverage = windSum / sources.size();
    }

    public static List<GenerateAmountByYearDto> calculateAveragesByYear(List<AreaGeneratorSource> areaGeneratorSources) {
        // 날짜별로 AreaGeneratorSource를 그룹화
        Map<Year, List<AreaGeneratorSource>> groupedSources = areaGeneratorSources.stream()
                .collect(Collectors.groupingBy(source -> Year.of(LocalDate.parse(source.getDate().toString()).getYear())));

        List<GenerateAmountByYearDto> result = new ArrayList<>();

        // 각 년도별로 평균 계산하여 GenerateAmountByYearDto 객체 생성
        for (Map.Entry<Year, List<AreaGeneratorSource>> entry : groupedSources.entrySet()) {
            Year year = entry.getKey();
            List<AreaGeneratorSource> sources = entry.getValue();

            GenerateAmountByYearDto dto = new GenerateAmountByYearDto(year, sources);
            result.add(dto);
        }

        return result;
    }
}
