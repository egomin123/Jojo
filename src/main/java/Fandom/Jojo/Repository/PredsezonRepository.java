package Fandom.Jojo.Repository;

import Fandom.Jojo.Models.Predsezon;
import Fandom.Jojo.Models.Sezon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PredsezonRepository  extends CrudRepository<Predsezon,Long> {


    public List<Predsezon> findByNamepredsezon(String Namepredsezon);
    public List<Predsezon> findByNamepredsezonContains(String Namepredsezon);
}