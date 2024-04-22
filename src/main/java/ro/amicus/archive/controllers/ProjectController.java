package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.entities.Project;
import ro.amicus.archive.servicies.ProjectService;

import java.util.List;
import java.util.UUID;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public List<Project> getProjects() {
        return projectService.getProjects();
    }

    @GetMapping("/projects/{id}")
    public Project getProject(@PathVariable UUID id) {
        return projectService.getProject(id);
    }

    @PostMapping("/add-projects")
    public Project addProject(@RequestBody Project project) {
        return projectService.addProject(project);
    }

}
