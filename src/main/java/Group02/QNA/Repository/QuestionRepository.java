package Group02.QNA.Repository;

import Group02.QNA.Models.Category;
import Group02.QNA.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(Category category);
    List<Question> findByAuthor(String author);
}
