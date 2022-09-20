package Fandom.Jojo.Controllers;

import Fandom.Jojo.Models.Stand;
import Fandom.Jojo.Models.Standv;
import Fandom.Jojo.Repository.StandRepository;
import Fandom.Jojo.Repository.StandvRepository;
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
@RequestMapping("/Standvs")
@PreAuthorize("hasAnyAuthority('USER')")
public class StandvController {


    @Autowired
    private StandvRepository standvRepository;

    @GetMapping("/")
    public String Index(Model model)
    {
        Iterable<Standv> standvs =  standvRepository.findAll();
        model.addAttribute("standvs", standvs);
        return "Standvs/Index";
    }

    @GetMapping("/add")
    public String addView(Model model)
    {
        model.addAttribute("standvs", new Standv());
        return "Standvs/AddStandv";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("standvs") @Valid Standv newStandv,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "Standvs/AddStandv";

        standvRepository.save(newStandv);
        return "redirect:/Standvs/";
    }

    @GetMapping("/Search")
    public String GetAdd(
            @RequestParam("Ima") String ima,
            Model model)
    {
        List<Standv> standvs = standvRepository.findByIma(ima);
        model.addAttribute("standvs", standvs);
        return "Standvs/Index";
    }

    @GetMapping("/Searchs")
    public String GetAdds(
            @RequestParam("Ima") String ima,
            Model model)
    {
        List<Standv> standvList = standvRepository.findByImaContains(ima);
        model.addAttribute("standvs", standvList);
        return "Standvs/Index";
    }



    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Standv> standv = standvRepository.findById(id);
        ArrayList<Standv> kolodsArrayList =  new ArrayList<>();
        standv.ifPresent(kolodsArrayList::add);
        model.addAttribute("standv", kolodsArrayList);
        return "Standvs/Info-Standv";
    }


    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Standv standv = standvRepository.findById(id).orElseThrow();
        standvRepository.delete(standv);

        return "redirect:/Standvs/";
    }


    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!standvRepository.existsById(id) )
        {
            return "redirect:/Standvs/";
        }

        Optional<Standv> standv = standvRepository.findById(id);
        ArrayList<Standv> standvArrayList =  new ArrayList<>();
        standv.ifPresent(standvArrayList::add);
        model.addAttribute("standvs", standvArrayList.get(0));
        model.addAttribute("Standvs", standvArrayList);
        model.addAttribute("ima", standv.get().getima());
        model.addAttribute("skill", standv.get().getskill());
        model.addAttribute("haracter", standv.get().getharacter());
        return "Standvs/Edit-Standv ";
    }


    @PostMapping("/edit/{id}")
    public String editKolods(
            @PathVariable("id") Long id,

            @ModelAttribute("standvs") @Valid Standv standv,
            BindingResult bindingResult,
            Model model)
    {
        if (!standvRepository.existsById(id) )
        {
            return "redirect:/Standvs/";
        }
        if(bindingResult.hasErrors())
            return "Standvs/Edit-Standv";
        standv.setId(id);
        //  News news = newsRepository.findById(id).orElseThrow();
        standvRepository.save(standv);
        return "redirect:/Standvs/";
    }
}

