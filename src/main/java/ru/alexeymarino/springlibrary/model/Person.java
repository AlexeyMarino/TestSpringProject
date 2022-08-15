package ru.alexeymarino.springlibrary.model;

public class Person {
    private int personId;
    private String follName;
    private int birthYear;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFollName() {
        return follName;
    }

    public void setFollName(String follName) {
        this.follName = follName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
