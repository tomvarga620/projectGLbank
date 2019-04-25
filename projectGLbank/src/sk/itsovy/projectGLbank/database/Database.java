package sk.itsovy.projectGLbank.database;

import sk.itsovy.projectGLbank.*;
import sun.tools.tree.BooleanExpression;

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
    public static final String queryCards = "SELECT * FROM card WHERE ida LIKE ?";
    public static final String queryInsertClient = "INSERT INTO client(fname,lname,email) VALUES(?,?,?) ";
    public static final String queryInsertUser = "INSERT INTO loginclient(idc,login,password) VALUES(?,?,?)";
    public static final String queryIfUserExists = "SELECT * from loginclient where idc = ? and login = ? and password = ?";
    public static final String queryUnblockCard = "UPDATE card SET Active = 1  WHERE id = ? and ida = ?";
    public static final String queryBlockCard = "UPDATE card SET Active = 0  WHERE id = ? and ida = ?";
    public static final String queryIfPassExists = "SELECT * from loginclient where idc = ? and password = ? ";
    public static final String queryInsertPass = "UPDATE loginclient SET password = ?  WHERE idc = ? ";


    //singleton database
    private static Database db = new Database();

    private Database(){

    }

    public static Database getInstance() {
        return db;
    }


    //get connection to mysql
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

    //method for validate login
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


    //method for position of employee
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

    //method for select clients to combobox
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

    //method for select accounts to combobox
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

    //select client info to labels in client tab
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

    // select account info to labels in account tab
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

    //select cards to comboboxCards
    public ArrayList<Card> clientCards(int idacc){
        Connection conn = getConnection();
        ArrayList <Card> cardList = new ArrayList<>();

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(queryCards);
            pst.setInt(1,idacc);
            rs = pst.executeQuery();

            while (rs.next()) {

                Card card = new Card(rs.getInt("id"),rs.getInt("ida"),rs.getString("PIN"),
                rs.getInt("ExpireM"),rs.getInt("ExpireY"),rs.getBoolean("Active"));
                cardList.add(card);
            }
            return cardList;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    public void insertUser(String fname,String lname,String email,String loginName,String pass){
        int id = 0;
        try {
            Connection conn = getConnection();

            PreparedStatement pst1 = conn.prepareStatement(queryInsertClient,Statement.RETURN_GENERATED_KEYS);
            PreparedStatement pst2 = conn.prepareStatement(queryInsertUser);
            //client table
            pst1.setString(1,fname);
            pst1.setString(2,lname);
            pst1.setString(3,email);

            int insert1 = pst1.executeUpdate();


            ResultSet rs = pst1.getGeneratedKeys();
            while(rs.next()){
                System.out.println("Result Set: " + rs.toString());
                id = rs.getInt(1);
            }

            //loginclient table
            pst2.setInt(1,id);
            pst2.setString(2,loginName);
            pst2.setString(3,pass);

            int insert2 = pst2.executeUpdate();

            System.out.println("User inserted");

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Boolean checkUserExists(int id, String loginName, String password){

        Connection conn = getConnection();

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(queryIfUserExists);
            pst.setInt(1,id);
            pst.setString(2,loginName);
            pst.setString(3,password);
            rs = pst.executeQuery();

            boolean bool = rs.next();

            return bool;


        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;

    }

    public int unblockCard(int id , int ida ){

        Connection conn = getConnection();

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(queryUnblockCard);
            pst.setInt(1,id);
            pst.setInt(2,ida);
            int rslt= pst.executeUpdate();

            //System.out.println(rslt);
            System.out.println("Card is actived");
            return rslt;


        }catch(SQLException e){
            e.printStackTrace();
        }

        return 0;

    }

    public int blockCard(int id , int ida ){

        Connection conn = getConnection();

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(queryBlockCard);
            pst.setInt(1,id);
            pst.setInt(2,ida);
            int rslt= pst.executeUpdate();

            //System.out.println(rslt);
            System.out.println("Card is blocked");
            return rslt;


        }catch(SQLException e){
            e.printStackTrace();
        }

        return 0;

    }

    public Boolean checkPassExists(int idc,String password){

        Connection conn = getConnection();

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(queryIfPassExists);
            pst.setInt(1,idc);
            pst.setString(2,password);
            rs = pst.executeQuery();

            boolean bool = rs.next();

            return bool;


        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;

    }

    public int resetPass(int idc,String password){

        Connection conn = getConnection();

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(queryInsertPass);
            pst.setString(1,password);
            pst.setInt(2,idc);
            int rslt= pst.executeUpdate();

            //System.out.println(rslt);
            System.out.println("Password is reseted");
            return rslt;


        }catch(SQLException e){
            e.printStackTrace();
        }

        return 0;

    }

}
