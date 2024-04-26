package ro.amicus.archive.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.dtos.UserRequestDTO;
import ro.amicus.archive.dtos.UserResponseDTO;
import ro.amicus.archive.dtos.UserSearchDTO;
import ro.amicus.archive.dtos.UserUserRoleRequestDTO;
import ro.amicus.archive.servicies.MyUserService;

import java.util.List;
import java.util.UUID;

@RestController
public class MyUserController {

    private final MyUserService myUserService;

    public MyUserController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @GetMapping("/users")
    public List<UserResponseDTO> getUsers() {
        return myUserService.getUsers();
    }

    @GetMapping("/user")
    public UserResponseDTO getUser(@RequestBody UserSearchDTO userSearchDTO) {
        return myUserService.getUser(userSearchDTO);
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO getUserById(@PathVariable UUID id) {
        return myUserService.getUserById(id);
    }

    @PostMapping("/add-user")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody UserRequestDTO userRequestDTO) {
        myUserService.addUser(userRequestDTO);
    }

    @PutMapping("/update-user")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUser(@RequestBody UserRequestDTO userRequestDTO) {
        myUserService.updateUser(userRequestDTO);
    }

    @DeleteMapping("/delete-user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@RequestBody UserRequestDTO userRequestDTO) {
        myUserService.deleteUser(userRequestDTO);
    }

    @PutMapping("/update-user-role")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUserRole(@RequestBody UserUserRoleRequestDTO userUserRoleRequestDTO) {
        myUserService.updateUserRole(userUserRoleRequestDTO);
    }

}
