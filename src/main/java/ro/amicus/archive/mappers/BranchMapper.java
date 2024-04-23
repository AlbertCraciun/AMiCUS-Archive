package ro.amicus.archive.mappers;

import org.mapstruct.Mapper;
import ro.amicus.archive.dtos.BranchDTO;
import ro.amicus.archive.entities.Branch;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    BranchDTO brachToBranchDTO(Branch branch);

    List<BranchDTO> brachListToBranchDTOList(List<Branch> branchList);

    Branch branchDTOToBranch(BranchDTO branchDTO);

}
