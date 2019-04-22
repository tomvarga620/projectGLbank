package sk.itsovy.projectGLbank.createuserWindow;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import sk.itsovy.projectGLbank.Globals;

import java.util.Random;

public class CreateUser {

    public TextField fnameFX;
    public TextField lnameFX;
    public TextField emailFX;
    public TextField loginFX;
    public TextField passwordFX;

    public void createUserAction(ActionEvent actionEvent) {

        String fname = fnameFX.getText();
        String lname = lnameFX.getText();
        String email = emailFX.getText();
        String loginName = loginFX.getText();
        String pass = passwordFX.getText();

        System.out.println(generateLogin());
        System.out.println(generatePass());

        Globals.db.insertUser(fname,lname,email,loginName,pass);

    }

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

}
