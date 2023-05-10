package kr.ac.kumoh.webkit.ecolocationback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kumoh.webkit.ecolocationback.entity.Generator;
import kr.ac.kumoh.webkit.ecolocationback.repository.GeneratorRepository;

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
}
