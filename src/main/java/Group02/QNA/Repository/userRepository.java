package Group02.QNA.Repository;

import Group02.QNA.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    public User findByEmail(String email);

    @Query("select u.id FROM User u where u.userName = ?1")
    public Integer getUserId(String id);

    User findByUserName(String username);

}
