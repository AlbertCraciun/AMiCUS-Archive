package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.entities.Privilege;

import java.util.List;

@RestController
public class PrivilegeController {

    private final PrivilegeService privilegeService;

    public PrivilegeController(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    @GetMapping("/privileges")
    public List<Privilege> getPrivileges() {
        return privilegeService.getPrivileges();
    }

    @GetMapping("/privileges/{id}")
    public Privilege getPrivilege(@PathVariable Long id) {
        return privilegeService.getPrivilege(id);
    }

    @PostMapping("/add-privileges")
    public Privilege addPrivilege(@RequestBody Privilege privilege) {
        return privilegeService.addPrivilege(privilege);
    }

}
