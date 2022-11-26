package Group02.QNA.Services;

import Group02.QNA.Models.Answer;
import Group02.QNA.Models.Message;
import Group02.QNA.Models.Question;
import Group02.QNA.Repository.AnswerRepository;
import Group02.QNA.Repository.QuestionRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest

class QuestionServiceTest {
@Autowired
    private AnswerRepository answerRepository;
@Autowired
    private QuestionRepository questionRepository;
private QuestionService questionService;
private Question question;
private Question questionList;
private Question testQuestion;
private Answer testAnswer;
@BeforeEach
    private void beforeEach(){questionService = new QuestionService(questionRepository,answerRepository);}

//    @Test
//    public void getQuestionTest(){
//
//    testQuestion.setContent("this is my test question");
//    testQuestion.setAuthor("sanjeewa@gmail.com");
//    testQuestion.setTitle("test");
//    questionRepository.save(testQuestion);
//
//
//
//    AssertionsForClassTypes.assertThat(questionRepository.findByAuthor("sanjeewa@gmail.com").get(0).equals(testQuestion));
//
//    }


}