package com.dev.manicure.service;

import com.dev.manicure.auth.JwtService;
import com.dev.manicure.auth.AuthenticationResponse;
import com.dev.manicure.entity.Token;
import com.dev.manicure.entity.User;
import com.dev.manicure.entity.enums.Role;
import com.dev.manicure.entity.enums.TokenType;
import com.dev.manicure.repository.TokenRepository;
import com.dev.manicure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenRepository tokenRepository;


    public AuthenticationResponse register(User userHolder) {

        Optional<User> userExists = userRepository.findByEmail(userHolder.getEmail());
        if (!userExists.isEmpty()) {
            throw new RuntimeException("User already exists!");
        } else {
            var user = User.builder()
                    .firstname(userHolder.getUsername())
                    .lastName(userHolder.getLastName())
                    .password(passwordEncoder.encode(userHolder.getPassword()))
                    .email(userHolder.getEmail())
                    .phone(userHolder.getPhone())
                    .birth(userHolder.getBirth())
                    .packageMonthly(userHolder.getPackageMonthly())
                    .role(Role.USER)
                    .build();
            var saveUser = userRepository.save(user);
            var token = jwtService.generateToken(user);
            saveUserToken(user,token);

            return AuthenticationResponse.builder().token(token).build();
        }
    }

    public AuthenticationResponse authentication(User userHolder) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userHolder.getEmail(),
                        userHolder.getPassword()
                )
        );
        var user = userRepository.findByEmail(userHolder.getEmail()).orElseThrow();
        var token = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user,token);
        return AuthenticationResponse.builder().token(token).build();
    }

    public List<User> userList() {
        List<User> user = userRepository.findAll();
        return user;
    }

    public User updateUser(Long id, User userHolder) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        user.setFirstname(userHolder.getUsername());
        user.setPassword(userHolder.getPassword());
        user.setBirth(userHolder.getBirth());
        user.setPhone(userHolder.getPhone());
        user.setEmail(userHolder.getEmail());
        return userRepository.save(user);
    }

    public ResponseEntity<User> deleteUser (Long id){
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}