package ro.amicus.archive.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.dtos.PrivilegeDTO;
import ro.amicus.archive.servicies.PrivilegeService;

import java.util.List;

@RestController
public class PrivilegeController {

    private final PrivilegeService privilegeService;

    public PrivilegeController(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    @GetMapping("/privileges")
    @ResponseStatus(HttpStatus.OK)
    public List<PrivilegeDTO> getPrivileges() {
        return privilegeService.getPrivileges();
    }

    @GetMapping("/privileges/{name}")
    @ResponseStatus(HttpStatus.OK)
    public PrivilegeDTO getPrivilege(@PathVariable String name) {
        return privilegeService.getPrivilege(name);
    }

    @PutMapping("/update-privileges/{name}")
    public void updatePrivilege(@RequestBody Integer activeDays, @PathVariable String name) {
        privilegeService.updatePrivilege(activeDays, name);
    }

}
