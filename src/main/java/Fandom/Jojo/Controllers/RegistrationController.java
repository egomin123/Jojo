package Fandom.Jojo.Controllers;

import Fandom.Jojo.Models.Role;
import Fandom.Jojo.Models.User;
import Fandom.Jojo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("registration")
    public String reg_view(Model model)
    {
        return("registration");
    }

    @PostMapping("registration")
    public String reg_action(User user, Model model)
    {
        User userFromDB = userRepository.findByLogin(user.getLogin());
        if(userFromDB != null)
        {
            model.addAttribute("error","Такой пользователь уже есть");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()) );
        userRepository.save(user);
        return "redirect:/login";
    }
    @Autowired
    private PasswordEncoder passwordEncoder;

}

