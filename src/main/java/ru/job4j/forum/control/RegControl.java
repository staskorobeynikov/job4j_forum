package ru.job4j.forum.control;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.AuthorityRepository;
import ru.job4j.forum.repository.UserRepository;

@Controller
public class RegControl {

    private static final Logger LOG = LogManager.getLogger(RegControl.class);

    private final PasswordEncoder encoder;

    private final UserRepository users;

    private final AuthorityRepository authorities;

    public RegControl(PasswordEncoder encoder,
                      UserRepository users,
                      AuthorityRepository authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    @PostMapping("/reg")
    public String save(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String index(
            @RequestParam(value = "error", required = false) String error,
            Model model
    ) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "User with this username is already registered!!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "reg";
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public String exceptionHandler(Exception e) {
        LOG.error("Нарушение уникальности поля username при регистрации пользователя.", e);
        return "redirect:/reg?error=true";
    }
}
