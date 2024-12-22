package dev.the_moon_team.fast_docs.controllers;

import dev.the_moon_team.fast_docs.dto.TemplateDto;
import dev.the_moon_team.fast_docs.entities.Template;
import dev.the_moon_team.fast_docs.repositories.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/template")
public class TemplateController {
    @Autowired
    TemplateRepository templateRepository;

    @GetMapping("/all")
    public ResponseEntity<?> read(){
        List<TemplateDto> templateDtos = new ArrayList<>();

        List<Template> templates = templateRepository.findAll();

        for (Template document: templates){
            TemplateDto dto = new TemplateDto();
            dto.title = document.getTitle();

            templateDtos.add(dto);
        }
        return new ResponseEntity<>(templateDtos, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> readById(@PathVariable String id){
        try {
            Template template = templateRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no no such Template"));

            TemplateDto dto = new TemplateDto();
            dto.title = template.getTitle();

            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save") //что-то там
    public ResponseEntity<?> save(@RequestBody TemplateDto templateDto){
        Template template = new Template();

        template.setTitle(templateDto.title);

        templateRepository.save(template);
        return new ResponseEntity<>(HttpStatus.OK); //что-то там
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody TemplateDto templateDto,@PathVariable String id){
        Template template = templateRepository.findById(id)
                .map(template1 -> {
                    template1.setTitle(templateDto.title);

                    return template1;
                }).orElseThrow(() -> new NoSuchElementException("no such Template"));


        TemplateDto dto = new TemplateDto();
        dto.title = template.getTitle();

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        templateRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK); //что-то там

    }
}
