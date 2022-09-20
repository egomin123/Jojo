package Fandom.Jojo.Repository;

import Fandom.Jojo.Models.Stand;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StandRepository extends CrudRepository<Stand, Long> {

    public List<Stand> findByIma(String Ima);
    public List<Stand> findByImaContains(String Ima);

    //public Stand findByIma(String ima);
}