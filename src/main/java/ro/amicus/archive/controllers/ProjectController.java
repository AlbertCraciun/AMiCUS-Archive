package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.entities.Project;

import java.util.List;

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
    public Project getProject(@PathVariable Long id) {
        return projectService.getProject(id);
    }

    @PostMapping("/add-projects")
    public Project addProject(@RequestBody Project project) {
        return projectService.addProject(project);
    }

}
