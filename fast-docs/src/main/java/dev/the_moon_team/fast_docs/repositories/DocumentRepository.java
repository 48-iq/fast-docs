package dev.the_moon_team.fast_docs.repositories;

import dev.the_moon_team.fast_docs.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, String> {

}
