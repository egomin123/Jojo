package Fandom.Jojo.Repository;

import Fandom.Jojo.Models.Pers;
import Fandom.Jojo.Models.Vsu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VsuRepository  extends CrudRepository<Vsu,Long> {


    public List<Vsu> findByIma(String Ima);
    public List<Vsu> findByImaContains(String Ima);
}