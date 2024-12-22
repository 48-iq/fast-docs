package dev.the_moon_team.fast_docs.controllers;

import dev.the_moon_team.fast_docs.dto.DocumentDto;
import dev.the_moon_team.fast_docs.dto.TemplateBlockDto;
import dev.the_moon_team.fast_docs.dto.TemplateDto;
import dev.the_moon_team.fast_docs.entities.Document;
import dev.the_moon_team.fast_docs.entities.Template;
import dev.the_moon_team.fast_docs.entities.TemplateBlock;
import dev.the_moon_team.fast_docs.repositories.TemplateBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/templateBlock")
public class TemplateBlockController {
    @Autowired
    TemplateBlockRepository templateBlockRepository;

    @GetMapping("/all")
    public ResponseEntity<?> read(){
        List<TemplateBlockDto> templateBlockDtos = new ArrayList<>();

        List<TemplateBlock> templateBlocks = templateBlockRepository.findAll();

        for (TemplateBlock templateBlock: templateBlocks){
            TemplateBlockDto dto = new TemplateBlockDto();
            dto.id = templateBlock.getId();
            dto.defaultValue = templateBlock.getDefaultValue();
            dto.type = templateBlock.getType();
            dto.template = templateBlock.getTemplate();

            templateBlockDtos.add(dto);
        }
        return new ResponseEntity<>(templateBlockDtos, HttpStatus.OK);
    }
    @GetMapping("/byid")
    public ResponseEntity<?> readById(String id){
        TemplateBlock templateBlock = templateBlockRepository.findById(id).get();

        TemplateBlockDto dto = new TemplateBlockDto();
        dto.id = templateBlock.getId();
        dto.defaultValue = templateBlock.getDefaultValue();
        dto.type = templateBlock.getType();
        dto.template = templateBlock.getTemplate();

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/save") //что-то там
    public ResponseEntity<?> save(TemplateBlockDto templateBlockDto){
        TemplateBlock templateBlock = new TemplateBlock();


        templateBlock.setTemplate(templateBlockDto.template);
        templateBlock.setDefaultValue(templateBlockDto.defaultValue);
        templateBlock.setType(templateBlockDto.type);

        templateBlockRepository.save(templateBlock);
        return new ResponseEntity<>(HttpStatus.OK); //что-то там
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(TemplateBlockDto templateBlockDto, String id){
        TemplateBlock templateBlock = templateBlockRepository.findById(id)
                .map(templateBlock1 -> {
                    templateBlock1.setTemplate(templateBlockDto.template);
                    templateBlock1.setDefaultValue(templateBlockDto.defaultValue);
                    templateBlock1.setType(templateBlockDto.type);
                    return templateBlock1;
                }).orElseThrow(() -> new NoSuchElementException("no such templateBlock"));


        TemplateBlockDto dto = new TemplateBlockDto();
        dto.id = templateBlock.getId();
        dto.template = templateBlock.getTemplate();
        dto.type = templateBlock.getType();
        dto.template = templateBlock.getTemplate();

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PostMapping("/delete")
    public ResponseEntity<?> delete(String id){
        templateBlockRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK); //что-то там

    }
}
