package sk.itsovy.projectGLbank;

import sk.itsovy.projectGLbank.database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Globals {

    public static String username= "root";
    public static String password= "Dadada5";
    public static final String host = "localhost";
    public static final String port = "3306";
    public static final String url = "jdbc:mysql://localhost:3306/glbank";
    public static final Database db = Database.getInstance();

}
