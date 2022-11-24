package Group02.QNA.Services;

import Group02.QNA.Models.Answer;
import Group02.QNA.Models.Rank;
import Group02.QNA.Models.User;
import Group02.QNA.Repository.AnswerRepository;
import Group02.QNA.Repository.RankRepository;
import Group02.QNA.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class RankService {

    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserRepository userRepo;


    public void saveRank(int id, Principal principal, Rank rank){
        Answer answer=answerRepository.findById(id);
        User user=userRepo.findByUserName(principal.getName());
        List<Rank> ranks =rankRepository.findAllByUser(userRepo.findByUserName(principal.getName()));
        int dummy = 0;
        for(int i=0;i<ranks.size();i++){
            if (ranks.get(i).getAnswer() == answer){
                dummy = dummy+1;
            };
        }
        if (dummy==0){
            rank.setIsRanked(Boolean.TRUE);
            rank.setAnswer(answer);
            rank.setUser(user);
            rankRepository.save(rank);
        }
    }

}
