package dev.the_moon_team.fast_docs.repositories;

import dev.the_moon_team.fast_docs.entities.DocumentBlock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentBlockRepository extends JpaRepository<DocumentBlock, String> {
}
