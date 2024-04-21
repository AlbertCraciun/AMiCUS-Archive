package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping("/user-roles")
    public String getUserRoles() {
        return userRoleService.getUserRoles();
    }

    @GetMapping("/user-roles/{id}")
    public String getUserRoleById(@PathVariable Long id) {
        return userRoleService.getUserRoleById(id);
    }

    @PostMapping("/user-roles")
    public String addUserRole(@RequestBody String userRole) {
        return userRoleService.addUserRole(userRole);
    }

}
