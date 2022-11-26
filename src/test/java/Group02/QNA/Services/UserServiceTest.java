package Group02.QNA.Services;

import Group02.QNA.Models.User;
import Group02.QNA.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserRepository userRepository;

    private UserService userService;

    @Test
    public void createUserTest(){
        User user=new User();
        user.setUserName("abkjkc@abc.com");
        user.setPassword("12345678");
        user.setFirstName("ABC");
        user.setLastName("XYZ");
        userRepository.save(user);
        assertThat(userRepository.getUserId("abc@abc.com").equals(2));

    }

}