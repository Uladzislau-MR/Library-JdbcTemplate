package dk.radkevich.library.dao;

import dk.radkevich.library.models.Book;
import dk.radkevich.library.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;


    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> showAllPeople() {
        return jdbcTemplate.query("Select* From Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void savePerson(Person person) {
        jdbcTemplate.update("INSERT INTO Person (full_Name,year_Of_Birth) VALUES (?,?) ", person.getFullName(), person.getYearOfBirth());

    }

//    public void updatePerson(int id, Person updatedPerson) {
//        updatedPerson = showByID(id);
//        jdbcTemplate.update("UPDATE Person SET id=?,full_Name=?,year_Of_Birth=? WHERE id=?", updatedPerson.getId(), updatedPerson.getFullName(), updatedPerson.getYearOfBirth(), id);
//        System.out.println("ID переданный в метод updatePerson: " + id);
//
//    }
    public void updatePerson(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET full_Name=?, year_Of_Birth=? WHERE id=?",
                updatedPerson.getFullName(), updatedPerson.getYearOfBirth(), id);
        System.out.println("ID переданный в метод updatePerson: " + id);
    }

    public Person showByID(int id) {

        Person person =    jdbcTemplate.query("Select* FROM Person WHERE id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
        person.setListOfBooks(jdbcTemplate.query("SELECT * FROM books WHERE person_id=?",
                new Object[]{id},
                new BookMapper()));


        return person;
    }

    public Person showListOfBooks(int id) {
        Person person = showByID(id);


        return person;

    }





    public void deletePerson(int id) {

        jdbcTemplate.update("DELETE from Person WHERE id = ?", id);

    }


}
