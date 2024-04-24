package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.amicus.archive.dtos.UserRoleDTO;
import ro.amicus.archive.servicies.UserRoleService;

import java.util.List;

@RestController
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping("/user-roles")
    public List<UserRoleDTO> getUserRoles() {
        return userRoleService.getUserRoles();
    }

}
