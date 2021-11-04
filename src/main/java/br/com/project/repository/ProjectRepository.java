package br.com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
