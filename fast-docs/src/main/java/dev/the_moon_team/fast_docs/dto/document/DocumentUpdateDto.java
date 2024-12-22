package dev.the_moon_team.fast_docs.dto.document;

import dev.the_moon_team.fast_docs.entities.Template;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentUpdateDto {
    public String title;

    public Template template;
}
