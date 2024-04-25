package ro.amicus.archive.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.amicus.archive.entities.User;
import ro.amicus.archive.repositories.UserRepository;

import java.nio.charset.Charset;
import java.util.*;

@Service
@Slf4j
@Configuration
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final Calendar calendar;
    private final JwtConfiguration jwtConfiguration;
    private final AddressFormulaRepository addressFormulaRepository;
    private final UserInfoRepository userInfoRepository;

    public CustomUserDetailsService(UserRepository userRepository,
                                    JwtConfiguration jwtConfiguration) {
        this.userRepository = userRepository;
        this.userAuthorityRepository = userAuthorityRepository;
        this.jwtConfiguration = jwtConfiguration;
        this.addressFormulaRepository = addressFormulaRepository;
        this.userInfoRepository = userInfoRepository;
        this.calendar = Calendar.getInstance();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userCredentials = findByEmail(email);
        return userDetailsMapper.toUserDetails(userCredentials);
    }

    public UserDetails loadByUsernameOrPhoneNo(UserCredentialsRequestBody requestBody)
            throws UsernameNotFoundException {
        User userCredentials = this.retrieveUser(requestBody);
        return userDetailsMapper.toUserDetails(userCredentials);
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    public UsernamePasswordAuthenticationToken registerWithSocial(User user) {
        this.mailService.generateAndSendEmail(user);

        UserDetails userDetailsDto =
                this.loadUserByUsername(user.getEmail());

        return new UsernamePasswordAuthenticationToken(
                userDetailsDto.getUsername(),
                userDetailsDto.getPassword(),
                userDetailsDto.getAuthorities());
    }

    public UsernamePasswordAuthenticationToken registerUserWithFacebook(UserInfoFromTokenResponseBody facebookUserInfo) {
        String randomPassword = generateRandomString();

        User user = User.builder()
                .enabled(false)
                .email(facebookUserInfo.getEmail())
                .password(passwordEncoder.encode(randomPassword))
                .loginMethod(LoginMethodEnum.FACEBOOK.getLoginMethod())
                .build();

        User userCredentialsEntity = userRepository.save(user);

        UserInfoEntity userInfo = UserInfoEntity.builder()
                .user(user)
                .fullName(facebookUserInfo.getName())
                .build();

        userInfoRepository.save(userInfo);

        this.createContactDataForUser(
                UserCredentialsRequestBody.builder()
                        .fullName(facebookUserInfo.getName())
                        .email(facebookUserInfo.getEmail())
                        .build(),
                userCredentialsEntity);

        AuthorityEntity authority = userAuthorityRepository.save(
                AuthorityEntity.builder()
                        .userId(userCredentialsEntity.getId())
                        .authority(UserRoleEnum.CUSTOMER.getValue())
                        .enabled(Boolean.TRUE)
                        .active(Boolean.TRUE)
                        .build());
        log.debug("User with email: '{}' was created with id: '{}'!", facebookUserInfo.getEmail(), userCredentialsEntity.getId());

        if (userCredentialsEntity.getRoles() == null) {
            userCredentialsEntity.setRoles(new ArrayList<>());
        }
        userCredentialsEntity.getRoles().add(authority);

        return this.registerWithSocial(user);
    }

    public UsernamePasswordAuthenticationToken registerUserWithGoogle(Payload payload) {
        String randomPassword = generateRandomString();

        User user = User.builder()
                .enabled(false)
                .email(payload.getEmail())
                .password(passwordEncoder.encode(randomPassword))
                .loginMethod(LoginMethodEnum.GOOGLE.getLoginMethod())
                .build();

        User userCredentialsEntity = userRepository.save(user);

        String fullName = (String) payload.get("name");
        UserInfoEntity userInfo = UserInfoEntity.builder()
                .user(user)
                .fullName(fullName)
                .build();

        userInfoRepository.save(userInfo);

        this.createContactDataForUser(
                UserCredentialsRequestBody.builder()
                        .fullName(fullName)
                        .email(payload.getEmail())
                        .build(),
                userCredentialsEntity);

        AuthorityEntity authority = userAuthorityRepository.save(
                AuthorityEntity.builder()
                        .userId(userCredentialsEntity.getId())
                        .authority(UserRoleEnum.CUSTOMER.getValue())
                        .enabled(Boolean.TRUE)
                        .active(Boolean.TRUE)
                        .build());
        log.debug("User with email: '{}' was created with id: '{}'!", payload.getEmail(), userCredentialsEntity.getId());

        if (userCredentialsEntity.getRoles() == null) {
            userCredentialsEntity.setRoles(new ArrayList<>());
        }
        userCredentialsEntity.getRoles().add(authority);

        return this.registerWithSocial(user);
    }

    private String generateRandomString() {
        byte[] array = new byte[128]; // length is bounded by 7
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }


    private void createContactDataForUser(UserCredentialsRequestBody requestBody, UserEntity user) {
        String deductedFirstName = "";
        String deductedLastName = "";
        if (requestBody.getFullName() == null){
            requestBody.setFullName("");
        }
        String[] fullNameParts = requestBody.getFullName().split(" ");
        if (fullNameParts.length > 0){
            deductedFirstName = fullNameParts[0];
        }
        if (fullNameParts.length > 1){
            deductedLastName = String.join(" ", Arrays.copyOfRange(fullNameParts, 2, fullNameParts.length));
        }

        this.contactDataRepository.save(
                ContactDataEntity.builder()
                        .userId(user.getId())
                        .lastName(deductedLastName)
                        .firstName(deductedFirstName)
                        .addressFormula(requestBody.getAddressFormulaId() != null ? requestBody.getAddressFormulaId() : null)
                        .email(requestBody.getEmail() != null? requestBody.getEmail() : null)
                        .phoneNumber(requestBody.getPhoneNo() != null ? requestBody.getPhoneNo() : null)
                        .birthDate(requestBody.getBirthDate() != null ? requestBody.getBirthDate() : null)
                        .isImmutable(Boolean.TRUE)
                        .build()
        );
    }


    public UserEntity createUser(UserCredentialsRequestBody requestBody, String alreadyExistingContactDataId) {
        UserEntity user = UserEntity.builder()
                .enabled(false)
                .phoneNo(requestBody.getPhoneNo())
                .email(requestBody.getEmail())
                .password(passwordEncoder.encode(requestBody.getPassword()))
                .build();

        UserEntity userCredentialsEntity = userRepository.save(user);

        saveUserInfo(requestBody, user);

        if (alreadyExistingContactDataId != null){
            Optional<ContactDataEntity> existingContactDataOptional = this.contactDataRepository.findById(alreadyExistingContactDataId);
            if (existingContactDataOptional.isEmpty()){
                throw Exceptions.INVALID_ASSOCIATED_CONTACT_DATA
                        .withContext(Collections.singletonMap("contactDataId", alreadyExistingContactDataId));
            }
            ContactDataEntity existingContactData = existingContactDataOptional.get();
            if (existingContactData.getUserId() != null){
                throw Exceptions.INVALID_ASSOCIATED_CONTACT_DATA
                        .withContext(Collections.singletonMap("contactDataUserId", existingContactData.getUserId()));
            }
        }

        if (alreadyExistingContactDataId == null){
            this.createContactDataForUser(requestBody, userCredentialsEntity);
        }

        AuthorityEntity authority = userAuthorityRepository.save(
                AuthorityEntity.builder()
                        .userId(userCredentialsEntity.getId())
                        .authority(UserRoleEnum.CUSTOMER.getValue())
                        .enabled(Boolean.TRUE)
                        .active(Boolean.TRUE)
                        .build());
        log.debug("User with email: '{}' was created with id: '{}'!", requestBody.getEmail(), userCredentialsEntity.getId());

        if (userCredentialsEntity.getRoles() == null) {
            userCredentialsEntity.setRoles(new ArrayList<>());
        }
        userCredentialsEntity.getRoles().add(authority);
        return userCredentialsEntity;
    }

    private void saveUserInfo(UserCredentialsRequestBody requestBody, UserEntity user) {
        UserInfoEntity userInfo = UserInfoEntity.builder()
                .user(user)
                .fullName(requestBody.getFullName())
                .build();

        if (requestBody.getAddressFormulaId() != null) {
            Optional<AddressFormulaEntity> addressFormula = this.addressFormulaRepository
                    .findById(requestBody.getAddressFormulaId());

            if (addressFormula.isEmpty()) {
                throw Exceptions.FORMULA_ADDRESS_NOT_FOUND
                        .withContext(
                                Collections.singletonMap("addressFormulaId", requestBody.getAddressFormulaId()));
            }

            userInfo.setAddressFormula(addressFormula.get());
        }

        if (requestBody.getBirthDate() != null) {
            userInfo.setDateOfBirth(requestBody.getBirthDate());
        }

        this.userInfoRepository.save(userInfo);
    }

    public boolean validateUserCredentials(UserCredentialsRequestBody userCredentialsRequestBody) {
        if (!Objects.equals(captchaDisabled, "true")) {
            if (userCredentialsRequestBody.getRecaptchaResponse().equals(recaptchaInternalKey)){
                return true;
            }

            boolean validationResponse = recapthcaClient.validateRecaptchaResponse(userCredentialsRequestBody.getRecaptchaResponse());
            if (!validationResponse) {
                log.debug("Captcha entered by user with email: '{}'/ phoneNo: '{}' is invalid.", userCredentialsRequestBody.getEmail(), userCredentialsRequestBody.getPhoneNo());
                throw Exceptions.CAPTCHA_INVALID;
            }
        }

        User user = retrieveActiveUser(userCredentialsRequestBody);
        return passwordEncoder.matches(userCredentialsRequestBody.getPassword(), user.getPassword());
    }

    public User retrieveActiveUser(UserCredentialsRequestBody userCredentialsRequestBody) {
        if (userCredentialsRequestBody.getEmail() != null) {
            return userRepository.findByEmailAndEnabledIsTrue(userCredentialsRequestBody.getEmail());
        } else {
            return userRepository.findByPhoneNoAndEnabledIsTrue(userCredentialsRequestBody.getPhoneNo());
        }
    }

    public User retrieveUser(UserCredentialsRequestBody userCredentialsRequestBody) {
        if (userCredentialsRequestBody.getEmail() != null) {
            return userRepository.findByEmail(userCredentialsRequestBody.getEmail());
        } else {
            return userRepository.findByPhoneNo(userCredentialsRequestBody.getPhoneNo());
        }
    }

    public boolean userWithEmailOrPhoneNoExists(UserCredentialsRequestBody requestBody) {
        List<User> allByEmail = this.userRepository.findAllByEmail(requestBody.getEmail());
        List<User> allByPhoneNo = this.userRepository.findAllByPhoneNo(requestBody.getPhoneNo());
        return (allByEmail != null && allByEmail.size() > 0) ||
                (allByPhoneNo != null && allByPhoneNo.size() > 0);
    }

    public String getActiveRoleForEmail(String email) {
        return userAuthorityRepository.findActiveAuthorityForEmail(email).getAuthority();
    }

    public TokenResponse generateJwtToken(String userName) {
        calendar.setTimeInMillis(System.currentTimeMillis() + Long.parseLong(jwtConfiguration.getJwtExpirationTime()));

        String activeRole = this.getActiveRoleForEmail(userName);

        String token = JWT.create()
                .withSubject(userName)
                .withExpiresAt(calendar.getTime())
                .withClaim(ACTIVE_ROLE, activeRole)
                .sign(Algorithm.HMAC512(jwtConfiguration.getJwtSecret().getBytes()));

        return TokenResponse.builder()
                .token(token)
                .validUntil(String.valueOf(calendar.getTimeInMillis()))
                .activeRole(activeRole)
                .build();
    }

}
