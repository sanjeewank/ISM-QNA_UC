package Group02.QNA.Services;

import Group02.QNA.Models.CustomUserDetails;
import Group02.QNA.Models.User;
import Group02.QNA.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class customUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByEmail(username);
        if(user==null){
            throw  new UsernameNotFoundException("user not found");
        }else{
            return new CustomUserDetails(user);
        }
    }
}
