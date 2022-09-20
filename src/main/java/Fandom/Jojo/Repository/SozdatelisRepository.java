package Fandom.Jojo.Repository;

import Fandom.Jojo.Models.Sozdatel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SozdatelisRepository extends CrudRepository<Sozdatel, Long> {

    public List<Sozdatel> findByIma(String Ima);
    public List<Sozdatel> findByImaContains(String Ima);
}