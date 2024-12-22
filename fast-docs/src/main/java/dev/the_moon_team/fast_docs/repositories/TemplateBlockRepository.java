package dev.the_moon_team.fast_docs.repositories;

import dev.the_moon_team.fast_docs.entities.TemplateBlock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateBlockRepository extends JpaRepository<TemplateBlock, String> {
}
