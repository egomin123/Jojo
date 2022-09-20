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
@RequestMapping("/Vpers")
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class VpersController {

    @Autowired
    private StandvRepository standvRepository;
    @Autowired
    private VperssRepository vperssRepository;

    @Autowired
    private VpersRepository vpersRepository;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public  String Index(Model model){

        Iterable<Standv> standvs = standvRepository.findAll();
        model.addAttribute("standv", standvs);

        Iterable<User> users = userRepository.findAll();
        model.addAttribute("user", users);

        Iterable<Vpers> vpers = vpersRepository.findAll();
        model.addAttribute("vpers",vpers);
        return "Vpers/Index";
    }


    @GetMapping("/add")
    public String addView(Model model)
    {

        Iterable<Standv> standv1 = standvRepository.findAll();
        model.addAttribute("standv", standv1);

        Iterable<User> user1 = userRepository.findAll();
        model.addAttribute("user", user1);

        return "Vpers/AddVpers";
    }


    @PostMapping("/add")
    public String blogPostAdd(@RequestParam("imavpers") String imavpers,
                              @RequestParam ("vozrastpers") Integer vozrastpers,
                              @RequestParam ("haracterpers") String haracterpers,
                              @RequestParam ("imast") String imast,
                              @RequestParam ("user") String user,
                              Model model)
    {

        List<Standv> standv = standvRepository.findByIma(imast);

        User users = userRepository.findByLogin(user);

        Vpers vpers = new Vpers(imavpers, vozrastpers, haracterpers, standv.get(0), users);
        vpersRepository.save(vpers);


        model.addAttribute("imavpers", imavpers);
        model.addAttribute("vozrastpers", vozrastpers);
        model.addAttribute("haracterpers", haracterpers);
        Iterable<Standv> standv1 = standvRepository.findAll();
        model.addAttribute("standv", standv1);

        Iterable<User> user1 = userRepository.findAll();
        model.addAttribute("user", user1);
        Iterable<Vpers> vpers1 = vpersRepository.findAll();
        model.addAttribute("vpers",vpers1);
        return "Vpers/Index";
    }



    @GetMapping("/Search")
    public String GetAdd(
            @RequestParam("Imavpers") String imavpers,
            Model model)
    {
        List<Vpers> vpers = vpersRepository.findByImavpers(imavpers);
        model.addAttribute("vpers", vpers);
        return "Vpers/Index";
    }

    @GetMapping("/Searchs")
    public String GetAdds(
            @RequestParam("Imavpers") String imavpers,
            Model model)
    {
        List<Vpers> vpersList = vpersRepository.findByImavpersContains(imavpers);
        model.addAttribute("vpers", vpersList);
        return "Vpers/Index";
    }


    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Vpers> vpers = vpersRepository.findById(id);
        ArrayList<Vpers> kolodsArrayList =  new ArrayList<>();
        vpers.ifPresent(kolodsArrayList::add);
        model.addAttribute("vpers", kolodsArrayList);
        return "Vpers/Info-Vpers";
    }


    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Vpers vpers = vpersRepository.findById(id).orElseThrow();
        vpersRepository.delete(vpers);

        return "redirect:/Vpers/";
    }



    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!vpersRepository.existsById(id) )
        {
            return "redirect:/Vpers/";
        }
        Iterable<Standv> standv1 = standvRepository.findAll();
        model.addAttribute("standv", standv1);

        Iterable<User> user1 = userRepository.findAll();
        model.addAttribute("user", user1);

        Optional<Vpers> vpers = vpersRepository.findById(id);
        ArrayList<Vpers> vpersArrayList =  new ArrayList<>();
        vpers.ifPresent(vpersArrayList::add);
        model.addAttribute("vpers", vpersArrayList.get(0));
        model.addAttribute("Vpers", vpersArrayList);
        model.addAttribute("imapers", vpers.get().getimavpers());
        model.addAttribute("vozrastpers", vpers.get().getvozrastpers());
        model.addAttribute("haracterpers", vpers.get().getharacterpers());


        return "Vpers/Edit-Vpers";
    }


    @PostMapping("/edit/{id}")
    public String editKolods(
            @PathVariable("id") Long id,

            @ModelAttribute("vpers") @Valid Vpers vpers,
            BindingResult bindingResult,
            @RequestParam ("imavpers") String imavpers,
            @RequestParam ("vozrastpers") Integer vozrastpers,
            @RequestParam ("haracterpers") String haracterpers,
            @RequestParam ("imast") String imast,
            @RequestParam ("user") String user,
            Model model)
    {
        if (!vpersRepository.existsById(id) )
        {
            return "redirect:/Vpers/";
        }
        if(bindingResult.hasErrors())
            return "Vpers/Edit-Vpers";


        Iterable<Standv> standv1 = standvRepository.findAll();
        model.addAttribute("standv", standv1);

        List<Standv> standv = standvRepository.findByIma(imast);

        List<User> user1 = vperssRepository.findByLogin(user);

        Vpers persw = new Vpers(imavpers, vozrastpers, haracterpers, standv.get(0), user1.get(0));
        persw.setId(id);
        vpersRepository.save(persw);
        return "redirect:/Vpers/";
    }


}
