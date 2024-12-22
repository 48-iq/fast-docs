package dev.the_moon_team.fast_docs.dto;

import dev.the_moon_team.fast_docs.entities.DocumentBlock;
import dev.the_moon_team.fast_docs.entities.Template;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

public class DocumentDto {
    public String id;
    public String title;

    public List<DocumentBlock> documentBlocks;

    public Template template;
}
