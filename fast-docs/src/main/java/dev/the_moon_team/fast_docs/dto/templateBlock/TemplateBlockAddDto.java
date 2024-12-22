package dev.the_moon_team.fast_docs.dto.templateBlock;

import dev.the_moon_team.fast_docs.entities.Template;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemplateBlockAddDto {
    public String defaultValue;
    public String type;

    public Template template;
}
