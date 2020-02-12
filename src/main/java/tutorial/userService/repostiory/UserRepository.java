package tutorial.userService.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tutorial.userService.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
