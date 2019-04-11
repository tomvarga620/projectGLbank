package sample;
import com.sun.glass.events.WindowEvent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        System.out.println(person.getFirstname());
        //Platform.exit();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("afterLog.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage2 = new Stage();
            stage2.setTitle("ABC");
            stage2.setScene(new Scene(root1));
            stage2.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
