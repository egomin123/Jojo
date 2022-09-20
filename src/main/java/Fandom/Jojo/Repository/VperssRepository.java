package Fandom.Jojo.Repository;

import Fandom.Jojo.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VperssRepository extends CrudRepository<User,Long> {


    public List<User> findByLogin(String login);
}
