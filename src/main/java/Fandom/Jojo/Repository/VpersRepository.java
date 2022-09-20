package Fandom.Jojo.Repository;

import Fandom.Jojo.Models.Pers;
import Fandom.Jojo.Models.Vpers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VpersRepository  extends CrudRepository<Vpers,Long> {


    public List<Vpers> findByImavpers(String Imavpers);
    public List<Vpers> findByImavpersContains(String Imavpers);
}