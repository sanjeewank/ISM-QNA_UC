package Group02.QNA.Repository;

import Group02.QNA.Models.Answer;
import Group02.QNA.Models.Rank;
import Group02.QNA.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankRepository extends JpaRepository<Rank,Integer> {
    List<Rank> findAllByAnswer(Answer answer);
    List<Rank> findAllByUser(User user);
}
