package sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AfterLog {

    String title;
    public Label afterLogName;
    public Label afterLogSurname;
    public Label afterLogPosition;

    public void setupAfterlog(String name, String surname, String position) {

        System.out.println(name+surname+position);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("afterLog.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage2 = new Stage();
            stage2.setTitle("After log");
            afterLogName = new Label(name);
            stage2.setScene(new Scene(root1));
            stage2.show();

        } catch (
                IOException e) {
            e.printStackTrace();
        }




    }

}
