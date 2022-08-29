package ru.alexeymarino.springlibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.alexeymarino.springlibrary.model.Book;
import ru.alexeymarino.springlibrary.model.Person;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate, PersonDAO personDAO) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAll() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getById(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id = ?", new Object[] {id}, new BeanPropertyRowMapper<>(Book.class)).stream()
                .findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, publication_year) VALUES(?, ?, ?)", book.getTitle(), book.getAuthor(), book.getPublicationYear());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET title=?, author=?, publication_year=? WHERE book_id=?", book.getTitle(), book.getAuthor(), book.getPublicationYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM book JOIN person ON book.person_id = person.person_id WHERE book.book_id = ?", new Object[] {id}, new BeanPropertyRowMapper<>(Person.class)).stream()
                .findAny();
    }

    public void take(int id, Person person) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", person.getPersonId(), id);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE book SET person_id=NULL WHERE book_id=?", id);
    }


}
