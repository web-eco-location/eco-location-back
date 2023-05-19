package kr.ac.kumoh.webkit.ecolocationback.service;

import kr.ac.kumoh.webkit.ecolocationback.dto.response.ComparePotentialAndGenerateDto;
import kr.ac.kumoh.webkit.ecolocationback.entity.AreaGeneratorSource;
import kr.ac.kumoh.webkit.ecolocationback.entity.EnergyPotential;
import kr.ac.kumoh.webkit.ecolocationback.repository.AreaGeneratorSourceRepository;
import kr.ac.kumoh.webkit.ecolocationback.repository.EnergyPotentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComparePotentialAndGenerateService {
    private final AreaGeneratorSourceRepository areaGeneratorSourceRepository;
    private final EnergyPotentialRepository energyPotentialRepository;

    public List<ComparePotentialAndGenerateDto> comparePotentialAndGenerate(LocalDateTime startTime, LocalDateTime endTime) {
        List<EnergyPotential> energyPotentialList = energyPotentialRepository.findByForecastTimeBetween(startTime, endTime);
        LocalDate startLocalDate = startTime.toLocalDate();
        LocalDate endLocalDate = endTime.toLocalDate();
        List<AreaGeneratorSource> areaGeneratorSourceList = areaGeneratorSourceRepository.findByDateBetween(startLocalDate, endLocalDate);

        List<ComparePotentialAndGenerateDto> response = mapToDtoList(energyPotentialList, areaGeneratorSourceList);

        return response;
    }

    /**
     * @param energyPotentials
     * @param areaGeneratorSources
     * @return
     * @description 리스트를 가져와 해당하는 지역으로 그룹화 하고 dto변환하는 함수
     */
    public static List<ComparePotentialAndGenerateDto> mapToDtoList(
            List<EnergyPotential> energyPotentials, List<AreaGeneratorSource> areaGeneratorSources){

        // 지역별로 그룹화된 EnergyPotential 맵 생성
        Map<String, List<EnergyPotential>> groupedEnergyPotentials = energyPotentials.stream()
                .collect(Collectors.groupingBy(EnergyPotential::getAreaName));
        // 지역별로 그룹화 된 AreaGenerator 맵 생성
        Map<String, List<AreaGeneratorSource>> groupedAreaGeneratorSources = areaGeneratorSources.stream()
                .collect(Collectors.groupingBy(AreaGeneratorSource::getArea));

        List<ComparePotentialAndGenerateDto> dtos = generateDtos(groupedEnergyPotentials, groupedAreaGeneratorSources);

        return dtos;
    }

    /**
     * @param groupedEnergyPotentials
     * @param groupedAreaGeneratorSources
     * @return
     * @description Map들을 가져와 지역에 맞추어 DtoList를 생성하는 함수
     */
    public static List<ComparePotentialAndGenerateDto> generateDtos(
            Map<String, List<EnergyPotential>> groupedEnergyPotentials,
            Map<String, List<AreaGeneratorSource>> groupedAreaGeneratorSources) {

        List<ComparePotentialAndGenerateDto> dtos = new ArrayList<>();

        // 강원도
        String areaName = "강원도";
        List<String> includedAreas = List.of("강원");

        double potentialAmount = calculatePotentialAmount(groupedEnergyPotentials, areaName);
        double generateAmount = calculateGenerateAmount(groupedAreaGeneratorSources, includedAreas);

        ComparePotentialAndGenerateDto dto = new ComparePotentialAndGenerateDto(areaName, potentialAmount, generateAmount);
        dtos.add(dto);

        // 경기도
        areaName = "경기도";
        includedAreas = List.of("서울", "세종", "인천", "경기");

        potentialAmount = calculatePotentialAmount(groupedEnergyPotentials, areaName);
        generateAmount = calculateGenerateAmount(groupedAreaGeneratorSources, includedAreas);

        dto = new ComparePotentialAndGenerateDto(areaName, potentialAmount, generateAmount);
        dtos.add(dto);

        // 충청도
        areaName = "충청도";
        includedAreas = List.of("대전", "충북", "충남");

        potentialAmount = calculatePotentialAmount(groupedEnergyPotentials, areaName);
        generateAmount = calculateGenerateAmount(groupedAreaGeneratorSources, includedAreas);

        dto = new ComparePotentialAndGenerateDto(areaName, potentialAmount, generateAmount);
        dtos.add(dto);

        // 경상북도
        areaName = "경상북도";
        includedAreas = List.of("대구", "경북");

        potentialAmount = calculatePotentialAmount(groupedEnergyPotentials, areaName);
        generateAmount = calculateGenerateAmount(groupedAreaGeneratorSources, includedAreas);

        dto = new ComparePotentialAndGenerateDto(areaName, potentialAmount, generateAmount);
        dtos.add(dto);

        // 경상남도
        areaName = "경상남도";
        includedAreas = List.of("부산", "울산", "경남");

        potentialAmount = calculatePotentialAmount(groupedEnergyPotentials, areaName);
        generateAmount = calculateGenerateAmount(groupedAreaGeneratorSources, includedAreas);

        dto = new ComparePotentialAndGenerateDto(areaName, potentialAmount, generateAmount);
        dtos.add(dto);

        // 전라북도
        areaName = "전라북도";
        includedAreas = List.of("전북");

        potentialAmount = calculatePotentialAmount(groupedEnergyPotentials, areaName);
        generateAmount = calculateGenerateAmount(groupedAreaGeneratorSources, includedAreas);

        dto = new ComparePotentialAndGenerateDto(areaName, potentialAmount, generateAmount);
        dtos.add(dto);

        // 전라남도
        areaName = "전라남도";
        includedAreas = List.of("광주", "전남");

        potentialAmount = calculatePotentialAmount(groupedEnergyPotentials, areaName);
        generateAmount = calculateGenerateAmount(groupedAreaGeneratorSources, includedAreas);

        dto = new ComparePotentialAndGenerateDto(areaName, potentialAmount, generateAmount);
        dtos.add(dto);

        // 제주도
        areaName = "제주도";
        includedAreas = List.of("제주");

        potentialAmount = calculatePotentialAmount(groupedEnergyPotentials, areaName);
        generateAmount = calculateGenerateAmount(groupedAreaGeneratorSources, includedAreas);

        dto = new ComparePotentialAndGenerateDto(areaName, potentialAmount, generateAmount);
        dtos.add(dto);

        return dtos;
    }

    /**
     * @param groupedEnergyPotentials
     * @param areaName
     * @return
     * @description 해당하는 area의 energyPotential을 가져와서 잠재량 더하여 반환하는 메소드
     */
    private static double calculatePotentialAmount(
            Map<String, List<EnergyPotential>> groupedEnergyPotentials,
            String areaName) {

        double potentialAmount = 0;
        String formattedNumber = "";

        List<EnergyPotential> energyPotentials = groupedEnergyPotentials.get(areaName);
        if (energyPotentials != null) {
            potentialAmount += energyPotentials.stream()
                    .mapToDouble(EnergyPotential::getForecastEnergyPotential)
                    .sum();
        }
        BigDecimal bigDecimal = BigDecimal.valueOf(potentialAmount);
        formattedNumber = bigDecimal.toPlainString();

        return Double.parseDouble(formattedNumber);
    }

    /**
     * @param groupedAreaGeneratorSources
     * @param includedAreas
     * @return
     * @description 해당하는 includedAreas의 energyPotential을 가져와서 잠재량 더하여 반환하는 메소드
     */
    private static double calculateGenerateAmount(
            Map<String, List<AreaGeneratorSource>> groupedAreaGeneratorSources,
            List<String> includedAreas) {

        double generateAmount = 0;
        String formattedNumber = "";

        for (String area : includedAreas) {
            List<AreaGeneratorSource> areaGeneratorSources = groupedAreaGeneratorSources.get(area);
            if (areaGeneratorSources != null) {
                generateAmount = areaGeneratorSources.stream()
                        .mapToDouble(ag -> Double.parseDouble(ag.getSrcRecycleSum())).sum();
            }
            BigDecimal bigDecimal = BigDecimal.valueOf(generateAmount);
            formattedNumber = bigDecimal.toPlainString();
        }

        return Double.parseDouble(formattedNumber);
    }
}
