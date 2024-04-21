package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.entities.Department;

import java.util.List;


@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    @GetMapping("/departments/{id}")
    public Department getDepartment(@PathVariable Long id) {
        return departmentService.getDepartment(id);
    }

    @PostMapping("/add-departments")
    public Department addDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

}
