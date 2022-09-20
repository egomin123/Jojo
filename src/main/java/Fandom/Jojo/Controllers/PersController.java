package Fandom.Jojo.Controllers;

import Fandom.Jojo.Models.*;
import Fandom.Jojo.Repository.PersRepository;
import Fandom.Jojo.Repository.SeusRepository;
import Fandom.Jojo.Repository.StandRepository;
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
@RequestMapping("/Pers")
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class PersController {


    @Autowired
    private StandRepository standRepository;
    @Autowired
    private PersRepository persRepository;
    @Autowired
    private SeusRepository seusRepository;

    @GetMapping("/")
    public  String Index(Model model){

        Iterable<Stand> stands = standRepository.findAll();
        model.addAttribute("stand", stands);

        Iterable<Seu> seus = seusRepository.findAll();
        model.addAttribute("seu", seus);

        Iterable<Pers> pers = persRepository.findAll();
        model.addAttribute("pers",pers);
        return "Pers/Index";
    }

    @GetMapping("/add")
    public String addView(Model model)
    {

        Iterable<Stand> stand1 = standRepository.findAll();
        model.addAttribute("stand", stand1);

        Iterable<Seu> seu1 = seusRepository.findAll();
        model.addAttribute("seu", seu1);

        return "Pers/AddPers";
    }


    @PostMapping("/add")
    public String blogPostAdd(@RequestParam ("imapers") String imapers,
                              @RequestParam ("vozrastpers") Integer vozrastpers,
                              @RequestParam ("haracterpers") String haracterpers,
                              @RequestParam ("imast") String imast,
                              @RequestParam ("imase") String imase,
                              Model model)
    {

        List<Stand> stand = standRepository.findByIma(imast);

        List<Seu> seu = seusRepository.findByIma(imase);

        Pers pers = new Pers(imapers, vozrastpers, haracterpers, stand.get(0), seu.get(0));
        persRepository.save(pers);


        model.addAttribute("imapers", imapers);
        model.addAttribute("vozrastpers", vozrastpers);
        model.addAttribute("haracterpers", haracterpers);
        Iterable<Stand> stand1 = standRepository.findAll();
        model.addAttribute("stand", stand1);

        Iterable<Seu> seu1 = seusRepository.findAll();
        model.addAttribute("seu", seu1);
        Iterable<Pers> pers1 = persRepository.findAll();
        model.addAttribute("pers",pers1);
        return "Pers/Index";
    }


    @GetMapping("/Search")
    public String GetAdd(
            @RequestParam("Imapers") String imapers,
            Model model)
    {
        List<Pers> pers = persRepository.findByImapers(imapers);
        model.addAttribute("pers", pers);
        return "Pers/Index";
    }

    @GetMapping("/Searchs")
    public String GetAdds(
            @RequestParam("Imapers") String imapers,
            Model model)
    {
        List<Pers> persList = persRepository.findByImapersContains(imapers);
        model.addAttribute("pers", persList);
        return "Pers/Index";
    }


    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Pers> pers = persRepository.findById(id);
        ArrayList<Pers> kolodsArrayList =  new ArrayList<>();
        pers.ifPresent(kolodsArrayList::add);
        model.addAttribute("pers", kolodsArrayList);
        return "Pers/Info-Pers";
    }



    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Pers pers = persRepository.findById(id).orElseThrow();
        persRepository.delete(pers);

        return "redirect:/Pers/";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!persRepository.existsById(id) )
        {
            return "redirect:/Pers/";
        }
        Iterable<Stand> stand1 = standRepository.findAll();
        model.addAttribute("stand", stand1);

        Iterable<Seu> seu1 = seusRepository.findAll();
        model.addAttribute("seu", seu1);

        Optional<Pers> pers = persRepository.findById(id);
        ArrayList<Pers> persArrayList =  new ArrayList<>();
        pers.ifPresent(persArrayList::add);
        model.addAttribute("pers", persArrayList.get(0));
        model.addAttribute("Pers", persArrayList);
        model.addAttribute("imapers", pers.get().getimapers());
        model.addAttribute("vozrastpers", pers.get().getvozrastpers());
        model.addAttribute("haracterpers", pers.get().getharacterpers());


        return "Pers/Edit-Pers";
    }


    @PostMapping("/edit/{id}")
    public String editKolods(
            @PathVariable("id") Long id,

            @ModelAttribute("pers") @Valid Pers pers,
            BindingResult bindingResult,
            @RequestParam ("imapers") String imapers,
            @RequestParam ("vozrastpers") Integer vozrastpers,
            @RequestParam ("haracterpers") String haracterpers,
            @RequestParam ("imast") String imast,
            @RequestParam ("imase") String imase,
            Model model)
    {
        if (!persRepository.existsById(id) )
        {
            return "redirect:/Pers/";
        }
        if(bindingResult.hasErrors())
            return "Pers/Edit-Pers";

        //  News news = newsRepository.findById(id).orElseThrow();
        Iterable<Stand> stand1 = standRepository.findAll();
        model.addAttribute("stand", stand1);

        List<Stand> stand = standRepository.findByIma(imast);

        List<Seu> seu = seusRepository.findByIma(imase);

        Pers persw = new Pers(imapers, vozrastpers, haracterpers, stand.get(0), seu.get(0));
        persw.setId(id);
        persRepository.save(persw);
        return "redirect:/Pers/";
    }


}
