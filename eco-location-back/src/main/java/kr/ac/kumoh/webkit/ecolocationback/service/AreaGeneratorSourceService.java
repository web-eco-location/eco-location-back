package kr.ac.kumoh.webkit.ecolocationback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kumoh.webkit.ecolocationback.entity.AreaGeneratorSource;
import kr.ac.kumoh.webkit.ecolocationback.repository.AreaGeneratorSourceRepository;

@Service
public class AreaGeneratorSourceService {

    @Autowired
    private AreaGeneratorSourceRepository areaGeneratorSourceRepository;

    public AreaGeneratorSource saveAreaGeneratorSource(AreaGeneratorSource areaGeneratorSource) {
        return areaGeneratorSourceRepository.save(areaGeneratorSource);
    }

    public List<AreaGeneratorSource> saveAreaGeneratorSources(List<AreaGeneratorSource> areaGeneratorSource) {
        return areaGeneratorSourceRepository.saveAll(areaGeneratorSource);
    }
    
    public List<AreaGeneratorSource> getAreaGeneratorSourcesByArea(String Area) {
    	return areaGeneratorSourceRepository.findByArea(Area);
    }
    
    public List<AreaGeneratorSource> getAreaGeneratorSourcesByTime(String Time) {
    	return areaGeneratorSourceRepository.findByTime(Time);
    }
}
