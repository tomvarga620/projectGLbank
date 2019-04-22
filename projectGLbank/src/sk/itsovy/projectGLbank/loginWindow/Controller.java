package sk.itsovy.projectGLbank.loginWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sk.itsovy.projectGLbank.Employee;
import sk.itsovy.projectGLbank.Globals;
import sk.itsovy.projectGLbank.database.Database;
import sk.itsovy.projectGLbank.afterLogWindow.AfterLog;

import java.io.IOException;


public class Controller {

    public TextField username;
    public TextField pass;
    public Button button;

    public void login(ActionEvent actionEvent) {
        String name = username.getText();
        String password = pass.getText();
        Employee person = Globals.db.compareEmployee(name,password);

        //TODO metoda pre validaciu
        if(person != null){
            System.out.println(person.getFirstname());
            System.out.println(person.getPosition());
            String position = Globals.db.getPosition(person);
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../afterLogWindow/afterLog.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage2 = new Stage();
                stage2.setTitle("After log");
                stage2.setScene(new Scene(root1));

                AfterLog acc;
                acc = fxmlLoader.getController();
                acc.setupAfterlog(person,position);

                stage2.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(person == null){
            System.out.println("bad login");
            username.setText("");
            pass.setText("");
        }

    }

    /*public void toCreateUser(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../createuserWindow/createUser.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage3 = new Stage();
            stage3.setTitle("Create User");
            stage3.setScene(new Scene(root1));

            stage3.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/
}
