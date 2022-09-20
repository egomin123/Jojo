package Fandom.Jojo.Controllers;


import Fandom.Jojo.Models.Sozdatel;
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
@RequestMapping("/Sozdatels")
@PreAuthorize("hasAnyAuthority('USER')")
public class SozdatelsController {

    @Autowired
    private SozdatelisRepository sozdatelisRepository;

    @GetMapping("/")
    public String Index(Model model)
    {
        Iterable<Sozdatel> sozdatels =  sozdatelisRepository.findAll();
        model.addAttribute("sozdatels", sozdatels);
        return "Sozdatels/Index";
    }

    @GetMapping("/add")
    public String addView(Model model)
    {
        model.addAttribute("sozdatels", new Sozdatel());
        return "Sozdatels/AddSozdatel";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("sozdatels") @Valid Sozdatel newSozdatel,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "Sozdatels/AddSozdatel";

        sozdatelisRepository.save(newSozdatel);
        return "redirect:/Sozdatels/";
    }

    @GetMapping("/Search")
    public String GetAdd(
            @RequestParam("Ima") String ima,
            Model model)
    {
        List<Sozdatel> sozdatels = sozdatelisRepository.findByIma(ima);
        model.addAttribute("sozdatels", sozdatels);
        return "Sozdatels/Index";
    }

    @GetMapping("/Searchs")
    public String GetAdds(
            @RequestParam("Ima") String ima,
            Model model)
    {
        List<Sozdatel> sozdatelList = sozdatelisRepository.findByImaContains(ima);
        model.addAttribute("sozdatels", sozdatelList);
        return "Sozdatels/Index";
    }



    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Sozdatel> sozdatel = sozdatelisRepository.findById(id);
        ArrayList<Sozdatel> kolodsArrayList =  new ArrayList<>();
        sozdatel.ifPresent(kolodsArrayList::add);
        model.addAttribute("sozdatel", kolodsArrayList);
        return "Sozdatels/Info-Sozdatel";
    }


    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Sozdatel sozdatel = sozdatelisRepository.findById(id).orElseThrow();
        sozdatelisRepository.delete(sozdatel);

        return "redirect:/Sozdatels/";
    }


    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!sozdatelisRepository.existsById(id) )
        {
            return "redirect:/Sozdatels/";
        }

        Optional<Sozdatel> sozdatel = sozdatelisRepository.findById(id);
        ArrayList<Sozdatel> sozdatelArrayList =  new ArrayList<>();
        sozdatel.ifPresent(sozdatelArrayList::add);
        model.addAttribute("sozdatels", sozdatelArrayList.get(0));
        model.addAttribute("Sozdatels", sozdatelArrayList);
        model.addAttribute("ima", sozdatel.get().getima());
        model.addAttribute("vozrastsozdatel", sozdatel.get().getvozrastsozdatel());
        model.addAttribute("dolznost", sozdatel.get().getdolznost());
        return "Sozdatels/Edit-Sozdatel";
    }


    @PostMapping("/edit/{id}")
    public String editKolods(
            @PathVariable("id") Long id,

            @ModelAttribute("sozdatels") @Valid Sozdatel sozdatel,
            BindingResult bindingResult,
            Model model)
    {
        if (!sozdatelisRepository.existsById(id) )
        {
            return "redirect:/Sozdatels/";
        }
        if(bindingResult.hasErrors())
            return "Sozdatels/Edit-Sozdatel";
        sozdatel.setId(id);
        //  News news = newsRepository.findById(id).orElseThrow();
        sozdatelisRepository.save(sozdatel);
        return "redirect:/Sozdatels/";
    }

}
