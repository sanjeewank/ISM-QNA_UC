package Group02.QNA.Repository;

import Group02.QNA.Models.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface userRepository extends JpaRepository<user,Integer> {
    @Query("SELECT u FROM user u WHERE u.userName = ?1")
    public user findByEmail(String email);

}
