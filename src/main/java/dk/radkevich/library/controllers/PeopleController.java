package dk.radkevich.library.controllers;

import dk.radkevich.library.dao.PersonDAO;
import dk.radkevich.library.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;


    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String ShowAllPeople(Model model) {
        model.addAttribute("people", personDAO.showAllPeople());

        return "people/ShowAllPeople";
    }

    @GetMapping("/newPerson")
    public String NewPerson(Model model) {
        model.addAttribute("person", new Person());

        return "people/NewPerson";
    }

    @GetMapping("/{id}")
    public String ShowByID(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.showByID(id));


        return "people/ShowById";
    }

    @GetMapping("/{id}/Edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.showByID(id));


        return "people/Edit";
    }
    //TODO сделать валидацю на а ошибки в update
    @PatchMapping("/{id}/update")
    public String Update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        personDAO.updatePerson(id, person);
        return "redirect:/people";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

       if(bindingResult.hasErrors()){
           return "people/NewPerson";
       }
        personDAO.savePerson(person);
        return "redirect:/people";
    }

    @DeleteMapping("{id}")
    public String Delete(@PathVariable("id") int id) {
        personDAO.deletePerson(id);
        return "redirect:/people";
    }


}

