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
    Globals generated = new Globals();

    public void createUserAction(ActionEvent actionEvent) {

        String fname = fnameFX.getText();
        String lname = lnameFX.getText();
        String email = emailFX.getText();
        String loginName = loginFX.getText();
        String pass = passwordFX.getText();

        System.out.println(generated.generateLogin());
        System.out.println(generated.generatePass());

        Globals.db.insertUser(fname,lname,email,loginName,pass);

    }

}
