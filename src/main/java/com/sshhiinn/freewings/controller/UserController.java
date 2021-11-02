package com.sshhiinn.freewings.controller;

import com.sshhiinn.freewings.exception.UserAlreadyRegistered;
import com.sshhiinn.freewings.exception.UserNotFound;
import com.sshhiinn.freewings.model.User;
import com.sshhiinn.freewings.repository.UserRepository;
import com.sshhiinn.freewings.service.SecurityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Tag(name = "User", description = "Users API")
@Controller
public class UserController {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final SecurityService securityService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, SecurityService securityService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.securityService = securityService;
    }

    @Operation(summary = "Show create user")
    @RequestMapping("/showReg")
    public String showRegistrationPage() {
        LOGGER.info("Inside showRegistrationPage()");
        return "login/registerUser";
    }

    @Operation(summary = "Create user")
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user) {
        LOGGER.info("{} Inside register()", user.getEmail());
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        if (foundUser.isPresent()) {
            LOGGER.error("User is already registered with email {} ", user.getEmail());
            throw new UserAlreadyRegistered("Email exists: " + user.getEmail());
        }
        LOGGER.info("Email Exists: " + user.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "login/login";
    }

    @Operation(summary = "Show login")
    @RequestMapping("/showLogin")
    public String showLoginPage() {
        LOGGER.info("Inside showLoginPage()");
        return "login/login";
    }

    @Operation(summary = "Login")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelmap) {
        LOGGER.info("{} Inside login()", email);
        Optional<User> foundUser = userRepository.findByEmail(email);
        if (!foundUser.isPresent()) {
            LOGGER.error("Email not found: " + email);
            throw new UserNotFound("Email not found: " + email);
        }
        LOGGER.info("Email Exists: " + email);
        boolean loginResponse = securityService.login(email, password);
        if (loginResponse) {
            modelmap.addAttribute("msg", "Successfully logged in");
            return "menu";
        } else {
            LOGGER.info("User entered Invalid credentials email:{} and password:{}", email, password);
            modelmap.addAttribute("msg", "Invalid username or password");
        }
        return "login/login";
    }

}
