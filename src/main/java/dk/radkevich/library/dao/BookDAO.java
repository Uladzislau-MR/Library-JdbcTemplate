package dk.radkevich.library.dao;

import dk.radkevich.library.models.Book;

import dk.radkevich.library.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;


    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("Select * FROM books", new BookMapper());

    }

    public void saveBook(Book book) {
        jdbcTemplate.update("INSERT INTO Book (name,author,year) VALUES (?,?,?) ", book.getName(), book.getAuthor(), book.getYear());

    }

    public void updateBook(int id, Book updatedBook) {

        jdbcTemplate.update("UPDATE books SET name=?,author=?,year=? WHERE id=?", updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYear(), id);
        System.out.println("ID переданный в метод updateBook: " + id);

    }

    public void setReader( int id,Person person) {
        jdbcTemplate.update("UPDATE books SET person_id=? WHERE id=?",person.getId(),id);
    }
//тоесть book id will be taken from book page, and person_id wil be taken from view
    public void releaseBook(int id) {
        jdbcTemplate.update("UPDATE books SET person_id=null WHERE id=?", id);
    }

    public Book showBookByID(int id) {
        return jdbcTemplate.query("Select* FROM books WHERE id=?", new Object[]{id}, new BookMapper()).stream().findAny().orElse(null);



    }

    public Person getBookReader(int id) {

        Person person = jdbcTemplate.query("Select full_name from person where id=(SELECT person_id from books where id = ?) ",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
        return person;
    }


    public void deleteBook(int id) {

        jdbcTemplate.update("DELETE from books WHERE id = ?", id);

    }
}