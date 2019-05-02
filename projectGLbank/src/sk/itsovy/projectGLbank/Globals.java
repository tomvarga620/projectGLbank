package sk.itsovy.projectGLbank;

import sk.itsovy.projectGLbank.database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;

public class Globals {

    public static String username= "root";
    public static String password= "Dadada5";
    public static final String host = "localhost";
    public static final String port = "3306";
    public static final String url = "jdbc:mysql://localhost:3306/glbank";
    public static final Database db = Database.getInstance();

    public String generateLogin(){

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcefghijklmnopqrst1234567890";
        StringBuilder randomstring = new StringBuilder();
        Random rnd = new Random();
        while (randomstring.length() < 5) {
            int index = (int) (rnd.nextFloat() * chars.length());
            randomstring.append(chars.charAt(index));
        }
        String saltStr = randomstring.toString();
        return saltStr;

    }

    public String generatePass(){

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcefghijklmnopqrst1234567890";
        StringBuilder randomstring = new StringBuilder();
        Random rnd = new Random();
        while (randomstring.length() < 8) {
            int index = (int) (rnd.nextFloat() * chars.length());
            randomstring.append(chars.charAt(index));
        }
        String saltStr = randomstring.toString();
        return saltStr;

    }

    public String generatePIN(){

        String chars = "1234567890";
        StringBuilder randomstring = new StringBuilder();
        Random rnd = new Random();
        while (randomstring.length() < 4) {
            int index = (int) (rnd.nextFloat() * chars.length());
            randomstring.append(chars.charAt(index));
        }
        String saltStr = randomstring.toString();
        return saltStr;

    }

}
