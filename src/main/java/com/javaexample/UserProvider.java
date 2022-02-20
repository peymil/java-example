package com.javaexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class UserProvider {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email);
        System.out.println(user.getPassword());
        Boolean isPasswordMatches = passwordEncoder.matches(password, user.getPassword());

        if(isPasswordMatches){
            System.out.println("matches");
        }
        System.out.println("no matches");
        return "";
    }

    public void register(String email, String password, String firstName, String lastName) {
        String securePassword = passwordEncoder.encode(password);
        System.out.println("securePassword");
        System.out.println(securePassword);
        User user = new User(email, securePassword, firstName, lastName);
        userRepository.save(user);
    }
}
