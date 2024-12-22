package dev.the_moon_team.fast_docs.dto;

import dev.the_moon_team.fast_docs.entities.Template;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemplateBlockDto {
    public String id;
    public String defaultValue;
    public String type;

    public Template template;
}
