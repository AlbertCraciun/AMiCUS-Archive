package ro.amicus.archive.security;

import ch.qos.logback.core.encoder.Encoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.amicus.archive.entities.MyUser;
import ro.amicus.archive.repositories.MyUserRepository;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final MyUserRepository myUserRepository;

    public MyUserDetailService(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<MyUser> myUser = myUserRepository.findByEmail(email);
        if (myUser.isPresent()) {
            var userObj = myUser.get();
            return User.withUsername(userObj.getEmail())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    private String[] getRoles(MyUser user) {
        String role = String.valueOf(user.getUserRole().getPrivilege().getName());
        if (role == null) {
            return new String[]{"USER"};
        }
        else if (role.equals("ADMIN")) {
            return new String[]{"ADMIN", "USER"};
        }
        else if (role.equals("SUPERADMIN")) {
            return new String[]{"SUPERADMIN", "ADMIN", "USER"};
        } else {
            return new String[]{"USER"};
        }
    }

}
