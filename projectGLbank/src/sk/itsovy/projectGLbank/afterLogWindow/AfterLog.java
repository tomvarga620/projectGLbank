package sk.itsovy.projectGLbank.afterLogWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sk.itsovy.projectGLbank.Client;
import sk.itsovy.projectGLbank.Employee;
import sk.itsovy.projectGLbank.Globals;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class AfterLog implements Initializable {

    public Label afterLogName;
    public Label afterLogSurname;
    public Label afterLogPosition;
    public Button logout;

    public void setupAfterlog(Employee person, String position) {

        String name = person.getFirstname();
        String surname = person.getSurname();

        afterLogName.setText(name);
        afterLogSurname.setText(surname);
        afterLogPosition.setText(position);

        //afterLogName.setText("test");
    }

    public void logoutbtn(ActionEvent actionEvent) {
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
    }

    @FXML
    ComboBox<String> combobox;
    ObservableList<String> list = FXCollections.observableList(Globals.db.listToString(Globals.db.selectClients()));

    /*ComboBox<Client> combobox;
    ObservableList<Client> list = FXCollections.observableList(Globals.db.selectClients());*/


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test");
        combobox.setItems(list);
    }

}
