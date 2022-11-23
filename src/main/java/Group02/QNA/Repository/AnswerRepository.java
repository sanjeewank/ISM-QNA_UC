package Group02.QNA.Repository;

import Group02.QNA.Models.Answer;
import Group02.QNA.Models.Question;
import Group02.QNA.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findById(String Id);

    List<Answer> findAllByQuestion(Question question);
}
