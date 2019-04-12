package sk.itsovy.projectGLbank.database;

import sk.itsovy.projectGLbank.Client;
import sk.itsovy.projectGLbank.Employee;
import sk.itsovy.projectGLbank.Globals;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {


    private static Database db = new Database();

    private Database(){

    }

    public static Database getInstance() {
        return db;
    }

    public static Connection getConnection(){
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("DriverLoaded");
            connection = DriverManager.getConnection(Globals.url, Globals.username, Globals.password);
            return connection;
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }


    public Employee compareEmployee(String name, String pass){

        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs;

        try {

            pst = conn.prepareStatement("SELECT * FROM Employee INNER JOIN loginEmployee ON Employee.ID = loginEmployee.IDEmployee WHERE login LIKE ? and password LIKE ?" );
            pst.setString(1,name);
            pst.setString(2,pass);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("It works");
                //System.out.println(rs.getString("fname"));

                Employee person = new Employee(rs.getString("fname"), rs.getString("lname"),
                        rs.getInt("position"), rs.getInt("id"));
                return person;
            }
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String getPosition(Employee emp){
        int position = emp.getPosition();
        String boss = "boss";
        String common = "common";

        if (position == 1) {
            return common;
        }
        else
        {
            return boss;
        }
    }

    public List <Client> selectClients(){
        Connection conn = getConnection();
        //String query = "SELECT * FROM persons WHERE dnar <= Current_date() - 18 ";
        String query = "SELECT * FROM Client";
        List <Client> clients = new ArrayList<>();

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {

                Client client = new Client(rs.getString("fname"),rs.getString("lname"));
                clients.add(client);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return clients;
    }

}
