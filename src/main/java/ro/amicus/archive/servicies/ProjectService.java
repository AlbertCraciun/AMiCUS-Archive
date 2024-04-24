package ro.amicus.archive.servicies;

import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ro.amicus.archive.dtos.ProjectDTO;
import ro.amicus.archive.entities.Project;
import ro.amicus.archive.repositories.BranchRepository;
import ro.amicus.archive.repositories.DepartmentRepository;
import ro.amicus.archive.repositories.ProjectRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final BranchRepository branchRepository;
    private final DepartmentRepository departmentRepository;

    public ProjectService(ProjectRepository projectRepository, BranchRepository branchRepository, DepartmentRepository departmentRepository) {
        this.projectRepository = projectRepository;
        this.branchRepository = branchRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<ProjectDTO> getProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(project -> ProjectDTO.builder()
                .branchName(project.getBranch().getCity().getCityName())
                .departmentName(project.getDepartment().getName())
                .name(project.getName())
                .editions(project.getEditions())
                .startYear(project.getStartYear())
                .lastEdDate(project.getLastEdDate())
                .lastEdParticipants(project.getLastEdParticipants())
                .lastEdExpenses(project.getLastEdExpenses())
                .objectives(project.getObjectives())
                .description(project.getDescription())
                .lastUpdateOn(project.getLastUpdateOn().toLocalDateTime().toLocalDate())
                .build()).toList();

    }

    public ProjectDTO getProject(ProjectDTO projectDTO) {
        Specification<Project> spec = projectMatches(projectDTO);
        List<Project> projects = projectRepository.findAll(spec);
        return projects.stream().map(project -> ProjectDTO.builder()
                .branchName(project.getBranch().getCity().getCityName())
                .departmentName(project.getDepartment().getName())
                .name(project.getName())
                .editions(project.getEditions())
                .startYear(project.getStartYear())
                .lastEdDate(project.getLastEdDate())
                .lastEdParticipants(project.getLastEdParticipants())
                .lastEdExpenses(project.getLastEdExpenses())
                .objectives(project.getObjectives())
                .description(project.getDescription())
                .lastUpdateOn(project.getLastUpdateOn().toLocalDateTime().toLocalDate())
                .build()).findFirst().orElse(null);
    }

    public void addProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setBranch(branchRepository.findByCity_CityName(projectDTO.getBranchName()));
        project.setDepartment(departmentRepository.findByName(projectDTO.getDepartmentName()));
        project.setName(projectDTO.getName());
        project.setEditions(projectDTO.getEditions());
        project.setStartYear(projectDTO.getStartYear());
        project.setLastEdDate(projectDTO.getLastEdDate());
        project.setLastEdParticipants(projectDTO.getLastEdParticipants());
        project.setLastEdExpenses(projectDTO.getLastEdExpenses());
        project.setObjectives(projectDTO.getObjectives());
        project.setDescription(projectDTO.getDescription());
        projectRepository.save(project);
    }

    public void updateProject(ProjectDTO projectDTO) {
        Specification<Project> spec = projectMatches(projectDTO);
        List<Project> projects = projectRepository.findAll(spec);
        Project project = projects.get(0);
        project.setBranch(branchRepository.findByCity_CityName(projectDTO.getBranchName()));
        project.setDepartment(departmentRepository.findByName(projectDTO.getDepartmentName()));
        project.setName(projectDTO.getName());
        projectRepository.save(project);
    }

    public void deleteProject(ProjectDTO projectDTO) {
        Specification<Project> spec = projectMatches(projectDTO);
        List<Project> projects = projectRepository.findAll(spec);
        projectRepository.delete(projects.get(0));
    }

    private Specification<Project> projectMatches(ProjectDTO criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.getBranchName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("branch").get("city").get("cityName"), criteria.getBranchName()));
            }
            if (criteria.getDepartmentName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("department").get("name"), criteria.getDepartmentName()));
            }
            if (criteria.getName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("name"), criteria.getName()));
            }
            if (criteria.getEditions() != null) {
                predicates.add(criteriaBuilder.equal(root.get("editions"), criteria.getEditions()));
            }
            if (criteria.getStartYear() != null) {
                predicates.add(criteriaBuilder.equal(root.get("startYear"), criteria.getStartYear()));
            }
            if (criteria.getLastEdDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("lastEdDate"), criteria.getLastEdDate()));
            }
            if (criteria.getLastEdParticipants() != null) {
                predicates.add(criteriaBuilder.equal(root.get("lastEdParticipants"), criteria.getLastEdParticipants()));
            }
            if (criteria.getLastEdExpenses() != null) {
                predicates.add(criteriaBuilder.equal(root.get("lastEdExpenses"), criteria.getLastEdExpenses()));
            }
            if (criteria.getObjectives() != null) {
                predicates.add(criteriaBuilder.equal(root.get("objectives"), criteria.getObjectives()));
            }
            if (criteria.getDescription() != null) {
                predicates.add(criteriaBuilder.equal(root.get("description"), criteria.getDescription()));
            }
            if (criteria.getLastUpdateOn() != null) {
                predicates.add(criteriaBuilder.equal(root.get("lastUpdateOn"), criteria.getLastUpdateOn()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
