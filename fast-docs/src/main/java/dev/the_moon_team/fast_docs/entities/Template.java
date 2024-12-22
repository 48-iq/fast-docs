package dev.the_moon_team.fast_docs.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "template")
public class Template {
    @Id
    private String id;
    private String title;
    @OneToMany(mappedBy = "template")
    private List<Document> documents;
    @OneToMany(mappedBy = "template")
    private List<TemplateBlock> blocks;
}
