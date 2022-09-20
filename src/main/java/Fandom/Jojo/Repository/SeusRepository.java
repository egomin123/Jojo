package Fandom.Jojo.Repository;

import Fandom.Jojo.Models.Seu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SeusRepository extends CrudRepository<Seu, Long> {

    public List<Seu> findByIma(String Ima);
    public List<Seu> findByImaContains(String Ima);

  //  public Seu findByIma(String ima);
}