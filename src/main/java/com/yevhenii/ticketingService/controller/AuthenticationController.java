package com.yevhenii.ticketingService.controller;

import com.yevhenii.ticketingService.controller.util.SecurityUtils;
import com.yevhenii.ticketingService.controller.util.SessionUtils;
import com.yevhenii.ticketingService.security.pojo.SignUpRequest;
import com.yevhenii.ticketingService.service.FileStorageService;
import com.yevhenii.ticketingService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController {

    SecurityUtils securityUtils;
    SessionUtils sessionUtils;
    UserService userService;
    FileStorageService fileStorageService;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationController(SecurityUtils securityUtils,
                                    SessionUtils sessionUtils,
                                    UserService userService,
                                    PasswordEncoder passwordEncoder,
                                    AuthenticationManager authenticationManager,
                                    FileStorageService fileStorageService) {
        this.securityUtils = securityUtils;
        this.sessionUtils = sessionUtils;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping({"/index", "/"})
    public String index() {
        return "index";
    }

    @GetMapping({"/register"})
    public String register() {
        return "register";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

//

    @PostMapping("/login")
    public RedirectView login(@RequestParam String email,
                              @RequestParam String password,
                              HttpSession session) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (!securityUtils.checkAdmin()) {
            System.out.println("Login success");

            return new RedirectView("/application");
        }
        else {

            System.out.println("Login success");
            return  new RedirectView("/admin/applications");
        }
    }

    @PostMapping("/register")
    public RedirectView register(@RequestParam String email,
                                 @RequestParam String password) {
        userService.register(new SignUpRequest(
                email,
                passwordEncoder.encode(password)
        ));
        return new RedirectView("login");
    }



}
