package Group02.QNA.Services;

import Group02.QNA.Models.*;
import Group02.QNA.Repository.AnswerRepository;
import Group02.QNA.Repository.QuestionRepository;
import Group02.QNA.Repository.RankRepository;
import Group02.QNA.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopRankService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private UserRepository userRepository;


    public ArrayList<TopRank> CalculateRank(){
        ArrayList<TopRank> rankings=new ArrayList<>();
        List<User> authors=userRepository.findAll();
        for(User author:authors){
            List<Question> questions = questionRepository.findAllByAuthor(author.getUserName());
            List<Answer> answers = answerRepository.findAnswersByAuthor(author.getUserName());
            List<Rank> ranking = rankRepository.findAllByUser(author);
            int total_likes=0;
            for (int i =0; i<answers.size();i++) {
                List<Rank> answer_likes = rankRepository.findAllByAnswer(answers.get(i));
                total_likes = total_likes + answer_likes.size();
            }
            int TotalScore=questions.size()*5 + answers.size()*10 + total_likes*20;
            TopRank topRank=new TopRank();
            topRank.setUser(author);
            topRank.setScore(TotalScore);
            rankings.add(topRank);
        }
        return rankings;
    }
}
