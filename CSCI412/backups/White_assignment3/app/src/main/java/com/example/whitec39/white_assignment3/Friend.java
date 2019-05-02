package com.example.whitec39.white_assignment3;

/**
 * Created by whitec39 on 10/31/18.
 */

public class Friend {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Friend( int id, String firstName, String lastName, String email ) {
        setId(id);
        setFirstName( firstName );
        setLastName( lastName );
        setEmail( email );
    }


    public void setId( int newId ) {
        id = newId;
    }
    public void setFirstName( String name ) {
        firstName = name;
    }

    public void setLastName( String name ) {
        lastName = name;
    }

    public void setEmail( String mail ) {
        email = mail;
    }

    public int getId( ) {
        return id;
    }

    public String getFirstName( ) {
        return firstName;
    }

    public String getLastName( ) {
        return lastName;
    }

    public String getEmail( ) {
        return email;
    }

    public String toString( ) {
        return id + "; " + firstName + "; " + lastName + "; " + email;
    }
}
