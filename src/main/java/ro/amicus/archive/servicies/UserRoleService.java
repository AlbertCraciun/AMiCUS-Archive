package ro.amicus.archive.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.amicus.archive.dtos.UserRoleDTO;
import ro.amicus.archive.entities.UserRole;
import ro.amicus.archive.repositories.MyUserRoleRepository;

import java.util.List;

@Slf4j
@Service
public class UserRoleService {

    private final MyUserRoleRepository myUserRoleRepository;

    public UserRoleService(MyUserRoleRepository myUserRoleRepository) {
        this.myUserRoleRepository = myUserRoleRepository;
    }

    public List<UserRoleDTO> getUserRoles() {
        List<UserRole> userRoles = myUserRoleRepository.findAll();
        return userRoles.stream()
                .map(userRole -> UserRoleDTO.builder()
                        .roleName(String.valueOf(userRole.getRoleName()))
                        .departmentName(userRole.getDepartment().getName())
                        .privilegeName(String.valueOf(userRole.getPrivilege().getName()))
                        .build())
                .toList();
    }

}
