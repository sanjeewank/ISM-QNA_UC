package Group02.QNA.Controllers;

import Group02.QNA.Models.Answer;
import Group02.QNA.Models.Question;
import Group02.QNA.Models.Rank;
import Group02.QNA.Repository.AnswerRepository;
import Group02.QNA.Repository.QuestionRepository;
import Group02.QNA.Repository.RankRepository;
import Group02.QNA.Repository.UserRepository;
import Group02.QNA.Services.AnswerService;
import Group02.QNA.Services.QuestionService;
import Group02.QNA.Services.RankService;
import Group02.QNA.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Controller
@RequestMapping("/app")
public class AppController {
    @Autowired
    RankService rankService;

    @Autowired
    QuestionService questionService;


    @Autowired
    UserService userService;

    @Autowired
    AnswerService answerService;


    @PostMapping(value = "/questions/ask")
    public String askPost(@ModelAttribute Question question, final ModelMap model, Principal principal) {
        Question Question=questionService.askQuestion(question,principal);
        model.addAttribute("question", Question);
        return "redirect:/app";
    }

    @GetMapping(value = "/question/{id}")
    public String question(final ModelMap model, @PathVariable int id,Principal principal) {
        Question question=questionService.findById(id);
        Answer answer=questionService.newAnswer(question);
        Rank rank=new Rank();
        model.addAttribute("question",question);
        model.addAttribute("answer",answer);
        model.addAttribute("rank",rank);
        model.addAttribute("ranks",questionService.getRanks(principal));
        model.addAttribute("userid",userService.getUserID(principal));
        model.addAttribute("allAnswers",answerService.getAllByQuestions(question));
        return "Question";
    }
    @PostMapping(value = "/question/{id}")
    public String questionAnswer(final ModelMap model, @PathVariable int id,@ModelAttribute Answer ans,Principal principal){
        Question question=questionService.findById(id);
        answerService.answerSave(ans,principal,question);
        Answer answer=new Answer();
        Rank rank=new Rank();
        answer.setQuestion(question);
        model.addAttribute("question",question);
        model.addAttribute("rank",rank);
        model.addAttribute("answer",answer);
        model.addAttribute("ranks",questionService.getRanks(principal));
        model.addAttribute("userid",userService.getUserID(principal));
        model.addAttribute("allAnswers",answerService.getAllByQuestions(question));
        return "Question";
    }

    @GetMapping(value = "AnswerRank/{id}")
    public String answerRank(final ModelMap model,@ModelAttribute Rank rank, @PathVariable int id,Principal principal){
        rankService.saveRank(id,principal,rank);
        return "index";
    }
}
