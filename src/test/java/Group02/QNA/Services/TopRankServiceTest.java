package Group02.QNA.Services;

import Group02.QNA.Models.*;
import Group02.QNA.Repository.AnswerRepository;
import Group02.QNA.Repository.QuestionRepository;
import Group02.QNA.Repository.RankRepository;
import Group02.QNA.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TopRankServiceTest {
    @Autowired
    RankRepository rankrepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnswerRepository answerRepository;
    private TopRankService toprankservice;

    private ArrayList<TopRank> rankings;
    private List<User> authors;
    private List<Question> questions;
    private List<Answer> answers;
    private List<Rank> ranking;
    private  TopRank topRank=new TopRank();
    private User user=new User();
    private int likes = 1;
    private int answerid =50;
    private int total_likes = 0;
    private List<Rank> answer_likes;

    private int TotalScore = 0;
    private ArrayList<TopRank> topRankArrayList;

    @Test
 public void rankTestUser(){


        assertThat(rankrepository.findAllByUser(userRepository.findByUserName("sanjeewank@gmail.com")).equals(likes));


 }
    @Test
    public void rankTestbyAnswer(){


        assertThat(rankrepository.findAllByAnswer(answerRepository.findById(answerid)).equals(likes));


    }
    @Test
    public void topTest(){
        user = userRepository.findByUserName("sanjeewank@gmail.com");
        questions = questionRepository.findAllByAuthor(user.getUserName());
        answers =  answerRepository.findAnswersByAuthor(user.getUserName());
        ranking = rankrepository.findAllByUser(user);
        answer_likes = rankrepository.findAllByAnswer(answers.get(0));
        total_likes = total_likes + answer_likes.size();
        TotalScore = questions.size()*5 + answers.size()*10 + total_likes*20;

        topRank.setUser(user);
        topRank.setScore(TotalScore);
        rankings.add(topRank);


        assertThat(toprankservice.CalculateRank().equals(topRankArrayList));


    }






}