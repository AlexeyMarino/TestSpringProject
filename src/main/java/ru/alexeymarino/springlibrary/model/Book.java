package ru.alexeymarino.springlibrary.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int bookId;
    private int personId;
    @NotEmpty(message = "Название книги не может быть пустым")
    @Size(min = 2, max = 200, message = "Название книги может быть длинной от 1 до 200 символов")
    private String title;
    @NotEmpty(message = "Имя автора не может быть пустым")
    @Size(min = 2, max = 35, message = "Имя может быть длинной от 2 до 35 символов")
    private String author;
    @Min(value = 1000, message = "Год публикации должен быть больше 1000")
    private int publicationYear;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
}
