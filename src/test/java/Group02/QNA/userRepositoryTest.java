package Group02.QNA;

import Group02.QNA.Models.user;
import Group02.QNA.Repository.userRepository;
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
    private userRepository repo;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void testCreateUser() {
        user User =new user();
        User.setUserName("sanjeewa@gmail.com");
        User.setPassword("sanjeewa123");
        User.setFirstName("sanjeewa");
        User.setLastName("Kulathunga");
        user saved =repo.save(User);
    }


}
