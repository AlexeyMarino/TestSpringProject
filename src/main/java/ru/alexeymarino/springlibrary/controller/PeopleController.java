package ru.alexeymarino.springlibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alexeymarino.springlibrary.dao.PersonDAO;
import ru.alexeymarino.springlibrary.model.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("people", personDAO.getAll());
        return "people/showall";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getById(id));
        model.addAttribute("books", personDAO.getBooksByPersonId(id));
        return "people/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDAO.update(id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "people/create";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person") Person person, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "people/create";
        }
        personDAO.save(person);
        model.addAttribute("people", personDAO.getAll());
        return "people/showall";
    }

}
