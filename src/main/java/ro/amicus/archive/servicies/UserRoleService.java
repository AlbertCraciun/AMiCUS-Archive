package ro.amicus.archive.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.amicus.archive.dtos.UserRoleDTO;
import ro.amicus.archive.entities.UserRole;
import ro.amicus.archive.repositories.UserRoleRepository;

import java.util.List;

@Slf4j
@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRoleDTO> getUserRoles() {
        List<UserRole> userRoles = userRoleRepository.findAll();
        return userRoles.stream()
                .map(userRole -> UserRoleDTO.builder()
                        .roleName(String.valueOf(userRole.getRoleName()))
                        .departmentName(userRole.getDepartment().getName())
                        .privilegeName(String.valueOf(userRole.getPrivilege().getName()))
                        .build())
                .toList();
    }

}
