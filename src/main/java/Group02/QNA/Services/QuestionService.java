package Group02.QNA.Services;

import Group02.QNA.Models.Answer;
import Group02.QNA.Models.Question;
import Group02.QNA.Models.Rank;
import Group02.QNA.Repository.QuestionRepository;
import Group02.QNA.Repository.RankRepository;
import Group02.QNA.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RankRepository rankRepository;


    public Question askQuestion(Question question,Principal principal){
        question.setAuthor(principal.getName());
        question.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC));
        if ("".equals(question.getCategory().getId())) {
            question.setCategory(null);
        }
        question = questionRepository.save(question);
        return question;
    }

    public Answer newAnswer(Question question){
        Answer answer=new Answer();
        answer.setQuestion(question);
        return answer;
    }

    public List<Question> findAll(){
        return questionRepository.findAll();
    }

    public List<Rank> getRanks(Principal principal){
        List<Rank> ranks=new LinkedList<>();
        ranks=rankRepository.findAllByUser(userRepo.findByUserName(principal.getName()));
        return ranks;
    }

    public Question findById(int id){
        return questionRepository.findById(id).get();
    }
}
