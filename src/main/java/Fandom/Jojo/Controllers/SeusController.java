package Fandom.Jojo.Controllers;

import Fandom.Jojo.Models.Seu;
import Fandom.Jojo.Models.Sozdatel;
import Fandom.Jojo.Repository.SeusRepository;
import Fandom.Jojo.Repository.SozdatelisRepository;
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
@RequestMapping("/Seus")
@PreAuthorize("hasAnyAuthority('USER')")
public class SeusController {


    @Autowired
    private SeusRepository seusRepository;

    @GetMapping("/")
    public String Index(Model model)
    {
        Iterable<Seu> seus =  seusRepository.findAll();
        model.addAttribute("seus", seus);
        return "Seus/Index";
    }

    @GetMapping("/add")
    public String addView(Model model)
    {
        model.addAttribute("seus", new Seu());
        return "Seus/AddSeu";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("seus") @Valid Seu newSeu,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "Seus/AddSeu";

        seusRepository.save(newSeu);
        return "redirect:/Seus/";
    }

    @GetMapping("/Search")
    public String GetAdd(
            @RequestParam("Ima") String ima,
            Model model)
    {
        List<Seu> seus = seusRepository.findByIma(ima);
        model.addAttribute("seus", seus);
        return "Seus/Index";
    }

    @GetMapping("/Searchs")
    public String GetAdds(
            @RequestParam("Ima") String ima,
            Model model)
    {
        List<Seu> seuList = seusRepository.findByImaContains(ima);
        model.addAttribute("seus", seuList);
        return "Seus/Index";
    }



    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Seu> seu = seusRepository.findById(id);
        ArrayList<Seu> kolodsArrayList =  new ArrayList<>();
        seu.ifPresent(kolodsArrayList::add);
        model.addAttribute("seu", kolodsArrayList);
        return "Seus/Info-Seu";
    }


    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Seu seu = seusRepository.findById(id).orElseThrow();
        seusRepository.delete(seu);

        return "redirect:/Seus/";
    }


    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!seusRepository.existsById(id) )
        {
            return "redirect:/Seus/";
        }

        Optional<Seu> seu = seusRepository.findById(id);
        ArrayList<Seu> seuArrayList =  new ArrayList<>();
        seu.ifPresent(seuArrayList::add);
        model.addAttribute("seus", seuArrayList.get(0));
        model.addAttribute("Seus", seuArrayList);
        model.addAttribute("ima", seu.get().getima());
        model.addAttribute("vozrastseu", seu.get().getvozrastseu());
        model.addAttribute("nacionalnost", seu.get().getnacionalnost());
        return "Seus/Edit-Seu";
    }


    @PostMapping("/edit/{id}")
    public String editKolods(
            @PathVariable("id") Long id,

            @ModelAttribute("seus") @Valid Seu seu,
            BindingResult bindingResult,
            Model model)
    {
        if (!seusRepository.existsById(id) )
        {
            return "redirect:/Seus/";
        }
        if(bindingResult.hasErrors())
            return "Seus/Edit-Seu";
        seu.setId(id);
        //  News news = newsRepository.findById(id).orElseThrow();
        seusRepository.save(seu);
        return "redirect:/Seus/";
    }

}
