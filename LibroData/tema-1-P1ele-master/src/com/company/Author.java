package com.company;

public class Author {
    int ID;
    String firstName;
    String lastName;

    public Author() {
    }

    public Author(int id, String firstName, String lastName)
    {
        this.ID = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() { return ID; }

    public void setId(int id) {
        this.ID = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString()
    {
        return firstName + " " + lastName;
    }
}
