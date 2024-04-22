package ro.amicus.archive.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.amicus.archive.entities.Privilege;
import ro.amicus.archive.repositories.PrivilegeRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class PrivilegeService {

    private final PrivilegeRepository privilegeRepository;

    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    public List<Privilege> getPrivileges() {
        return privilegeRepository.findAll();
    }

    public Privilege getPrivilege(UUID id) {
        return privilegeRepository.findById(id).orElse(null);
    }

    public Privilege addPrivilege(Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

}
