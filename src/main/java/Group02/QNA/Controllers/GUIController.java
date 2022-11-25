package Group02.QNA.Controllers;


import Group02.QNA.Models.*;
import Group02.QNA.Repository.*;
import Group02.QNA.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Controller
public class GUIController {

    @Autowired
    private RankService rankService;

    @Autowired
    private TopRankService topRankService;

    @Autowired
    RegisterService registerService;

    @Autowired
    QuestionService questionService;

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/")
    public String getHomePage(){
        return "index";
    }

    @GetMapping(value = "/signup")
    public String getSignupPage(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping(value = "/register")
    public  String doRegistration(User User){
        registerService.saveUser(User);
        return "index";
    }

    @GetMapping(value = "/app")
    public String getApp(Model model){
        Question question = new Question();
        question.setCategory(new Category("default"));
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("questions", questionService.findAll());
        model.addAttribute("question", question);
        model.addAttribute("rankings",topRankService.CalculateRank());
        return "AppHome";
    }
}
