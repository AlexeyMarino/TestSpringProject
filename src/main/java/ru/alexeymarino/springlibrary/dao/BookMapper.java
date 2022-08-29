package ru.alexeymarino.springlibrary.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.alexeymarino.springlibrary.model.Book;
import ru.alexeymarino.springlibrary.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getInt("book_id"));
        book.setPersonId(rs.getInt("person_id"));
        book.setAuthor(rs.getString("author"));
        book.setTitle(rs.getString("title"));
        book.setPublicationYear(rs.getInt("publication_year"));
        return book;
    }
}
