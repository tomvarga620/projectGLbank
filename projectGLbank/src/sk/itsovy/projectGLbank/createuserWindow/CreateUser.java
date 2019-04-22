package sk.itsovy.projectGLbank.createuserWindow;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.util.Random;

public class CreateUser {

    public TextField fnameFX;
    public TextField lnameFX;
    public TextField emailFX;
    public TextField loginFX;
    public TextField passwordFX;

    public void createUserAction(ActionEvent actionEvent) {

        fnameFX.getText();
        lnameFX.getText();
        emailFX.getText();
        loginFX.getText();
        passwordFX.getText();

        System.out.println(generateLogin());
        System.out.println(generatePassword());

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

    public String generatePassword(){

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
