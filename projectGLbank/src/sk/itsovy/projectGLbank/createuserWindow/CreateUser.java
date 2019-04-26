package sk.itsovy.projectGLbank.createuserWindow;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sk.itsovy.projectGLbank.Globals;

import java.util.Random;

public class CreateUser {

    public TextField fnameFX;
    public TextField lnameFX;
    public TextField emailFX;
    public Label newLogin;
    public Label newPass;

    Globals generated = new Globals();

    public void createUserAction(ActionEvent actionEvent) {

        String fname = fnameFX.getText();
        String lname = lnameFX.getText();
        String email = emailFX.getText();
        String loginName = generated.generateLogin();
        String pass = generated.generatePass();

        System.out.println(loginName);
        System.out.println(pass);

        newLogin.setText(loginName);
        newPass.setText(pass);

        Globals.db.insertUser(fname,lname,email,loginName,pass);

    }

}
