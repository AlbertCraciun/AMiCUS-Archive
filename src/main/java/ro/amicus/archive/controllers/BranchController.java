package ro.amicus.archive.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.dtos.BranchDTO;
import ro.amicus.archive.servicies.BranchService;

import java.util.List;

@RestController
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/branches")
    @ResponseStatus(HttpStatus.OK)
    public List<BranchDTO> getBranches() {
        return branchService.getBranches();
    }

    @GetMapping("/branches/{name}")
    @ResponseStatus(HttpStatus.OK)
    public BranchDTO getBranch(@PathVariable String name) {
        return branchService.getBranch(name);
    }

    @PostMapping("/add-branches")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBranch(@RequestBody BranchDTO branchDTO) {
        branchService.addBranch(branchDTO);
    }

}
