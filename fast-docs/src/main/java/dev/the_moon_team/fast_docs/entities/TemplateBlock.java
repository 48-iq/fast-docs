package dev.the_moon_team.fast_docs.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "template_blocks")
public class TemplateBlock implements Serializable {
    @Id
    private String id;
    private String defaultValue;
    private String type;
}
