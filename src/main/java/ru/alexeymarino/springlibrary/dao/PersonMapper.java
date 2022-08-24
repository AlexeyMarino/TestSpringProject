package ru.alexeymarino.springlibrary.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.alexeymarino.springlibrary.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setPersonId(rs.getInt("person_id"));
        person.setFullName(rs.getString("full_name"));
        person.setBirthYear(rs.getInt("birth_year"));
        return person;
    }
}
