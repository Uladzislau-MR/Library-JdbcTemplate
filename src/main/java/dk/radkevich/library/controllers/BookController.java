package dk.radkevich.library.controllers;

import dk.radkevich.library.dao.BookDAO;

import dk.radkevich.library.dao.PersonDAO;
import dk.radkevich.library.models.Book;

import dk.radkevich.library.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String ShowAllBooks(Model model) {
        model.addAttribute("books", bookDAO.getAllBooks());

        return "books/ShowAllBooks";
    }

    @GetMapping("/newBook")
    public String NewBook(Model model) {
        model.addAttribute("book", new Book());

        return "books/NewBook";
    }

    @GetMapping("/{id}")
    public String ShowByID(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.showBookByID(id));
        model.addAttribute("personBook", bookDAO.getBookReader(id));
        model.addAttribute("people",personDAO.showAllPeople());
        return "books/ShowById";
    }

//    public String setReader(Model model, @ModelAttribute("person")Person person) {
//        model.addAttribute("people",personDAO.showAllPeople());
//        return  "books/ShowById";
//    }

    @GetMapping("/{id}/Edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.showBookByID(id));

        return "books/Edit";
    }
    //TODO сделать валидацю на а ошибки в update
    @PatchMapping("/{id}")
    public String Update(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        bookDAO.updateBook(id, book);
        return "redirect:/books";
    }


    @PatchMapping("/{id}/set")
    public String addReader( @PathVariable("id")  int id, @ModelAttribute("person")Person selectedPerson) {

        bookDAO.setReader(id,selectedPerson);

        return "redirect:/books/"+id;
    }

//    @PostMapping("/{id}/set")
//    public String addReader(@PathVariable("id") int bookId, @RequestParam("personId") int personId) {
//        // Здесь ваша логика для назначения читателя книге
//        bookDAO.setReader(bookId, personId); // Пример вызова метода, где personId передается в DAO
//
//        return "redirect:/books";
//    }


    @PostMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id) {
        // Освобождение книги по id
        bookDAO.releaseBook(id);
        // Перенаправление обратно к списку книг
        return "redirect:/books";
    }

    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

       if(bindingResult.hasErrors()){
           return "books/NewBook";
       }
        bookDAO.saveBook(book);
        return "redirect:/books";
    }

    @DeleteMapping("{id}")
    public String Delete(@PathVariable("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }


}

