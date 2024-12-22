package dev.the_moon_team.fast_docs.controllers;

import dev.the_moon_team.fast_docs.dto.DocumentBlockDto;
import dev.the_moon_team.fast_docs.entities.Document;
import dev.the_moon_team.fast_docs.entities.DocumentBlock;
import dev.the_moon_team.fast_docs.repositories.DocumentBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/documentBlocks")
public class DocumentBlockController {
    @Autowired
    DocumentBlockRepository documentBlockRepository;

    @GetMapping("/all")
    public ResponseEntity<?> read(){
        List<DocumentBlockDto> documentBlockDtos = new ArrayList<>();

        List<DocumentBlock> documents = documentBlockRepository.findAll();

        for (DocumentBlock documentBlock: documents){
            DocumentBlockDto dto = new DocumentBlockDto();

            dto.document = documentBlock.getDocument();
            dto.x = documentBlock.getX();
            dto.y = documentBlock.getY();
            dto.templateBlock = documentBlock.getTemplateBlock();
            dto.height = documentBlock.getHeight();
            dto.width = documentBlock.getWidth();
            dto.value = documentBlock.getValue();

            documentBlockDtos.add(dto);
        }
        return new ResponseEntity<>(documentBlockDtos, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> readById(@PathVariable String id){
        try {
            DocumentBlock documentBlock = documentBlockRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no such DocumentBlock"));
            DocumentBlockDto dto = new DocumentBlockDto();

            dto.document = documentBlock.getDocument();
            dto.x = documentBlock.getX();
            dto.y = documentBlock.getY();
            dto.templateBlock = documentBlock.getTemplateBlock();
            dto.height = documentBlock.getHeight();
            dto.width = documentBlock.getWidth();
            dto.value = documentBlock.getValue();

            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save") //что-то там
    public ResponseEntity<?> save(@RequestBody DocumentBlockDto documentBlockDto){
        DocumentBlock documentBlock = new DocumentBlock();

        documentBlock.setDocument(documentBlockDto.document);
        documentBlock.setTemplateBlock(documentBlockDto.templateBlock);
        documentBlock.setX(documentBlockDto.x);
        documentBlock.setY(documentBlockDto.y);
        documentBlock.setWidth(documentBlockDto.width);
        documentBlock.setHeight(documentBlockDto.height);
        documentBlock.setValue(documentBlockDto.value);

        documentBlockRepository.save(documentBlock);
        return new ResponseEntity<>(HttpStatus.OK); //что-то там
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody DocumentBlockDto newDocumentBlock,@PathVariable String id){
        DocumentBlock documentBlock = documentBlockRepository.findById(id)
                .map(documentB -> {
                    documentB.setDocument(newDocumentBlock.document);
                    documentB.setTemplateBlock(newDocumentBlock.templateBlock);
                    documentB.setX(newDocumentBlock.x);
                    documentB.setY(newDocumentBlock.y);
                    documentB.setWidth(newDocumentBlock.width);
                    documentB.setHeight(newDocumentBlock.height);
                    documentB.setValue(newDocumentBlock.value);

                    return documentB;
                }).orElseThrow(() -> new NoSuchElementException("no such DocumentBlock"));


        DocumentBlockDto dto = new DocumentBlockDto();

        dto.document = documentBlock.getDocument();
        dto.x = documentBlock.getX();
        dto.y = documentBlock.getY();
        dto.templateBlock = documentBlock.getTemplateBlock();
        dto.height = documentBlock.getHeight();
        dto.width = documentBlock.getWidth();
        dto.value = documentBlock.getValue();

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        documentBlockRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK); //что-то там

    }
}
