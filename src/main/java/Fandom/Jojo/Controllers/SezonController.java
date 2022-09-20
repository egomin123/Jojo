package Fandom.Jojo.Controllers;


import Fandom.Jojo.Models.*;
import Fandom.Jojo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Sezon")
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class SezonController {

    @Autowired
    private SezonRepository sezonRepository;
    @Autowired
    private SozdatelisRepository sozdatelisRepository;
    @Autowired
    private VideosRepository videosRepository;
    @Autowired
    private MangasRepository mangasRepository;
    @Autowired
    private PersRepository persRepository;


    @GetMapping("/")
    public  String Index(Model model){

        Iterable<Sezon> sezons = sezonRepository.findAll();
        model.addAttribute("sezon", sezons);

        Iterable<Sozdatel> sozdatels = sozdatelisRepository.findAll();
        model.addAttribute("sozdatel", sozdatels);

        Iterable<Video> videos = videosRepository.findAll();
        model.addAttribute("video", videos);

        Iterable<Manga> mangas = mangasRepository.findAll();
        model.addAttribute("manga", mangas);

        Iterable<Pers> pers = persRepository.findAll();
        model.addAttribute("pers", pers);
        return "Sezon/Index";
    }


    @GetMapping("/add")
    public String addView(Model model)
    {

        Iterable<Sozdatel> sozdatel1 = sozdatelisRepository.findAll();
        model.addAttribute("sozdatel", sozdatel1);

        Iterable<Video> video1 = videosRepository.findAll();
        model.addAttribute("video", video1);

        Iterable<Manga> manga1 = mangasRepository.findAll();
        model.addAttribute("manga", manga1);

        Iterable<Pers> pers1 = persRepository.findAll();
        model.addAttribute("pers", pers1);

        return "Sezon/AddSezon";
    }







    @PostMapping("/add")
    public String blogPostAdd(@RequestParam("namesezon") String namesezon,
                              @RequestParam ("datavixoda") Integer datavixoda,
                              @RequestParam ("kolvoseria") Integer kolvoseria,
                              @RequestParam ("video") String video,
                              @RequestParam ("sozdatel") String sozdatel,
                              @RequestParam ("manga") String manga,
                              @RequestParam ("pers") String pers,
                              @RequestParam ("dataanonsa") Integer dataanonsa,
                              @RequestParam ("datakonca") Integer datakonca,
                              Model model)
    {

        List<Video> videos = videosRepository.findByIma(video);
        List<Sozdatel> sozdatels = sozdatelisRepository.findByIma(sozdatel);
        List<Manga> mangas = mangasRepository.findByIma(manga);
        List<Pers> pers1 = persRepository.findByImapers(pers);



        Sezon sezon = new Sezon(namesezon, datavixoda, kolvoseria, videos.get(0), sozdatels.get(0), mangas.get(0), pers1.get(0),dataanonsa, datakonca);
        sezonRepository.save(sezon);


        model.addAttribute("namesezon", namesezon);
        model.addAttribute("datavixoda", datavixoda);
        model.addAttribute("kolvoseria", kolvoseria);
        model.addAttribute("dataanonsa", dataanonsa);
        model.addAttribute("datakonca", datakonca);

        Iterable<Video> videos1 = videosRepository.findAll();
        model.addAttribute("video", videos1);

        Iterable<Sozdatel> sozdatel1 = sozdatelisRepository.findAll();
        model.addAttribute("sozdatel", sozdatel1);

        Iterable<Manga> mangas1 = mangasRepository.findAll();
        model.addAttribute("manga", mangas1);

        Iterable<Pers> pers2 = persRepository.findAll();
        model.addAttribute("pers",pers2);

        Iterable<Sezon> sezons1 = sezonRepository.findAll();
        model.addAttribute("sezon",sezons1);

        return "Sezon/Index";
    }

    @GetMapping("/Search")
    public String GetAdd(
            @RequestParam("namesezon") String namesezon,
            Model model)
    {
        List<Sezon> sezons = sezonRepository.findByNamesezon(namesezon);
        model.addAttribute("sezon", sezons);
        return "Sezon/Index";
    }

    @GetMapping("/Searchs")
    public String GetAdds(
            @RequestParam("namesezon") String namesezon,
            Model model)
    {
        List<Sezon> sezonsList = sezonRepository.findByNamesezonContains(namesezon);
        model.addAttribute("sezon", sezonsList);
        return "Sezon/Index";
    }

    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Sezon> sezon = sezonRepository.findById(id);
        ArrayList<Sezon> kolodsArrayList =  new ArrayList<>();
        sezon.ifPresent(kolodsArrayList::add);
        model.addAttribute("sezon", kolodsArrayList);
        return "Sezon/Info-Sezon";
    }


    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Sezon sezon = sezonRepository.findById(id).orElseThrow();
        sezonRepository.delete(sezon);

        return "redirect:/Sezon/";
    }




    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!sezonRepository.existsById(id) )
        {
            return "redirect:/Sezon/";
        }
        Iterable<Video> videos = videosRepository.findAll();
        model.addAttribute("video", videos);

        Iterable<Sozdatel> sozdatels = sozdatelisRepository.findAll();
        model.addAttribute("sozdatel", sozdatels);

        Iterable<Manga> mangas = mangasRepository.findAll();
        model.addAttribute("manga", mangas);

        Iterable<Pers> perss = persRepository.findAll();
        model.addAttribute("pers", perss);



        Optional<Sezon> sezon = sezonRepository.findById(id);
        ArrayList<Sezon> sezonsArrayList =  new ArrayList<>();
        sezon.ifPresent(sezonsArrayList::add);
        model.addAttribute("sezons", sezonsArrayList.get(0));
        model.addAttribute("Sezons", sezonsArrayList);
        model.addAttribute("namesezon", sezon.get().getnamesezon());
        model.addAttribute("datavixoda", sezon.get().getdatavixoda());
        model.addAttribute("kolvoseria", sezon.get().getkolvoseria());
        model.addAttribute("dataanonsa", sezon.get().getdataanonsa());
        model.addAttribute("datakonca", sezon.get().getdatakonca());


        return "Sezon/Edit-Sezon";
    }



    @PostMapping("/edit/{id}")
    public String editKolods(
            @PathVariable("id") Long id,

            @ModelAttribute("sezons") @Valid Sezon sezon,
            BindingResult bindingResult,
            @RequestParam ("namesezon") String namesezon,
            @RequestParam ("datavixoda") Integer datavixoda,
            @RequestParam ("kolvoseria") Integer kolvoseria,
            @RequestParam ("video") String video,
            @RequestParam ("sozdatel") String sozdatel,
            @RequestParam ("manga") String manga,
            @RequestParam ("pers") String pers,
            @RequestParam ("dataanonsa") Integer dataanonsa,
            @RequestParam ("datakonca") Integer datakonca,
            Model model)
    {
        if (!sezonRepository.existsById(id) )
        {
            return "redirect:/Sezon/";
        }
        if(bindingResult.hasErrors())
            return "Sezon/Edit-Sezon";

        //  News news = newsRepository.findById(id).orElseThrow();
        Iterable<Video> stand1 = videosRepository.findAll();
        model.addAttribute("video", stand1);

        Iterable<Sozdatel> stand2 = sozdatelisRepository.findAll();
        model.addAttribute("sozdatel", stand2);

        Iterable<Manga> stand3 = mangasRepository.findAll();
        model.addAttribute("manga", stand3);

        Iterable<Pers> stand4 = persRepository.findAll();
        model.addAttribute("pers", stand4);

        List<Video> videos = videosRepository.findByIma(video);

        List<Sozdatel> sozdatels = sozdatelisRepository.findByIma(sozdatel);
        List<Manga> mangas = mangasRepository.findByIma(manga);
        List<Pers> pers1 = persRepository.findByImapers(pers);

        Sezon sezon1 = new Sezon(namesezon, datavixoda, kolvoseria, videos.get(0), sozdatels.get(0), mangas.get(0), pers1.get(0), dataanonsa , datakonca);
        sezon1.setId(id);
        sezonRepository.save(sezon1);
        return "redirect:/Sezon/";
    }


}
