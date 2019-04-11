package sample;
import com.sun.glass.events.WindowEvent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Database.Database;

import java.io.IOException;


public class Controller {

    public TextField username;
    public TextField pass;
    public Button button;

    public void login(ActionEvent actionEvent) {
        String name = username.getText();
        String password = pass.getText();
        Database db = Database.getInstance();
        Employee person = db.compareEmployee(name,password);

        //TODO metoda pre validaciu
        if(person != null){
            System.out.println(person.getFirstname());
            System.out.println(person.getPosition());
            String position = db.getPosition(person);
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();

            AfterLog after = new AfterLog();
            after.setupAfterlog(person.getFirstname(),person.getSurname(),position);
        }
        if(person == null){

        }

    }
}
