package Fandom.Jojo.Repository;

import Fandom.Jojo.Models.Standv;
import Fandom.Jojo.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
        public User findByLogin(String login);

        public List<User> findById(String login);
}
