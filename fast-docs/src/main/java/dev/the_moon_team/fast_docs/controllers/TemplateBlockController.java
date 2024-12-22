package dev.the_moon_team.fast_docs.controllers;

import dev.the_moon_team.fast_docs.entities.Document;
import dev.the_moon_team.fast_docs.entities.TemplateBlock;
import dev.the_moon_team.fast_docs.repositories.TemplateBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class TemplateBlockController {
    @Autowired
    TemplateBlockRepository templateBlockRepository;

    @GetMapping("")
    public List<TemplateBlock> read(){
        return templateBlockRepository.findAll();
    }
    @GetMapping("")
    public TemplateBlock readById(String id){
        return templateBlockRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no such TemplateBlock"));
    }
    @PostMapping("") //что-то там
    public String save(TemplateBlock templateBlock){
        templateBlockRepository.save(templateBlock);
        return ""; //что-то там
    }

    @PostMapping("")
    public String update(TemplateBlock newTemplateBlock, String id){
        templateBlockRepository.findById(id)
                .map(templateBlock -> {
                    templateBlock.setType(newTemplateBlock.getType());
                    templateBlock.setTemplate(newTemplateBlock.getTemplate());
                    templateBlock.setDefaultValue(newTemplateBlock.getDefaultValue());

                    return templateBlock;
                }).orElseThrow(() -> new NoSuchElementException("no such TemplateBlock"));
        return "";
    }
    @PostMapping("")
    public String delete(String id){
        templateBlockRepository.deleteById(id);
        return "";
    }
}
