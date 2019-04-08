package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static sample.Database.Database.getConnection;

public class Employee {

    private String Firstname;
    private String Surname;
    private int position;
    private int id;

    public Employee(String firstname, String surname, int position, int id) {
        Firstname = firstname;
        Surname = surname;
        this.position = position;
        this.id = id;
    }


    public String getFirstname() {
        return Firstname;
    }

    public String getSurname() {
        return Surname;
    }

    public int getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }
}
