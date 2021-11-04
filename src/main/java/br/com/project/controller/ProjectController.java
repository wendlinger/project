package br.com.project.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.exceptions.BusinessException;
import br.com.project.model.Project;
import br.com.project.service.ProjectService;

@RestController
@RequestMapping("projects")
public class ProjectController {

	private ProjectService projectService;

	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@PostMapping
	public ResponseEntity<Project> save(Project project) throws BusinessException, URISyntaxException {
		return ResponseEntity.ok(projectService.save(project));
	}

}
