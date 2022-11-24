package Group02.QNA.Controllers;


import Group02.QNA.Models.*;
import Group02.QNA.Repository.*;
import Group02.QNA.Services.RankService;
import Group02.QNA.Services.TopRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Controller
public class GUIController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;


    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private RankService rankService;

    @Autowired
    private TopRankService topRankService;


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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(User.getPassword());
        User.setPassword(encodedPassword);
        userRepo.save(User);
        return "index";
    }

    @GetMapping(value = "/app")
    public String getApp(Model model){
        Question question = new Question();
        question.setCategory(new Category("default"));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("questions", questionRepository.findAll());
        model.addAttribute("question", question);
        model.addAttribute("rankings",topRankService.CalculateRank());
        return "AppHome";
    }

    @PostMapping(value = "/questions/ask")
    public String askPost(@ModelAttribute Question question, final ModelMap model, Principal principal) {
        question.setAuthor(principal.getName());
        question.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC));
        if ("".equals(question.getCategory().getId())) {
            question.setCategory(null);
        }
        question = questionRepository.save(question);
        model.addAttribute("question", question);
        return "redirect:/app";
    }

    @GetMapping(value = "/question/{id}")
    public String question(final ModelMap model, @PathVariable int id,Principal principal) {
        Question question=questionRepository.findById(id).get();
        Answer answer=new Answer();
        Rank rank=new Rank();
        answer.setQuestion(question);
        model.addAttribute("question",question);
        model.addAttribute("answer",answer);
        model.addAttribute("rank",rank);
        model.addAttribute("ranks",rankRepository.findAllByUser(userRepo.findByUserName(principal.getName())));
        model.addAttribute("userid",userRepo.getUserId(principal.getName()));
        model.addAttribute("allAnswers",answerRepository.findAllByQuestion(question));
        return "Question";

    }
    @PostMapping(value = "/question/{id}")
    public String questionAnswer(final ModelMap model, @PathVariable int id,@ModelAttribute Answer ans,Principal principal){
        Question question=questionRepository.findById(id).get();
        ans.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC));
        ans.setAuthor(principal.getName());
        ans.setQuestion(question);
        answerRepository.save(ans);
        Answer answer=new Answer();
        Rank rank=new Rank();
        answer.setQuestion(question);
        model.addAttribute("question",question);
        model.addAttribute("answer",answer);
        model.addAttribute("rank",rank);
        model.addAttribute("ranks",rankRepository.findAllByUser(userRepo.findByUserName(principal.getName())));
        model.addAttribute("userid",userRepo.getUserId(principal.getName()));
        model.addAttribute("allAnswers",answerRepository.findAllByQuestion(question));
        return "Question";
    }

    @GetMapping(value = "AnswerRank/{id}")
    public String answerRank(final ModelMap model,@ModelAttribute Rank rank, @PathVariable int id,Principal principal){
        rankService.saveRank(id,principal,rank);
        return "index";
    }
}
