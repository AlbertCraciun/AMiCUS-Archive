package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.dtos.ProjectDTO;
import ro.amicus.archive.servicies.ProjectService;

import java.util.List;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public List<ProjectDTO> getProjects() {
        return projectService.getProjects();
    }

    @GetMapping("/project")
    public ProjectDTO getProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.getProject(projectDTO);
    }

    @PostMapping("/add-projects")
    public void addProject(@RequestBody ProjectDTO projectDTO) {
        projectService.addProject(projectDTO);
    }

    @PutMapping("/update-project")
    public void updateProject(@RequestBody ProjectDTO projectDTO) {
        projectService.updateProject(projectDTO);
    }

    @DeleteMapping("/delete-project")
    public void deleteProject(@RequestBody ProjectDTO projectDTO) {
        projectService.deleteProject(projectDTO);
    }

}
