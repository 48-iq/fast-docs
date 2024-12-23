package dev.the_moon_team.fast_docs.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "documents")
public class Document {
    @Id
    private String id;
    private String title;

    @OneToMany(mappedBy = "document")
    private List<DocumentBlock> documentBlocks;
}
