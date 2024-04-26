package ro.amicus.archive.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.amicus.archive.dtos.UserRequestDTO;
import ro.amicus.archive.dtos.UserResponseDTO;
import ro.amicus.archive.dtos.UserSearchDTO;
import ro.amicus.archive.dtos.UserUserRoleRequestDTO;
import ro.amicus.archive.entities.MyUser;
import ro.amicus.archive.entities.UserRole;
import ro.amicus.archive.repositories.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class MyUserService {

    private final MyUserRepository myUserRepository;
    private final BranchRepository branchRepository;
    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;
    private final PrivilegeRepository privilegeRepository;
    private final PasswordEncoder passwordEncoder;
    private final MyUserRoleRepository myUserRoleRepository;

    public MyUserService(MyUserRepository myUserRepository,
                         BranchRepository branchRepository, FacultyRepository facultyRepository,
                         DepartmentRepository departmentRepository, PrivilegeRepository privilegeRepository,
                         PasswordEncoder passwordEncoder, MyUserRoleRepository myUserRoleRepository) {
        this.myUserRepository = myUserRepository;
        this.branchRepository = branchRepository;
        this.facultyRepository = facultyRepository;
        this.departmentRepository = departmentRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
        this.myUserRoleRepository = myUserRoleRepository;
    }

    public List<UserResponseDTO> getUsers() {
        List<MyUser> myUsers = myUserRepository.findAll();

        //check if myUsers is null
        if (myUsers == null) {
            return null;
        }

//        //check if lastUpdateOn is null
//        for (MyUser myUser : myUsers) {
//            if (myUser.getLastUpdateOn() == null) {
//                return null;
//            }
//        }

        return myUsers.stream().map(myUser -> UserResponseDTO.builder()
                .firstName(myUser.getFirstName())
                .lastName(myUser.getLastName())
                .email(myUser.getEmail())
                .birthDate(myUser.getBirthDate())
                .role(myUser.getUserRole().getRoleName())
                .branchName(myUser.getBranch().getCity().getCityName())
                .facultyName(myUser.getFaculty().getName())
                .bcdStartYear(myUser.getBcdStartYear())
                .bcdStatus(myUser.getBcdStatus())
                .mdStatus(myUser.getMdStatus())
                .ddStatus(myUser.getDdStatus())
                //.lastUpdate(myUser.getLastUpdateOn().toLocalDateTime().toLocalDate())
                .build()).toList();
    }

    public UserResponseDTO getUser(UserSearchDTO userSearchDTO) {
        Optional<MyUser> myUser = myUserRepository.findByEmail(userSearchDTO.getEmail());
        return myUser.map(user -> UserResponseDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .role(user.getUserRole().getRoleName())
                .branchName(user.getBranch().getCity().getCityName())
                .facultyName(user.getFaculty().getName())
                .bcdStartYear(user.getBcdStartYear())
                .bcdStatus(user.getBcdStatus())
                .mdStatus(user.getMdStatus())
                .ddStatus(user.getDdStatus())
                .lastUpdate(user.getLastUpdateOn().toLocalDateTime().toLocalDate())
                .build()).orElse(null);
    }

    public void addUser(UserRequestDTO userRequestDTO) {

        log.info("Adding user: {}", userRequestDTO);

        MyUser myUser = new MyUser();
        myUser.setFirstName(userRequestDTO.getFirstName());
        myUser.setLastName(userRequestDTO.getLastName());
        myUser.setEmail(userRequestDTO.getEmail());
        myUser.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        myUser.setBirthDate(userRequestDTO.getBirthDate());
        myUser.setBranch(branchRepository.findByCity_CityName(userRequestDTO.getBranch()));
        myUser.setFaculty(facultyRepository.findByName(userRequestDTO.getFaculty()));
        myUser.setBcdStartYear(userRequestDTO.getBcdStartYear());
        myUser.setBcdStatus(userRequestDTO.getBcdStatus());
        myUser.setMdStatus(userRequestDTO.getMdStatus());
        myUser.setDdStatus(userRequestDTO.getDdStatus());

        log.info("Adding user: {}", myUser);
        UserRole basicUserRole = new UserRole();
        basicUserRole.setRoleName("USER");
        basicUserRole.setStartDate(LocalDate.now());
        basicUserRole.setPrivilege(privilegeRepository.findByName("USER"));
        myUserRoleRepository.save(basicUserRole);

        myUser.setUserRole(myUserRoleRepository.findByRoleNameAndStartDate("USER", LocalDate.now()).get(0));

        myUserRepository.save(myUser);
    }

    public void updateUser(UserRequestDTO userRequestDTO) {
        Optional<MyUser> myUser = myUserRepository.findByEmail(userRequestDTO.getEmail());
        if (myUser.isEmpty()) {
            return;
        }
        myUser.get().setFirstName(userRequestDTO.getFirstName());
        myUser.get().setLastName(userRequestDTO.getLastName());
        myUser.get().setEmail(userRequestDTO.getEmail());
        myUser.get().setPassword(userRequestDTO.getPassword());
        myUser.get().setBirthDate(userRequestDTO.getBirthDate());
        myUser.get().setBranch(branchRepository.findByCity_CityName(userRequestDTO.getBranch()));
        myUser.get().setFaculty(facultyRepository.findByName(userRequestDTO.getFaculty()));
        myUser.get().setBcdStartYear(userRequestDTO.getBcdStartYear());
        myUser.get().setBcdStatus(userRequestDTO.getBcdStatus());
        myUser.get().setMdStatus(userRequestDTO.getMdStatus());
        myUser.get().setDdStatus(userRequestDTO.getDdStatus());
        myUserRepository.save(myUser.get());
    }

    public void deleteUser(UserRequestDTO userRequestDTO) {
        Optional<MyUser> myUser = myUserRepository.findByEmail(userRequestDTO.getEmail());
        if (myUser.isEmpty()) {
            return;
        }
        myUserRepository.delete(myUser.get());
    }

    public void updateUserRole(UserUserRoleRequestDTO userUserRoleRequestDTO) {
        Optional<MyUser> myUser = myUserRepository.findByEmail(userUserRoleRequestDTO.getEmail());

        if (myUser.isEmpty()) {
            return;
        }

        UserRole userRole = new UserRole();
        userRole.setRoleName(userUserRoleRequestDTO.getUserRoleDTO().getRoleName());
        userRole.setDepartment(departmentRepository.findByName(userUserRoleRequestDTO.getUserRoleDTO().getDepartmentName()));
        userRole.setPrivilege(privilegeRepository.findByName(userUserRoleRequestDTO.getUserRoleDTO().getPrivilegeName()));
        userRole.setStartDate(LocalDate.now());

        myUser.get().setApprovedRoleOn(LocalDate.now());
        myUser.get().setApprovedRoleBy(myUserRepository.findByEmail(userUserRoleRequestDTO.getApprovedRoleBy()).get().getUserId());

        myUser.get().setUserRole(userRole);
        myUserRepository.save(myUser.get());
    }

    public UserResponseDTO getUserById(UUID id) {
        return myUserRepository.findById(id).map(user -> UserResponseDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .role(user.getUserRole().getRoleName())
                .branchName(user.getBranch().getCity().getCityName())
                .facultyName(user.getFaculty().getName())
                .bcdStartYear(user.getBcdStartYear())
                .bcdStatus(user.getBcdStatus())
                .mdStatus(user.getMdStatus())
                .ddStatus(user.getDdStatus())
                .build()).orElse(null);
    }
}
