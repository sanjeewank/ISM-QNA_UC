package Group02.QNA.Services;

import Group02.QNA.Models.Answer;
import Group02.QNA.Models.Question;
import Group02.QNA.Repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> getAllByQuestions(Question question){
        return  answerRepository.findAllByQuestion(question);
    }

    public void answerSave(Answer ans, Principal principal,Question question){
        ans.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC));
        ans.setAuthor(principal.getName());
        ans.setQuestion(question);
        answerRepository.save(ans);
    }


}
