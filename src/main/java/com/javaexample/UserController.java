package com.javaexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Autowired
    private UserProvider userProvider;

    @PostMapping("/register")
    public void register(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, HttpSession session) {
        System.out.println("register");
        userProvider.register(email, password, firstName, lastName);
    }

    @PostMapping("/login")
    public void login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
        if (!session.isNew()) {
            session.invalidate();
        }
        System.out.println(session.getId());
        userProvider.login(email, password);
    }
}
