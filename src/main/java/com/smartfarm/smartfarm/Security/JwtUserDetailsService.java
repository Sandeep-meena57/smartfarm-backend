package com.smartfarm.smartfarm.Security;


import com.smartfarm.smartfarm.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user -> org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
//                        .roles(user.getRole().name()) // Must be FARMER or ADMIN
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}

