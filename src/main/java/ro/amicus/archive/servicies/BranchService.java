package ro.amicus.archive.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.amicus.archive.entities.Branch;
import ro.amicus.archive.repositories.BranchRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public List<Branch> getBranches() {
        return branchRepository.findAll();
    }

    public Branch getBranch(UUID id) {
        return branchRepository.findById(id).orElse(null);
    }

    public Branch addBranch(Branch branch) {
        return branchRepository.save(branch);
    }
}
