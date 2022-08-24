package ru.alexeymarino.springlibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.alexeymarino.springlibrary.model.Book;
import ru.alexeymarino.springlibrary.model.Person;

import java.util.List;

@Repository
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public Person getById(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE person_id = ?", new Object[] {id}, new PersonMapper()).stream()
                .findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(full_name, birth_year) VALUES(?, ?)", person.getFullName(), person.getBirthYear());
    }

    public void update(Person person) {
        jdbcTemplate.update("UPDATE person SET full_name=?, birth_year=? WHERE person_id=?", person.getFullName(), person.getBirthYear(), person.getPersonId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person  WHERE person_id=?", id);
    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}
