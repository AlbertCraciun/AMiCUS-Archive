package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.entities.User;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{name}")
    public User getUser(@PathVariable String name) {
        return userService.getUser(anme);
    }

    @PostMapping("/add-users")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

}
