package Group02.QNA.Controllers;


import Group02.QNA.Models.user;
import Group02.QNA.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GUIController {

    @Autowired
    private userRepository userRepo;

    @GetMapping(value = "/")
    public String getHomePage(){
        return "index";
    }

    @GetMapping(value = "/signup")
    public String getSignupPage(Model model){
        model.addAttribute("user", new user());
        return "signup";
    }

    @PostMapping(value = "/register")
    public  String doRegistration(user User){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(User.getPassword());
        User.setPassword(encodedPassword);
        userRepo.save(User);
        return "index";
    }

    @GetMapping(value = "/app")
    public String getApp(){
        return "AppHome";
    }

}
