package sample.Database;

import sample.Employee;
import sample.Globals;

import java.sql.*;

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
        Employee person;
        try {

            Connection conn = getConnection();

            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement("select * from loginEmp");
            rs = pst.executeQuery();

            while(rs.next()){
               // System.out.println(rs.getString("login"));
                String tempname = rs.getString("login");
                String tempass = rs.getString("password");
                if(tempname.equals(name) && tempass.equals(pass)){
                    System.out.println("it works");
                }
                else
                {
                    System.out.println("bad login");
                    return null;
                }
            }


           /* while (rs.next()) {
                person = new Employee(rs.getString(),);
            }*/


        }catch (SQLException e){
            e.printStackTrace();
        }
        //return person;
        return null;
    }

}
