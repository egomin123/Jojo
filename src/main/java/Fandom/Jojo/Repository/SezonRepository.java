package Fandom.Jojo.Repository;

import Fandom.Jojo.Models.Pers;
import Fandom.Jojo.Models.Sezon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SezonRepository  extends CrudRepository<Sezon,Long> {


    public List<Sezon> findByNamesezon(String Namesezon);
    public List<Sezon> findByNamesezonContains(String Namesezon);
}