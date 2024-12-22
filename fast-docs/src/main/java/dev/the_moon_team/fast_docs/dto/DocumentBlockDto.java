package dev.the_moon_team.fast_docs.dto;

import dev.the_moon_team.fast_docs.entities.Document;
import dev.the_moon_team.fast_docs.entities.TemplateBlock;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentBlockDto {
    public String value;
    public TemplateBlock templateBlock;
    public Document document;
    public Integer x;
    public Integer y;
    public Integer width;
    public Integer height;
}
