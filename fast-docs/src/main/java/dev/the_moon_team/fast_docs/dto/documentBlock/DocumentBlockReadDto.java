package dev.the_moon_team.fast_docs.dto.documentBlock;

import dev.the_moon_team.fast_docs.entities.Document;
import dev.the_moon_team.fast_docs.entities.TemplateBlock;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentBlockReadDto {
    public String value;
    public TemplateBlock templateBlock;
    public Document document;
    public Integer x;
    public Integer y;
    public Integer width;
    public Integer height;
}
