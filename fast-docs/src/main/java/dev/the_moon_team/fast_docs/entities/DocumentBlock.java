package dev.the_moon_team.fast_docs.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "document_blocks")
public class DocumentBlock {
    @Id
    private String id;
    private String value;
    @ManyToOne
    @JoinColumn(name = "template_block_id", referencedColumnName = "id")
    private TemplateBlock templateBlock;
    @ManyToOne
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    private Document document;
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;
}
