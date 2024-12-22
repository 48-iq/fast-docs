package dev.the_moon_team.fast_docs.controllers;

import dev.the_moon_team.fast_docs.entities.Document;
import dev.the_moon_team.fast_docs.entities.DocumentBlock;
import dev.the_moon_team.fast_docs.repositories.DocumentBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class DocumentBlockController {
    @Autowired
    DocumentBlockRepository documentBlockRepository;

    @GetMapping("")
    public List<DocumentBlock> read(){
        return documentBlockRepository.findAll();
    }
    @GetMapping("")
    public DocumentBlock readById(String id){
        return documentBlockRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no such DocumentBlock"));
    }
    @PostMapping("") //что-то там
    public String save(DocumentBlock documentBlock){
        documentBlockRepository.save(documentBlock);
        return ""; //что-то там
    }

    @PostMapping("")
    public String update(DocumentBlock newDocumentBlock, String id){
        documentBlockRepository.findById(id)
                .map(documentBlock -> {
                    documentBlock.setDocument(newDocumentBlock.getDocument());
                    documentBlock.setTemplateBlock(newDocumentBlock.getTemplateBlock());
                    documentBlock.setX(newDocumentBlock.getX());
                    documentBlock.setY(newDocumentBlock.getY());
                    documentBlock.setHeight(newDocumentBlock.getHeight());
                    documentBlock.setValue(newDocumentBlock.getValue());
                    documentBlock.setWidth(newDocumentBlock.getWidth());

                    return documentBlock;
                }).orElseThrow(() -> new NoSuchElementException("no such document block"));
        return "";
    }
    @PostMapping("")
    public String delete(String id){
        documentBlockRepository.deleteById(id);
        return "";
    }
}
