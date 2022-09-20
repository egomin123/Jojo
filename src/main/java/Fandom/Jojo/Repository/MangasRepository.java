package Fandom.Jojo.Repository;

import Fandom.Jojo.Models.Manga;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MangasRepository extends CrudRepository<Manga, Long> {

    public List<Manga> findByIma(String Ima);
    public List<Manga> findByImaContains(String Ima);
}