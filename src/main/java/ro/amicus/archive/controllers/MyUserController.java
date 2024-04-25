package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.dtos.UserRequestDTO;
import ro.amicus.archive.dtos.UserResponseDTO;
import ro.amicus.archive.dtos.UserSearchDTO;
import ro.amicus.archive.dtos.UserUserRoleRequestDTO;
import ro.amicus.archive.servicies.MyUserService;

import java.util.List;

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

    @PostMapping("/add-user")
    public void addUser(@RequestBody UserRequestDTO userRequestDTO) {
        myUserService.addUser(userRequestDTO);
    }

    @PutMapping("/update-user")
    public void updateUser(@RequestBody UserRequestDTO userRequestDTO) {
        myUserService.updateUser(userRequestDTO);
    }

    @DeleteMapping("/delete-user")
    public void deleteUser(@RequestBody UserRequestDTO userRequestDTO) {
        myUserService.deleteUser(userRequestDTO);
    }

    @PutMapping("/update-user-role")
    public void updateUserRole(@RequestBody UserUserRoleRequestDTO userUserRoleRequestDTO) {
        myUserService.updateUserRole(userUserRoleRequestDTO);
    }

}
