package ro.amicus.archive.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.amicus.archive.dtos.UserRequestDTO;
import ro.amicus.archive.dtos.UserResponseDTO;
import ro.amicus.archive.dtos.UserSearchDTO;
import ro.amicus.archive.dtos.UserUserRoleRequestDTO;
import ro.amicus.archive.entities.User;
import ro.amicus.archive.entities.UserRole;
import ro.amicus.archive.enums.AcademicStatus;
import ro.amicus.archive.enums.PrivilegeNames;
import ro.amicus.archive.enums.RoleNames;
import ro.amicus.archive.repositories.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;
    private final PrivilegeRepository privilegeRepository;

    public UserService(UserRepository userRepository,
                       BranchRepository branchRepository, FacultyRepository facultyRepository,
                       DepartmentRepository departmentRepository, PrivilegeRepository privilegeRepository) {
        this.userRepository = userRepository;
        this.branchRepository = branchRepository;
        this.facultyRepository = facultyRepository;
        this.departmentRepository = departmentRepository;
        this.privilegeRepository = privilegeRepository;
    }

    public List<UserResponseDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> UserResponseDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .role(user.getUserRole().getRoleName() + " " + user.getUserRole().getDepartment().getName())
                .branchName(user.getBranch().getCity().getCityName())
                .facultyName(user.getFaculty().getName())
                .bcdStartYear(user.getBcdStartYear())
                .bcdStatus(user.getBcdStatus().name())
                .mdStatus(user.getMdStatus().name())
                .ddStatus(user.getDdStatus().name())
                .lastUpdate(user.getLastUpdateOn().toLocalDateTime().toLocalDate())
                .build()).toList();
    }

    public UserResponseDTO getUser(UserSearchDTO userSearchDTO) {
        User user = userRepository.findByEmail(userSearchDTO.getEmail());
        return UserResponseDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .role(user.getUserRole().getRoleName() + " " + user.getUserRole().getDepartment().getName())
                .branchName(user.getBranch().getCity().getCityName())
                .facultyName(user.getFaculty().getName())
                .bcdStartYear(user.getBcdStartYear())
                .bcdStatus(user.getBcdStatus().name())
                .mdStatus(user.getMdStatus().name())
                .ddStatus(user.getDdStatus().name())
                .lastUpdate(user.getLastUpdateOn().toLocalDateTime().toLocalDate())
                .build();
    }

    public void addUser(UserRequestDTO userRequestDTO) {

        User user = new User();
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setBirthDate(userRequestDTO.getBirthDate());
        user.setBranch(branchRepository.findByCity_CityName(userRequestDTO.getBranch()));
        user.setFaculty(facultyRepository.findByName(userRequestDTO.getFaculty()));
        user.setBcdStartYear(userRequestDTO.getBcdStartYear());
        user.setBcdStatus(AcademicStatus.valueOf(userRequestDTO.getBcdStatus()));
        user.setMdStatus(AcademicStatus.valueOf(userRequestDTO.getMdStatus()));
        user.setDdStatus(AcademicStatus.valueOf(userRequestDTO.getDdStatus()));
        userRepository.save(user);
    }

    public void updateUser(UserRequestDTO userRequestDTO) {
        User user = userRepository.findByEmail(userRequestDTO.getEmail());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setBirthDate(userRequestDTO.getBirthDate());
        user.setBranch(branchRepository.findByCity_CityName(userRequestDTO.getBranch()));
        user.setFaculty(facultyRepository.findByName(userRequestDTO.getFaculty()));
        user.setBcdStartYear(userRequestDTO.getBcdStartYear());
        user.setBcdStatus(AcademicStatus.valueOf(userRequestDTO.getBcdStatus()));
        user.setMdStatus(AcademicStatus.valueOf(userRequestDTO.getMdStatus()));
        user.setDdStatus(AcademicStatus.valueOf(userRequestDTO.getDdStatus()));
        userRepository.save(user);
    }

    public void deleteUser(UserRequestDTO userRequestDTO) {
        User user = userRepository.findByEmail(userRequestDTO.getEmail());
        userRepository.delete(user);
    }

    public void updateUserRole(UserUserRoleRequestDTO userUserRoleRequestDTO) {
        User user = userRepository.findByEmail(userUserRoleRequestDTO.getEmail());

        UserRole userRole = new UserRole();
        userRole.setRoleName(RoleNames.valueOf(userUserRoleRequestDTO.getUserRoleDTO().getRoleName()));
        userRole.setDepartment(departmentRepository.findByName(userUserRoleRequestDTO.getUserRoleDTO().getDepartmentName()));
        userRole.setPrivilege(privilegeRepository.findByName(PrivilegeNames.valueOf(userUserRoleRequestDTO.getUserRoleDTO().getPrivilegeName())));
        userRole.setStartDate(LocalDate.now());

        user.setApprovedRoleOn(LocalDate.now());
        user.setApprovedRoleBy(userUserRoleRequestDTO.getApprovedRoleBy());

        user.setUserRole(userRole);
        userRepository.save(user);
    }
}
