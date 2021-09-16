package com.buddylogin.demo.appuser;

import com.buddylogin.demo.Registration.token.ConfirmationToken;
import com.buddylogin.demo.Registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService, UserDetailsPasswordService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));

    }

    @Override
    public UserDetails updatePassword(UserDetails userDetails, String s) {
        return null;  //recommended by software

    }
    public String signUpUser(AppUser appUser) {         //Return link for user confirmation
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {
            //send confirmation again if not confirmed first time
            throw new IllegalStateException("email already exists");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser); // saves each user to DB

        String token = UUID.randomUUID().toString();
//        TODO: SEND confirmation token   // sends token for confirmation, without this "user is disabled" error on login page

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

        return token;

        //TODO: SEND EMAIL
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
