package ro.amicus.archive.controllers;

import org.springframework.web.bind.annotation.*;
import ro.amicus.archive.entities.Branch;
import ro.amicus.archive.servicies.BranchService;

import java.util.List;
import java.util.UUID;

@RestController
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/branches")
    public List<Branch> getBranches() {
        return branchService.getBranches();
    }

    @GetMapping("/branches/{id}")
    public Branch getBranch(@PathVariable UUID id) {
        return branchService.getBranch(id);
    }

    @PostMapping("/add-branches")
    public Branch addBranch(@RequestBody Branch branch) {
        return branchService.addBranch(branch);
    }

}
