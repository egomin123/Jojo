package Fandom.Jojo.Repository;


import Fandom.Jojo.Models.Pers;
import Fandom.Jojo.Models.Seu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersRepository extends CrudRepository<Pers,Long> {


    public List<Pers> findByImapers(String Imapers);
    public List<Pers> findByImapersContains(String Imapers);
}