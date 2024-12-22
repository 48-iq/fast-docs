package dev.the_moon_team.fast_docs.controllers;

import dev.the_moon_team.fast_docs.entities.Document;
import dev.the_moon_team.fast_docs.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class DocumentController {
    @Autowired
    DocumentRepository documentRepository;

    @GetMapping("")
    public List<Document> read(){
        return documentRepository.findAll();
    }
    @GetMapping("")
    public Document readById(String id){
        return documentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no such document"));
    }
    @PostMapping("") //что-то там
    public String save(Document document){
        documentRepository.save(document);
        return ""; //что-то там
    }

    @PostMapping("")
    public String update(Document newDocument, String id){
        documentRepository.findById(id)
                        .map(document -> {
                            document.setTitle(newDocument.getTitle());
                            document.setTemplate(newDocument.getTemplate());
                            document.setDocumentBlocks(newDocument.getDocumentBlocks());
                            return document;
                        }).orElseThrow(() -> new NoSuchElementException("no such document"));
        return "";
    }
    @PostMapping("")
    public String delete(String id){
        documentRepository.deleteById(id);
        return "";
    }
}
