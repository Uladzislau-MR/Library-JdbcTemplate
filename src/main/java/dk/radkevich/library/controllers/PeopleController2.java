//package dk.radkevich.library.controllers;
//
//import dk.radkevich.library.dao.BookDAO;
//import dk.radkevich.library.dao.PersonDAO;
//import dk.radkevich.library.models.Book;
//import dk.radkevich.library.models.Person;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@Controller
//@RequestMapping("/people23")
//public class PeopleController2 {
//    private final PersonDAO personDAO;
//    private final BookDAO bookDAO;
//
//
//    public PeopleController2(PersonDAO personDAO, BookDAO bookDAO) {
//        this.personDAO = personDAO;
//        this.bookDAO = bookDAO;
//    }
//
//    @GetMapping
//    public String ShowAllPeople(Model model) {
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!! Inside books !!!!!!!!!!!!!!!!!!!!!!!!!!");
//        List<Person> people = personDAO.showAllPeople();
//        System.out.println("people = " + people);
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!! After people print !!!!!!!!!!!!!!!!!!!!!!!!!!");
//        List<Book> books = bookDAO.getAllBooks();
//        System.out.println("books = " + books);
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!! After books print !!!!!!!!!!!!!!!!!!!!!!!!!!");
//        model.addAttribute("people", people);
//
//        return "people/ShowAllPeople";
//    }
//
//    @GetMapping("/newPerson")
//    public String NewPerson(Model model) {
//        model.addAttribute("person", new Person());
//
//        return "people/NewPerson";
//    }
//
//    @GetMapping("/{id}")
//    public String ShowByID(@PathVariable("id") int id, Model model) {
////        model.addAttribute("person", personDAO.showBookByID(id));
//
//        return "people/ShowById";
//    }
//
//    @GetMapping("/{id}/Edit")
//    public String edit(@PathVariable("id") int id, Model model) {
////        model.addAttribute("person", personDAO.showBookByID(id));
//
//        return "people/Edit";
//    }
//    //TODO сделать валидацю на а ошибки в update
//    @PatchMapping("/{id}")
//    public String Update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
////        personDAO.updateBook(id, person);
//        return "redirect:/people";
//    }
//
//    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
//
//       if(bindingResult.hasErrors()){
//           return "people/NewPerson";
//       }
////        personDAO.savePerson(person);
//        return "redirect:/people";
//    }
//
//    @DeleteMapping("{id}")
//    public String Delete(@PathVariable("id") int id) {
////        personDAO.deletePerson(id);
//        return "redirect:/people";
//    }
//
//
//}
//
