package Group02.QNA.Services;

import Group02.QNA.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public int getUserID(Principal principal){
        return userRepository.getUserId(principal.getName());
    }
}
