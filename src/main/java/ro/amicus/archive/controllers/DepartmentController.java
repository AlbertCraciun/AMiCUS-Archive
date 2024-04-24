package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.servicies.DepartmentService;

import java.util.List;


@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public List<String> getDepartments() {
        return departmentService.getDepartments();
    }

    @GetMapping("/departments/{id}")
    public String getDepartment(@PathVariable String name) {
        return departmentService.getDepartment(name);
    }

    @PostMapping("/add-departments")
    public void addDepartment(@RequestBody String name) {
        departmentService.addDepartment(name);
    }

}
