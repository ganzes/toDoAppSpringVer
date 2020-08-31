package io.github.ganzes.lang;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LangService {
    private final LangRepository repository;

    public LangService(LangRepository repository){
        this.repository = repository;
    }

    public List<LangDTO> findAll (){
        List<Lang> findAllList = repository.findAll();
        return findAllList.stream()
        .map(LangDTO::new)
        .collect(Collectors.toList());
    }
}
