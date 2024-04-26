package ro.amicus.archive.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.amicus.archive.dtos.PrivilegeDTO;
import ro.amicus.archive.entities.Privilege;
import ro.amicus.archive.repositories.PrivilegeRepository;

import java.util.List;

@Slf4j
@Service
public class PrivilegeService {

    private final PrivilegeRepository privilegeRepository;

    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    public List<PrivilegeDTO> getPrivileges() {
        List<Privilege> privileges = privilegeRepository.findAll();
        return privileges.stream()
                .map(privilege -> PrivilegeDTO.builder()
                        .name(privilege.getName())
                        .activeDays(privilege.getActiveDays())
                        .build())
                .toList();
    }

    public PrivilegeDTO getPrivilege(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        return PrivilegeDTO.builder()
                .name(privilege.getName())
                .activeDays(privilege.getActiveDays())
                .build();
    }

    public void updatePrivilege(Integer activeDays, String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        privilege.setActiveDays(activeDays);
        privilegeRepository.save(privilege);
    }

}
