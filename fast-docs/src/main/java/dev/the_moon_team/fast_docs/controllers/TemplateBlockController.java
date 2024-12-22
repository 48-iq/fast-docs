package dev.the_moon_team.fast_docs.controllers;

import dev.the_moon_team.fast_docs.dto.templateBlock.TemplateBlockAddDto;
import dev.the_moon_team.fast_docs.dto.templateBlock.TemplateBlockReadDto;
import dev.the_moon_team.fast_docs.dto.templateBlock.TemplateBlockUpdateDto;
import dev.the_moon_team.fast_docs.entities.TemplateBlock;
import dev.the_moon_team.fast_docs.repositories.TemplateBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<TemplateBlockReadDto> templateBlockDtos = new ArrayList<>();

        List<TemplateBlock> templateBlocks = templateBlockRepository.findAll();

        for (TemplateBlock templateBlock: templateBlocks){
            TemplateBlockReadDto dto = new TemplateBlockReadDto();
            dto.defaultValue = templateBlock.getDefaultValue();
            dto.type = templateBlock.getType();
            dto.template = templateBlock.getTemplate();

            templateBlockDtos.add(dto);
        }
        return new ResponseEntity<>(templateBlockDtos, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> readById(@PathVariable String id){
        try {
            TemplateBlock templateBlock = templateBlockRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no no such TemplateBlock"));

            TemplateBlockReadDto dto = new TemplateBlockReadDto();
            dto.defaultValue = templateBlock.getDefaultValue();
            dto.type = templateBlock.getType();
            dto.template = templateBlock.getTemplate();

            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save") //что-то там
    public ResponseEntity<?> save(@RequestBody TemplateBlockAddDto templateBlockDto){
        TemplateBlock templateBlock = new TemplateBlock();


        templateBlock.setTemplate(templateBlockDto.template);
        templateBlock.setDefaultValue(templateBlockDto.defaultValue);
        templateBlock.setType(templateBlockDto.type);

        templateBlockRepository.save(templateBlock);
        return new ResponseEntity<>(HttpStatus.OK); //что-то там
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody TemplateBlockUpdateDto templateBlockDto, @PathVariable String id){
        TemplateBlock templateBlock = templateBlockRepository.findById(id)
                .map(templateBlock1 -> {
                    templateBlock1.setTemplate(templateBlockDto.template);
                    templateBlock1.setDefaultValue(templateBlockDto.defaultValue);
                    templateBlock1.setType(templateBlockDto.type);
                    return templateBlock1;
                }).orElseThrow(() -> new NoSuchElementException("no such templateBlock"));


        TemplateBlockUpdateDto dto = new TemplateBlockUpdateDto();
        dto.template = templateBlock.getTemplate();
        dto.type = templateBlock.getType();
        dto.template = templateBlock.getTemplate();

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        templateBlockRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK); //что-то там

    }
}
