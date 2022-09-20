package Fandom.Jojo.Repository;

import Fandom.Jojo.Models.Stand;
import Fandom.Jojo.Models.Standv;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StandvRepository extends CrudRepository<Standv, Long> {

    public List<Standv> findByIma(String Ima);
    public List<Standv> findByImaContains(String Ima);
}