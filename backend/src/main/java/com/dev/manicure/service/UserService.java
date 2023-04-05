package com.dev.manicure.service;

import com.dev.manicure.auth.JwtService;
import com.dev.manicure.auth.AuthenticationResponse;
import com.dev.manicure.entity.User;
import com.dev.manicure.entity.enums.Role;
import com.dev.manicure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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



    public AuthenticationResponse register(User userHolder) {

        Optional<User> userExists = userRepository.findByEmail(userHolder.getEmail());
        if (!userExists.isEmpty()) {
            throw new RuntimeException("User already exists!");
        } else {
            var user = User.builder()
                    .firstname(userHolder.getUsername())
                    .lastName(userHolder.getLastName())
                    .birth(userHolder.getBirth())
                    .password(passwordEncoder.encode(userHolder.getPassword()))
                    .email(userHolder.getEmail())
                    .phone(userHolder.getPhone())
                    .packageMonthly(userHolder.getPackageMonthly())
                    .role(Role.USER)
                    .build();
            var saveUser = userRepository.save(user);
            var token = jwtService.generateToken(user);

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

        return AuthenticationResponse.builder().token(token).build();
    }

    public List<User> userList() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            Date birthDate = user.getBirth();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String formattedBirthDate = formatter.format(birthDate);
            user.setFormattedBirthDate(formattedBirthDate);
        }
        return users;
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
        userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}