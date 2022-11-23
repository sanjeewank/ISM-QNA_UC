package Group02.QNA;

import Group02.QNA.Models.User;
import Group02.QNA.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class userRepositoryTest {
    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void testCreateUser() {
        User User =new User();
        User.setUserName("sanjeewa@gmail.com");
        User.setPassword("sanjeewa123");
        User.setFirstName("sanjeewa");
        User.setLastName("Kulathunga");
        Group02.QNA.Models.User saved =repo.save(User);
    }


}
