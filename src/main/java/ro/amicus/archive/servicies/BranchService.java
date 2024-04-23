package ro.amicus.archive.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.amicus.archive.dtos.BranchDTO;
import ro.amicus.archive.mappers.BranchMapper;
import ro.amicus.archive.repositories.BranchRepository;

import java.util.List;

@Slf4j
@Service
public class BranchService {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    public BranchService(BranchRepository branchRepository, BranchMapper branchMapper) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
    }

    public List<BranchDTO> getBranches() {
        return branchMapper.brachListToBranchDTOList(branchRepository.findAll());
    }

    public BranchDTO getBranch(String name) {
        return branchMapper.brachToBranchDTO(branchRepository.findByCity_CityName(name));
    }

    public void addBranch(BranchDTO branchDTO) {
        branchRepository.save(branchMapper.branchDTOToBranch(branchDTO));
    }
}
