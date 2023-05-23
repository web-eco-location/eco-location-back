package kr.ac.kumoh.webkit.ecolocationback.service;

import java.util.ArrayList;
import java.util.List;

import kr.ac.kumoh.webkit.ecolocationback.dto.response.GeneratorCountDto;
import kr.ac.kumoh.webkit.ecolocationback.dto.response.RefineGeneratorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kumoh.webkit.ecolocationback.entity.Generator;
import kr.ac.kumoh.webkit.ecolocationback.repository.GeneratorRepository;

import javax.persistence.*;

@Service
public class GeneratorService {

    @Autowired
    private GeneratorRepository generatorRepository;

    public List<Generator> getAllGenerators() {
        return generatorRepository.findAll();
    }

    public Generator saveGenerator(Generator generator) {
        return generatorRepository.save(generator);
    }

    public List<Generator> saveGenerators(List<Generator> generators) {
        return generatorRepository.saveAll(generators);
    }
    
    public List<Generator> getGeneratorsByDetailArea(String detailArea){
    	return generatorRepository.findByDetailArea(detailArea);
    }
    
    public List<Generator> getGeneratorsByPowerSource(String powerSource){
    	return generatorRepository.findByPowerSource(powerSource);
    }


    public GeneratorCountDto getGeneratorsCountByAreaAndPower(String detailArea) {
        List<Generator> generatorList = generatorRepository.findByDetailArea(detailArea);

        GeneratorCountDto generatorCountDto = new GeneratorCountDto();
        for (Generator generator : generatorList) {
            generatorCountDto.count(generator);
        }

        return generatorCountDto;
    }

    @PersistenceContext
    private EntityManager em;
    public List<RefineGeneratorDto> GeneratorRefine(){
        TypedQuery<RefineGeneratorDto> query = em.createQuery(
                "SELECT NEW kr.ac.kumoh.webkit.ecolocationback.dto.response.RefineGeneratorDto" +
                        "(g.wideArea, g.powerSource, SUM(g.generateAmount) as amount) FROM Generator g " +
                        "where g.powerSource in ('바이오에너지', '수력에너지', '연료전지', '태양에너지', '풍력에너지')" +
                        " GROUP BY g.wideArea, g.powerSource",
                RefineGeneratorDto.class);
        List<RefineGeneratorDto> resultList = query.getResultList();

        return resultList;
    }
}
