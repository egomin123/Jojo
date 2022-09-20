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
@RequestMapping("/Predsezon")
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class PredsezonController {


    @Autowired
    private VperssRepository vperssRepository;
    @Autowired
    private SezonRepository sezonRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PredsezonRepository predsezonRepository;


    @GetMapping("/")
    public  String Index(Model model){

        Iterable<Sezon> sezons = sezonRepository.findAll();
        model.addAttribute("sezon", sezons);

        Iterable<User> users = userRepository.findAll();
        model.addAttribute("user", users);

        Iterable<Predsezon> predsezons = predsezonRepository.findAll();
        model.addAttribute("predsezon",predsezons);
        return "Predsezon/Index";
    }


    @GetMapping("/add")
    public String addView(Model model)
    {

        Iterable<Sezon> sezons = sezonRepository.findAll();
        model.addAttribute("sezon", sezons);

        Iterable<User> users = userRepository.findAll();
        model.addAttribute("user", users);

        return "Predsezon/AddPredsezon";
    }


    @PostMapping("/add")
    public String blogPostAdd(@RequestParam("namepredsezon") String namepredsezon,
                              @RequestParam ("user") String user,
                              @RequestParam ("sezon") String sezon,

                              Model model)
    {

        List<User> users = vperssRepository.findByLogin(user);

        List<Sezon> sezons = sezonRepository.findByNamesezon(sezon);

        Predsezon predsezon1 = new Predsezon(namepredsezon, users.get(0), sezons.get(0));
        predsezonRepository.save(predsezon1);


        model.addAttribute("namepredsezon", namepredsezon);


        Iterable<User> users1 = userRepository.findAll();
        model.addAttribute("user", users1);

        Iterable<Sezon> sezons1 = sezonRepository.findAll();
        model.addAttribute("sezon", sezons1);

        Iterable<Predsezon> predsezons = predsezonRepository.findAll();
        model.addAttribute("predsezon",predsezons);
        return "Predsezon/Index";
    }

    @GetMapping("/Search")
    public String GetAdd(
            @RequestParam("namepredsezon") String namepredsezon,
            Model model)
    {
        List<Predsezon> predsezons = predsezonRepository.findByNamepredsezon(namepredsezon);
        model.addAttribute("predsezon", predsezons);
        return "Predsezon/Index";
    }

    @GetMapping("/Searchs")
    public String GetAdds(
            @RequestParam("namepredsezon") String namepredsezon,
            Model model)
    {
        List<Predsezon> persList = predsezonRepository.findByNamepredsezonContains(namepredsezon);
        model.addAttribute("predsezon", persList);
        return "Predsezon/Index";
    }


    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Predsezon> predsezon = predsezonRepository.findById(id);
        ArrayList<Predsezon> kolodsArrayList =  new ArrayList<>();
        predsezon.ifPresent(kolodsArrayList::add);
        model.addAttribute("predsezon", kolodsArrayList);
        return "Predsezon/Info-Predsezon";
    }


    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Predsezon predsezon = predsezonRepository.findById(id).orElseThrow();
        predsezonRepository.delete(predsezon);

        return "redirect:/Predsezon/";
    }


    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!predsezonRepository.existsById(id) )
        {
            return "redirect:/Predsezon/";
        }
        Iterable<User> user1 = userRepository.findAll();
        model.addAttribute("user", user1);

        Iterable<Sezon> sezons = sezonRepository.findAll();
        model.addAttribute("sezon", sezons);

        Optional<Predsezon> predsezon = predsezonRepository.findById(id);
        ArrayList<Predsezon> predsezonArrayList =  new ArrayList<>();
        predsezon.ifPresent(predsezonArrayList::add);
        model.addAttribute("predsezon", predsezonArrayList.get(0));
        model.addAttribute("Predsezon", predsezonArrayList);
        model.addAttribute("namepredsezon", predsezon.get().getnamepredsezon());



        return "Predsezon/Edit-Predsezon";
    }


    @PostMapping("/edit/{id}")
    public String editKolods(
            @PathVariable("id") Long id,
            @ModelAttribute("predsezon") @Valid Predsezon predsezon,
            BindingResult bindingResult,
            @RequestParam ("namepredsezon") String namepredsezon,
            @RequestParam ("login") String login,
            @RequestParam ("ima") String ima,

            Model model)
    {
        if (!predsezonRepository.existsById(id) )
        {
            return "redirect:/Predsezon/";
        }
        if(bindingResult.hasErrors())
            return "Predsezon/Edit-Predsezon";


        Iterable<User> users = userRepository.findAll();
        model.addAttribute("user", users);

        List<User> user1 = vperssRepository.findByLogin(login);

        List<Sezon> sezons = sezonRepository.findByNamesezon(ima);

        Predsezon predsezon1 = new Predsezon(namepredsezon, user1.get(0), sezons.get(0));
        predsezon1.setId(id);
        predsezonRepository.save(predsezon1);
        return "redirect:/Predsezon/";
    }


}
