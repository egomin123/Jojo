package Fandom.Jojo.Controllers;


import Fandom.Jojo.Models.Stand;
import Fandom.Jojo.Models.Video;
import Fandom.Jojo.Repository.StandRepository;
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
@RequestMapping("/Stands")
@PreAuthorize("hasAnyAuthority('USER')")
public class StandController {

    @Autowired
    private StandRepository standRepository;

    @GetMapping("/")
    public String Index(Model model)
    {
        Iterable<Stand> stands =  standRepository.findAll();
        model.addAttribute("stands", stands);
        return "Stands/Index";
    }

    @GetMapping("/add")
    public String addView(Model model)
    {
        model.addAttribute("stands", new Stand());
        return "Stands/AddStand";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("stands") @Valid Stand newStand,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "Stands/AddStand";

        standRepository.save(newStand);
        return "redirect:/Stands/";
    }

    @GetMapping("/Search")
    public String GetAdd(
            @RequestParam("Ima") String ima,
            Model model)
    {
        //List<Stand> stands = standRepository.findByIma(ima);
      //  model.addAttribute("stands", stands);
        return "Stands/Index";
    }

    @GetMapping("/Searchs")
    public String GetAdds(
            @RequestParam("Ima") String ima,
            Model model)
    {
        List<Stand> standList = standRepository.findByImaContains(ima);
        model.addAttribute("stands", standList);
        return "Stands/Index";
    }



    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Stand> stand = standRepository.findById(id);
        ArrayList<Stand> kolodsArrayList =  new ArrayList<>();
        stand.ifPresent(kolodsArrayList::add);
        model.addAttribute("stand", kolodsArrayList);
        return "Stands/Info-Stand";
    }


    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Stand stand = standRepository.findById(id).orElseThrow();
        standRepository.delete(stand);

        return "redirect:/Stands/";
    }


    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!standRepository.existsById(id) )
        {
            return "redirect:/Stands/";
        }

        Optional<Stand> stand = standRepository.findById(id);
        ArrayList<Stand> standArrayList =  new ArrayList<>();
        stand.ifPresent(standArrayList::add);
        model.addAttribute("stands", standArrayList.get(0));
        model.addAttribute("Stands", standArrayList);
        model.addAttribute("ima", stand.get().getima());
        model.addAttribute("skill", stand.get().getskill());
        model.addAttribute("haracter", stand.get().getharacter());
        return "Stands/Edit-Stand";
    }


    @PostMapping("/edit/{id}")
    public String editKolods(
            @PathVariable("id") Long id,

            @ModelAttribute("stands") @Valid Stand stand,
            BindingResult bindingResult,
            Model model)
    {
        if (!standRepository.existsById(id) )
        {
            return "redirect:/Stands/";
        }
        if(bindingResult.hasErrors())
            return "Stands/Edit-Stand";
        stand.setId(id);
        //  News news = newsRepository.findById(id).orElseThrow();
        standRepository.save(stand);
        return "redirect:/Stands/";
    }
}
