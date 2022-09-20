package Fandom.Jojo.Controllers;

import Fandom.Jojo.Models.Manga;
import Fandom.Jojo.Models.Video;
import Fandom.Jojo.Repository.MangasRepository;
import Fandom.Jojo.Repository.VideosRepository;
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
@RequestMapping("/Mangas")
@PreAuthorize("hasAnyAuthority('USER')")
public class MangasController {

    @Autowired
    private MangasRepository mangasRepository;

    @GetMapping("/")
    public String Index(Model model)
    {
        Iterable<Manga> mangas =  mangasRepository.findAll();
        model.addAttribute("mangas", mangas);
        return "Mangas/Index";
    }

    @GetMapping("/add")
    public String addView(Model model)
    {
        model.addAttribute("mangas", new Manga());
        return "Mangas/AddManga";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("mangas") @Valid Manga newManga,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "Mangas/AddManga";

        mangasRepository.save(newManga);
        return "redirect:/Mangas/";
    }

    @GetMapping("/Search")
    public String GetAdd(
            @RequestParam("Ima") String ima,
            Model model)
    {
        List<Manga> mangas = mangasRepository.findByIma(ima);
        model.addAttribute("mangas", mangas);
        return "Mangas/Index";
    }

    @GetMapping("/Searchs")
    public String GetAdds(
            @RequestParam("Ima") String ima,
            Model model)
    {
        List<Manga> videoList = mangasRepository.findByImaContains(ima);
        model.addAttribute("mangas", videoList);
        return "Mangas/Index";
    }



    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Manga> manga = mangasRepository.findById(id);
        ArrayList<Manga> kolodsArrayList =  new ArrayList<>();
        manga.ifPresent(kolodsArrayList::add);
        model.addAttribute("manga", kolodsArrayList);
        return "Mangas/Info-Manga";
    }


    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Manga manga = mangasRepository.findById(id).orElseThrow();
        mangasRepository.delete(manga);

        return "redirect:/Mangas/";
    }


    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!mangasRepository.existsById(id) )
        {
            return "redirect:/Mangas/";
        }

        Optional<Manga> manga = mangasRepository.findById(id);
        ArrayList<Manga> mangaArrayList =  new ArrayList<>();
        manga.ifPresent(mangaArrayList::add);
        model.addAttribute("mangas", mangaArrayList.get(0));
        model.addAttribute("Mangas", mangaArrayList);
        model.addAttribute("ima", manga.get().getima());
        model.addAttribute("godvixodam", manga.get().getgodvixodam());
        model.addAttribute("companym", manga.get().getcompanym());
        return "Mangas/Edit-Manga";
    }


    @PostMapping("/edit/{id}")
    public String editKolods(
            @PathVariable("id") Long id,

            @ModelAttribute("mangas") @Valid Manga manga,
            BindingResult bindingResult,
            Model model)
    {
        if (!mangasRepository.existsById(id) )
        {
            return "redirect:/Mangas/";
        }
        if(bindingResult.hasErrors())
            return "Mangas/Edit-Manga";
        manga.setId(id);
        //  News news = newsRepository.findById(id).orElseThrow();
        mangasRepository.save(manga);
        return "redirect:/Mangas/";
    }

}

