package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.dtos.UserRequestDTO;
import ro.amicus.archive.dtos.UserResponseDTO;
import ro.amicus.archive.dtos.UserSearchDTO;
import ro.amicus.archive.dtos.UserUserRoleRequestDTO;
import ro.amicus.archive.servicies.UserService;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserResponseDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/user")
    public UserResponseDTO getUser(@RequestBody UserSearchDTO userSearchDTO) {
        return userService.getUser(userSearchDTO);
    }

    @PostMapping("/add-users")
    public void addUser(@RequestBody UserRequestDTO userRequestDTO) {
        userService.addUser(userRequestDTO);
    }

    @PutMapping("/update-user")
    public void updateUser(@RequestBody UserRequestDTO userRequestDTO) {
        userService.updateUser(userRequestDTO);
    }

    @DeleteMapping("/delete-user")
    public void deleteUser(@RequestBody UserRequestDTO userRequestDTO) {
        userService.deleteUser(userRequestDTO);
    }

    @PutMapping("/update-user-role")
    public void updateUserRole(@RequestBody UserUserRoleRequestDTO userUserRoleRequestDTO) {
        userService.updateUserRole(userUserRoleRequestDTO);
    }

}
