package kr.ac.kumoh.webkit.ecolocationback.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import kr.ac.kumoh.webkit.ecolocationback.dto.AreaGeneratorSourceDto;
import kr.ac.kumoh.webkit.ecolocationback.dto.EnergyPotentialDto;
import kr.ac.kumoh.webkit.ecolocationback.dto.GeneratorDto;
import kr.ac.kumoh.webkit.ecolocationback.entity.AreaGeneratorSource;
import kr.ac.kumoh.webkit.ecolocationback.entity.EnergyPotential;
import kr.ac.kumoh.webkit.ecolocationback.entity.Generator;
import kr.ac.kumoh.webkit.ecolocationback.repository.AreaGeneratorSourceRepository;
import kr.ac.kumoh.webkit.ecolocationback.repository.EnergyPotentialRepository;
import kr.ac.kumoh.webkit.ecolocationback.repository.GeneratorRepository;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseInitializerService {
    private final AreaGeneratorSourceRepository areaGeneratorSourceRepository;
    private final EnergyPotentialRepository energyPotentialRepository;
    private final GeneratorRepository generatorRepository;

    public DatabaseInitializerService(AreaGeneratorSourceRepository areaGeneratorSourceRepository, EnergyPotentialRepository energyPotentialRepository,
                                      GeneratorRepository generatorRepository) {
        this.areaGeneratorSourceRepository = areaGeneratorSourceRepository;
        this.energyPotentialRepository = energyPotentialRepository;
        this.generatorRepository = generatorRepository;
    }

    public void initializeDatabase() {
        clearRepositories();

        inputData("generator", "src/main/resources/csv/generators.csv");
        inputData("energyPotential", "src/main/resources/csv/totalEnergyPotential.csv");
        inputData("areaGeneratorSource", "src/main/resources/csv/AreaGeneratorSource.csv");
    }
    private void clearRepositories() {
        // 모든 리포지토리의 데이터 삭제
        generatorRepository.deleteAllInBatch();
        energyPotentialRepository.deleteAllInBatch();
        areaGeneratorSourceRepository.deleteAllInBatch();
    }


    public void inputData(String jobId, String filePath) {
        try {
            CSVReader csvReader = new CSVReaderBuilder(new FileReader(filePath)).withSkipLines(1).build();
            String[] line;
            List<GeneratorDto> generatorDtos = new ArrayList<>();
            List<EnergyPotentialDto> energyPotentialDtos = new ArrayList<>();
            List<AreaGeneratorSourceDto> areaGeneratorSourceDtos = new ArrayList<>();

            // CSV 파일의 각 라인을 반복하여 데이터 추출
            while ((line = csvReader.readNext()) != null) {
                if (jobId.equals("generator")) {
                    GeneratorDto generatorDto = new GeneratorDto();
                    generatorDto.setCompanyName(line[0]);
                    generatorDto.setGeneratorName(line[1]);
                    generatorDto.setGeneratorNum(line[2]);
                    generatorDto.setGenerateAmount(Double.parseDouble(line[3]));
                    generatorDto.setMemberType(line[4]);
                    generatorDto.setPowerSupply(line[5]);
                    generatorDto.setPowerSource(line[6]);
                    generatorDto.setGenerationType(line[7]);
                    generatorDto.setBusinessType(line[8]);
                    generatorDto.setWideArea(line[9]);
                    generatorDto.setDetailArea(line[10]);

                    generatorDtos.add(generatorDto);
                } else if (jobId.equals("energyPotential")) {
                    EnergyPotentialDto energyPotentialDto = new EnergyPotentialDto();
                    energyPotentialDto.setPowerType(line[0]);
                    energyPotentialDto.setAreaName(line[1]);
                    energyPotentialDto.setCreateTime_s(line[2]);
                    energyPotentialDto.setForecastTime_s(line[3]);
                    energyPotentialDto.setLeadTime(Long.parseLong(line[4]));
                    energyPotentialDto.setForecastEnergyPotential(Double.parseDouble(line[5]));
                    energyPotentialDto.setForecastCapacity(Double.parseDouble(line[6]));

                    energyPotentialDtos.add(energyPotentialDto);
                } else if (jobId.equals("areaGeneratorSource")) {
                    AreaGeneratorSourceDto areaGeneratorSourceDto = new AreaGeneratorSourceDto();
                    areaGeneratorSourceDto.setDate(line[0]);
                    areaGeneratorSourceDto.setArea(line[1]);
                    areaGeneratorSourceDto.setSrcNucl(Double.parseDouble(line[2]));
                    areaGeneratorSourceDto.setSrcBcoal(Double.parseDouble(line[3]));
                    areaGeneratorSourceDto.setSrcHcoal(Double.parseDouble(line[4]));
                    areaGeneratorSourceDto.setSrcOil(Double.parseDouble(line[5]));
                    areaGeneratorSourceDto.setSrcLng(Double.parseDouble(line[6]));
                    areaGeneratorSourceDto.setSrcPump(Double.parseDouble(line[7]));
                    areaGeneratorSourceDto.setSrcFuelcell(Double.parseDouble(line[8]));
                    areaGeneratorSourceDto.setSrcCoalgas(Double.parseDouble(line[9]));
                    areaGeneratorSourceDto.setSrcSolar(Double.parseDouble(line[10]));
                    areaGeneratorSourceDto.setSrcWind(Double.parseDouble(line[11]));
                    areaGeneratorSourceDto.setSrcWater(Double.parseDouble(line[12]));
                    areaGeneratorSourceDto.setSrcSea(Double.parseDouble(line[13]));
                    areaGeneratorSourceDto.setSrcBio(Double.parseDouble(line[14]));
                    areaGeneratorSourceDto.setSrcWaste(Double.parseDouble(line[15]));
                    areaGeneratorSourceDto.setSrcRecycleSum(Double.parseDouble(line[16]));
                    areaGeneratorSourceDto.setSrcOther(Double.parseDouble(line[17]));
                    areaGeneratorSourceDto.setSrcAll(Double.parseDouble(line[18]));
                    areaGeneratorSourceDto.setRecyclePercent(Double.parseDouble(line[19]));

                    areaGeneratorSourceDtos.add(areaGeneratorSourceDto);
                }
            }

            csvReader.close();

            // DTO 객체를 해당 리포지토리에 저장
            if (jobId.equals("generator")) {
                List<Generator> generators = convertToGenerators(generatorDtos);
                generatorRepository.saveAll(generators);
            }
            else if (jobId.equals("energyPotential")) {
                List<EnergyPotential> energyPotentials = convertToEnergyPotentials(energyPotentialDtos);
                energyPotentialRepository.saveAll(energyPotentials);
            } else if (jobId.equals("areaGeneratorSource")) {
                List<AreaGeneratorSource> areaGeneratorSources = convertToAreaGeneratorSources(areaGeneratorSourceDtos);
                areaGeneratorSourceRepository.saveAll(areaGeneratorSources);
            }
            } catch(IOException | CsvValidationException e){
                e.printStackTrace();
            }
        }


    private List<Generator> convertToGenerators(List<GeneratorDto> generatorDtos) {
        List<Generator> generators = new ArrayList<>();
        for (GeneratorDto generatorDto : generatorDtos) {
            Generator generator = new Generator();
            generator.setCompanyName(generatorDto.getCompanyName());
            generator.setGeneratorName(generatorDto.getGeneratorName());
            generator.setGeneratorNum(generatorDto.getGeneratorNum());
            generator.setGenerateAmount(generatorDto.getGenerateAmount());
            generator.setMemberType(generatorDto.getMemberType());
            generator.setPowerSupply(generatorDto.getPowerSupply());
            generator.setPowerSource(generatorDto.getPowerSource());
            generator.setGenerationType(generatorDto.getGenerationType());
            generator.setBusinessType(generatorDto.getBusinessType());
            generator.setWideArea(generatorDto.getWideArea());
            generator.setDetailArea(generatorDto.getDetailArea());

            generators.add(generator);
        }
        return generators;
    }
    private List<EnergyPotential> convertToEnergyPotentials(List<EnergyPotentialDto> energyPotentialDtos) {
        List<EnergyPotential> energyPotentials = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");

        for(EnergyPotentialDto energyPotentialDto : energyPotentialDtos){
            EnergyPotential energyPotential = new EnergyPotential();
            energyPotential.setPowerType(energyPotentialDto.getPowerType());
            energyPotential.setAreaName(energyPotentialDto.getAreaName());
            energyPotential.setCreateTime(LocalDateTime.parse(energyPotentialDto.getCreateTime_s(), formatter));
            energyPotential.setForecastTime(LocalDateTime.parse(energyPotentialDto.getForecastTime_s(), formatter));
            energyPotential.setLeadTime(energyPotentialDto.getLeadTime());
            energyPotential.setForecastEnergyPotential(energyPotentialDto.getForecastEnergyPotential());
            energyPotential.setForecastCapacity(energyPotentialDto.getForecastCapacity());

            energyPotentials.add(energyPotential);
        }
        return energyPotentials;
    }

    private List<AreaGeneratorSource> convertToAreaGeneratorSources(List<AreaGeneratorSourceDto> areaGeneratorSourceDtos) {
        List<AreaGeneratorSource> areaGeneratorSources = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");

        for(AreaGeneratorSourceDto areaGeneratorDto : areaGeneratorSourceDtos){
            AreaGeneratorSource areaGeneratorSource = new AreaGeneratorSource();
            areaGeneratorSource.setDate(LocalDate.parse(areaGeneratorDto.getDate(), formatter));
            areaGeneratorSource.setArea(areaGeneratorDto.getArea());
            areaGeneratorSource.setSrcNucl(areaGeneratorDto.getSrcNucl());
            areaGeneratorSource.setSrcBcoal(areaGeneratorDto.getSrcBcoal());
            areaGeneratorSource.setSrcHcoal(areaGeneratorDto.getSrcHcoal());
            areaGeneratorSource.setSrcOil(areaGeneratorDto.getSrcOil());
            areaGeneratorSource.setSrcLng(areaGeneratorDto.getSrcLng());
            areaGeneratorSource.setSrcPump(areaGeneratorDto.getSrcPump());
            areaGeneratorSource.setSrcFuelcell(areaGeneratorDto.getSrcFuelcell());
            areaGeneratorSource.setSrcCoalgas(areaGeneratorDto.getSrcCoalgas());
            areaGeneratorSource.setSrcSolar(areaGeneratorDto.getSrcSolar());
            areaGeneratorSource.setSrcWind(areaGeneratorDto.getSrcWind());
            areaGeneratorSource.setSrcWater(areaGeneratorDto.getSrcWater());
            areaGeneratorSource.setSrcSea(areaGeneratorDto.getSrcSea());
            areaGeneratorSource.setSrcBio(areaGeneratorDto.getSrcBio());
            areaGeneratorSource.setSrcWaste(areaGeneratorDto.getSrcWaste());
            areaGeneratorSource.setSrcRecycleSum(areaGeneratorDto.getSrcRecycleSum());
            areaGeneratorSource.setSrcOther(areaGeneratorDto.getSrcOther());
            areaGeneratorSource.setSrcAll(areaGeneratorDto.getSrcAll());
            areaGeneratorSource.setRecyclePercent(areaGeneratorDto.getRecyclePercent());
            areaGeneratorSources.add(areaGeneratorSource);
        }
        return areaGeneratorSources;
    }
}
