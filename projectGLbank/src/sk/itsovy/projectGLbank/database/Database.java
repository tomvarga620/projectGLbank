package sk.itsovy.projectGLbank.database;

import sk.itsovy.projectGLbank.Account;
import sk.itsovy.projectGLbank.Client;
import sk.itsovy.projectGLbank.Employee;
import sk.itsovy.projectGLbank.Globals;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Database {

    public static final String queryEmployee = "SELECT * FROM Employee INNER JOIN loginEmp ON Employee.ID = loginEmp.IDE WHERE login LIKE ? and password LIKE ?";
    public static final String queryClient = "SELECT * FROM Client";
    public static final String queryAccount = "SELECT * FROM Account where IDC like ? ";
    public static final String queryClientInfo = "SELECT * FROM Client where ID like ? ";
    public static final String queryAccountInfo = "SELECT * FROM Account where id like ? ";

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

            pst = conn.prepareStatement(queryEmployee);
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

    public ArrayList<Client> selectClientsToList(){
        Connection conn = getConnection();
        //String query = "SELECT * FROM persons WHERE dnar <= Current_date() - 18 ";
        ArrayList <Client> clientList = new ArrayList<>();

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(queryClient);
            rs = pst.executeQuery();

            while (rs.next()) {

                Client client = new Client(rs.getString("fname"),rs.getString("lname"),rs.getString("email"),rs.getInt("ID"));
                clientList.add(client);
            }
            return clientList;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    public ArrayList<Account> selectAccountsToList(int id) {

        Connection conn = getConnection();

        ArrayList <Account> accounts = new ArrayList<>();
        try {

            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(queryAccount);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            while (rs.next()) {

               Account acc = new Account(rs.getInt("ID"),rs.getString("AccNum"),
                        rs.getDouble("amount"),rs.getInt("IDC"));
                System.out.println(acc.getAccNum());
                accounts.add(acc);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return accounts;
    }


    public Client selectClientInfo(int id){
        Connection conn = getConnection();
        Client client = null;

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(queryClientInfo);
            pst.setInt(1,id);
            rs = pst.executeQuery();

            while (rs.next()) {

                client = new Client(rs.getString("fname"),rs.getString("lname"),rs.getString("email"),
                rs.getInt("ID"));

            }
            return client;

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }


    public Account selectAccInfo(int id){
        Connection conn = getConnection();
        Account acc = null;

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(queryAccountInfo);
            pst.setInt(1,id);
            rs = pst.executeQuery();

            while (rs.next()) {

                acc = new Account(rs.getInt("id"),rs.getString("AccNum"),rs.getDouble("amount"),
                        rs.getInt("idc"));

            }
            return acc;

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }


}
