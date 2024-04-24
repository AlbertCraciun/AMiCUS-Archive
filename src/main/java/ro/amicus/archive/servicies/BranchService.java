package ro.amicus.archive.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.amicus.archive.dtos.BranchDTO;
import ro.amicus.archive.entities.Branch;
import ro.amicus.archive.repositories.BranchRepository;

import java.util.List;

@Slf4j
@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public List<BranchDTO> getBranches() {
        List<Branch> branches = branchRepository.findAll();
        return branches.stream().map(branch -> BranchDTO.builder()
                .cityName(branch.getCity().getCityName())
                .foundationYear(branch.getFoundationYear())
                .build()).toList();
    }

    public BranchDTO getBranch(String name) {
        Branch branch = branchRepository.findByCity_CityName(name);
        return BranchDTO.builder()
                .cityName(branch.getCity().getCityName())
                .foundationYear(branch.getFoundationYear())
                .build();
    }

    public void addBranch(BranchDTO branchDTO) {
        Branch branch = new Branch();
        branch.setCity(branchRepository.findByCity_CityName(branchDTO.getCityName()).getCity());
        branch.setFoundationYear(branchDTO.getFoundationYear());
        branchRepository.save(branch);
    }
}
