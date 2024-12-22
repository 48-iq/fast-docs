package dev.the_moon_team.fast_docs.repositories;

import dev.the_moon_team.fast_docs.entities.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, String> {
}
