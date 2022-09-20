package Fandom.Jojo.Repository;

import Fandom.Jojo.Models.Sozdatel;
import Fandom.Jojo.Models.Video;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VideosRepository extends CrudRepository<Video, Long> {

    public List<Video> findByIma(String Ima);
    public List<Video> findByImaContains(String Ima);
}