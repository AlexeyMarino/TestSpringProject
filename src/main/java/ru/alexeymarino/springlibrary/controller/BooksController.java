package ru.alexeymarino.springlibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alexeymarino.springlibrary.dao.BookDAO;
import ru.alexeymarino.springlibrary.dao.PersonDAO;
import ru.alexeymarino.springlibrary.model.Book;
import ru.alexeymarino.springlibrary.model.Person;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }


    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("books", bookDAO.getAll());
        return "books/showall";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.getById(id));
        Optional<Person> owner = bookDAO.getBookOwner(id);
        if(owner.isPresent()) {
            model.addAttribute("owner", owner.get());
        } else{
            model.addAttribute("people", personDAO.getAll());
        }
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        bookDAO.update(id, book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "books/create";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") Book book, Model model) {
        bookDAO.save(book);
        model.addAttribute("books", bookDAO.getAll());
        return "books/showall";
    }

    @PatchMapping("/{id}/take")
    public String take(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        bookDAO.take(id, person);
        return "redirect:/books/"+id;
    }
    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookDAO.release(id);
        return "redirect:/books/"+id;
    }


}
