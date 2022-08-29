package ru.alexeymarino.springlibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alexeymarino.springlibrary.dao.BookDAO;
import ru.alexeymarino.springlibrary.model.Book;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;

    @Autowired
    public BooksController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }


    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("books", bookDAO.getAll());
        return "books/showall";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getById(id));
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
        model.addAttribute("book", bookDAO.getAll());
        return "books/showall";
    }
}
