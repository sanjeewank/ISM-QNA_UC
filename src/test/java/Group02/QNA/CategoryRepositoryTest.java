package Group02.QNA;

import Group02.QNA.Models.Category;
import Group02.QNA.Models.User;
import Group02.QNA.Repository.CategoryRepository;
import Group02.QNA.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void createCategory() {
        Category cat =new Category();
        cat.setId("java");
        repo.save(cat);
    }

}
