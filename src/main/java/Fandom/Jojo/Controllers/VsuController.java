package Fandom.Jojo.Controllers;


import Fandom.Jojo.Models.*;
import Fandom.Jojo.Repository.PersRepository;
import Fandom.Jojo.Repository.StandRepository;
import Fandom.Jojo.Repository.VpersRepository;
import Fandom.Jojo.Repository.VsuRepository;
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
@RequestMapping("/Vsu")
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class VsuController {



    @Autowired
    private VsuRepository vsuRepository;
    @Autowired
    private VpersRepository vpersRepository;


    @GetMapping("/")
    public  String Index(Model model){

        Iterable<Vpers> vpers = vpersRepository.findAll();
        model.addAttribute("vpers", vpers);


        Iterable<Vsu> vsu = vsuRepository.findAll();
        model.addAttribute("vsu",vsu);
        return "Vsu/Index";
    }


    @GetMapping("/add")
    public String addView(Model model)
    {

        Iterable<Vpers> vpers1 = vpersRepository.findAll();
        model.addAttribute("vpers", vpers1);

        return "Vsu/AddVsu";
    }


    @PostMapping("/add")
    public String blogPostAdd(@RequestParam("ima") String ima,
                              @RequestParam ("samvsu") String samvsu,
                              @RequestParam ("imavpers") String imavpers,
                              Model model)
    {

        List<Vpers> vpers = vpersRepository.findByImavpers(imavpers);


        Vsu vsu = new Vsu(ima, samvsu, vpers.get(0));
        vsuRepository.save(vsu);


        model.addAttribute("ima", ima);
        model.addAttribute("samvsu", samvsu);
        Iterable<Vpers> vpers1 = vpersRepository.findAll();
        model.addAttribute("vpers", vpers1);

        Iterable<Vsu> vsus = vsuRepository.findAll();
        model.addAttribute("vsu",vsus);
        return "Vsu/Index";
    }


    @GetMapping("/Search")
    public String GetAdd(
            @RequestParam("Ima") String ima,
            Model model)
    {
        List<Vsu> vsus = vsuRepository.findByIma(ima);
        model.addAttribute("vsu", vsus);
        return "Vsu/Index";
    }

    @GetMapping("/Searchs")
    public String GetAdds(
            @RequestParam("Ima") String ima,
            Model model)
    {
        List<Vsu> vsuList = vsuRepository.findByImaContains(ima);
        model.addAttribute("vsu", vsuList);
        return "Vsu/Index";
    }


    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Vsu> vsus = vsuRepository.findById(id);
        ArrayList<Vsu> kolodsArrayList =  new ArrayList<>();
        vsus.ifPresent(kolodsArrayList::add);
        model.addAttribute("vsu", kolodsArrayList);
        return "Vsu/Info-Vsu";
    }


    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Vsu vsu = vsuRepository.findById(id).orElseThrow();
        vsuRepository.delete(vsu);

        return "redirect:/Vsu/";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!vsuRepository.existsById(id) )
        {
            return "redirect:/Vsu/";
        }
        Iterable<Vpers> vpers1 = vpersRepository.findAll();
        model.addAttribute("vpers", vpers1);


        Optional<Vsu> vsus = vsuRepository.findById(id);
        ArrayList<Vsu> vsuArrayList =  new ArrayList<>();
        vsus.ifPresent(vsuArrayList::add);
        model.addAttribute("vsu", vsuArrayList.get(0));
        model.addAttribute("Vsu", vsuArrayList);
        model.addAttribute("ima", vsus.get().getima());
        model.addAttribute("samvsu", vsus.get().getsamvsu());


        return "Vsu/Edit-Vsu";
    }



    @PostMapping("/edit/{id}")
    public String editKolods(
            @PathVariable("id") Long id,

            @ModelAttribute("vsu") @Valid Vsu vsu,
            BindingResult bindingResult,
            @RequestParam ("ima") String ima,
            @RequestParam ("samvsu") String samvsu,
            @RequestParam ("imavpers") String imavpers,
            Model model)
    {
        if (!vsuRepository.existsById(id) )
        {
            return "redirect:/Vsu/";
        }
        if(bindingResult.hasErrors())
            return "Vsu/Edit-Vsu";

        //  News news = newsRepository.findById(id).orElseThrow();
        Iterable<Vpers> vpers1 = vpersRepository.findAll();
        model.addAttribute("vpers", vpers1);

        List<Vpers> vpers = vpersRepository.findByImavpers(imavpers);

        Vsu vsuw = new Vsu(ima, samvsu, vpers.get(0));
        vsuw.setId(id);
        vsuRepository.save(vsuw);
        return "redirect:/Vsu/";
    }

}
