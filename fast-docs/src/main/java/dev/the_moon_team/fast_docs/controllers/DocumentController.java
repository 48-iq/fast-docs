package dev.the_moon_team.fast_docs.controllers;

import dev.the_moon_team.fast_docs.dto.DocumentDto;
import dev.the_moon_team.fast_docs.entities.Document;
import dev.the_moon_team.fast_docs.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    @Autowired
    DocumentRepository documentRepository;

    @GetMapping("/all")
    public ResponseEntity<?> read(){
        List<DocumentDto> documentDtos = new ArrayList<>();

        List<Document> documents = documentRepository.findAll();

        for (Document document: documents){
            DocumentDto dto = new DocumentDto();
            dto.documentBlocks = document.getDocumentBlocks();
            dto.title = document.getTitle();
            dto.template = document.getTemplate();

            documentDtos.add(dto);
        }
        return new ResponseEntity<>(documentDtos, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> readById(@PathVariable String id){
        try {
            Document document = documentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no no such Document"));

            DocumentDto dto = new DocumentDto();
            dto.documentBlocks = document.getDocumentBlocks();
            dto.title = document.getTitle();
            dto.template = document.getTemplate();

            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save") //что-то там
    public ResponseEntity<?> save(@RequestBody DocumentDto documentDto){
        Document document = new Document();

        document.setTitle(documentDto.title);
        document.setTemplate(documentDto.template);
        document.setDocumentBlocks(documentDto.documentBlocks);

        documentRepository.save(document);
        return new ResponseEntity<>(HttpStatus.OK); //что-то там
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody DocumentDto documentDto,@PathVariable String id){
        Document doc = documentRepository.findById(id)
                        .map(document -> {
                            document.setTitle(documentDto.title);
                            document.setTemplate(documentDto.template);
                            document.setDocumentBlocks(documentDto.documentBlocks);
                            return document;
                        }).orElseThrow(() -> new NoSuchElementException("no such document"));


        DocumentDto dto = new DocumentDto();
        dto.documentBlocks = doc.getDocumentBlocks();
        dto.title = doc.getTitle();
        dto.template = doc.getTemplate();

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        documentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK); //что-то там

    }
}
