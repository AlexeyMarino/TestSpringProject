package ru.alexeymarino.springlibrary.model;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int personId;
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 35, message = "Имя может быть длинной от 2 до 35 символов")
    private String fullName;
    @Min(value = 1930, message = "Год рождения должен быть больше 1930")
    private int birthYear;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
