package Fandom.Jojo.Controllers;

import Fandom.Jojo.Models.Sozdatel;
import Fandom.Jojo.Models.Video;
import Fandom.Jojo.Repository.SozdatelisRepository;
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
@RequestMapping("/Videos")
@PreAuthorize("hasAnyAuthority('USER')")
public class VideosController {

    @Autowired
    private VideosRepository videosRepository;

    @GetMapping("/")
    public String Index(Model model)
    {
        Iterable<Video> videos =  videosRepository.findAll();
        model.addAttribute("videos", videos);
        return "Videos/Index";
    }

    @GetMapping("/add")
    public String addView(Model model)
    {
        model.addAttribute("videos", new Video());
        return "Videos/AddVideo";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("videos") @Valid Video newSozdatel,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "Videos/AddVideo";

        videosRepository.save(newSozdatel);
        return "redirect:/Videos/";
    }

    @GetMapping("/Search")
    public String GetAdd(
            @RequestParam("Ima") String ima,
            Model model)
    {
        List<Video> videos = videosRepository.findByIma(ima);
        model.addAttribute("videos", videos);
        return "Videos/Index";
    }

    @GetMapping("/Searchs")
    public String GetAdds(
            @RequestParam("Ima") String ima,
            Model model)
    {
        List<Video> videoList = videosRepository.findByImaContains(ima);
        model.addAttribute("videos", videoList);
        return "Videos/Index";
    }



    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Video> video = videosRepository.findById(id);
        ArrayList<Video> kolodsArrayList =  new ArrayList<>();
        video.ifPresent(kolodsArrayList::add);
        model.addAttribute("video", kolodsArrayList);
        return "Videos/Info-Video";
    }


    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Video video = videosRepository.findById(id).orElseThrow();
        videosRepository.delete(video);

        return "redirect:/Videos/";
    }


    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!videosRepository.existsById(id) )
        {
            return "redirect:/Videos/";
        }

        Optional<Video> video = videosRepository.findById(id);
        ArrayList<Video> videoArrayList =  new ArrayList<>();
        video.ifPresent(videoArrayList::add);
        model.addAttribute("videos", videoArrayList.get(0));
        model.addAttribute("Videos", videoArrayList);
        model.addAttribute("ima", video.get().getima());
        model.addAttribute("godvixoda", video.get().getgodvixoda());
        model.addAttribute("company", video.get().getcompany());
        return "Videos/Edit-Video";
    }


    @PostMapping("/edit/{id}")
    public String editKolods(
            @PathVariable("id") Long id,

            @ModelAttribute("videos") @Valid Video video,
            BindingResult bindingResult,
            Model model)
    {
        if (!videosRepository.existsById(id) )
        {
            return "redirect:/Videos/";
        }
        if(bindingResult.hasErrors())
            return "Videos/Edit-Video";
        video.setId(id);
        //  News news = newsRepository.findById(id).orElseThrow();
        videosRepository.save(video);
        return "redirect:/Videos/";
    }

}
