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
    public Label errorBox;

    Globals generated = new Globals();

    public void createUserAction(ActionEvent actionEvent) {

        String fname = fnameFX.getText();
        String lname = lnameFX.getText();
        String email = emailFX.getText();
        String loginName = generated.generateLogin();
        String pass = generated.generatePass();

        boolean blank = validBlank(fname,lname,email);
        boolean emailB = validEmail(email);
        boolean length = validLength(fname,lname,email);

       // System.out.println("Validation result "+validUser(blank,length,emailB));

        if(validUser(blank,length,emailB)){
            System.out.println("Generated login"+loginName);
            System.out.println("Generated pass"+pass);

            Globals.db.insertUser(fname,lname,email,loginName,pass);

            newLogin.setText(loginName);
            newPass.setText(pass);
        }

    }

    public Boolean validBlank(String fname,String lname,String email){
        if( fname.equals("") || lname.equals("") || email.equals("")){
            return false;
        }
        else
        {
            return true;
        }
    }


    public Boolean validLength(String name,String fname,String email){
        int nameL = name.length();
        //System.out.println(nameL);
        int fnameL = fname.length();
        //System.out.println(fnameL);
        int emailL = email.length();
        //System.out.println(emailL);

        if(nameL >= 15 || fnameL >= 15 || emailL >= 30){
            return false;
        }
        else
        {
            return true;
        }

    }

    public Boolean validEmail(String email){

        if(email.contains("@")){
            return true;
        }
        else
        {
            return false;
        }

    }

    public Boolean validUser(boolean blank,boolean length,boolean email){

        if(blank){
            if(length){
                if(email){
                    return true;
                }
                else
                {
                    //System.out.println("wrong email format");
                    errorBox.setText("Wrong e-mail format type");
                    return false;
                }
            }
            else
            {
                //System.out.println("");
                errorBox.setText("First and Last name or mail \n has length more than 15");
                return false;
            }
        }
        else
        {
            //System.out.println("Fields are blank!");
            errorBox.setText("Registration Fields are blank!");
            return false;
        }
    }

}
