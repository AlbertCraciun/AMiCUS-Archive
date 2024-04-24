package ro.amicus.archive.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.amicus.archive.entities.Department;
import ro.amicus.archive.repositories.DepartmentRepository;

import java.util.List;

@Slf4j
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<String> getDepartments() {
        return departmentRepository.findAll().stream().map(Department::getName).toList();
    }

    public String getDepartment(String name) {
        return departmentRepository.findByName(name).getName();
    }

    public void addDepartment(String name) {
        Department department = new Department();
        department.setName(name);
        departmentRepository.save(department);
    }

}
