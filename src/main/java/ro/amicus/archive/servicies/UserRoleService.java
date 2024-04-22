package ro.amicus.archive.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.amicus.archive.entities.UserRole;
import ro.amicus.archive.repositories.UserRoleRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRole> getUserRoles() {
        return userRoleRepository.findAll();
    }

    public UserRole getUserRole(UUID id) {
        return userRoleRepository.findById(id).orElse(null);
    }

    public UserRole addUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

}
