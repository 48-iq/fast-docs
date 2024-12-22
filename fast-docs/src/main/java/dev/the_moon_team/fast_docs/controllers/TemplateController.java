package dev.the_moon_team.fast_docs.controllers;

import dev.the_moon_team.fast_docs.entities.Document;
import dev.the_moon_team.fast_docs.entities.Template;
import dev.the_moon_team.fast_docs.repositories.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class TemplateController {
    @Autowired
    TemplateRepository templateRepository;

    @GetMapping("")
    public List<Template> read(){
        return templateRepository.findAll();
    }
    @GetMapping("")
    public Template readById(String id){
        return templateRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no such Template"));
    }
    @PostMapping("") //что-то там
    public String save(Template template){
        templateRepository.save(template);
        return ""; //что-то там
    }

    @PostMapping("")
    public String update(Template newTemplate, String id){
        templateRepository.findById(id)
                .map(template -> {
                    template.setTitle(newTemplate.getTitle());
                    // это вроде не надо
/*                    template.setDocuments(newTemplate.getDocuments());
                    template.setBlocks(newTemplate.getBlocks());*/

                    return template;
                }).orElseThrow(() -> new NoSuchElementException("no such template"));
        return "";
    }
    @PostMapping("")
    public String delete(String id){
        templateRepository.deleteById(id);
        return "";
    }
}
