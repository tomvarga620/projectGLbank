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
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

public class AfterLog implements Initializable {

    public Label afterLogName;
    public Label afterLogSurname;
    public Label afterLogPosition;
    public Button logout;

    //client fill
    public Label clientName;
    public Label clientSurname;
    public Label clientMail;

    ArrayList<Client> clientList;

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
    ComboBox combobox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test");
        fillDropdown();
    }

    public void fillDropdown(){
        clientList = Globals.db.selectClientsToList();
        ObservableList<String> oblist = FXCollections.observableArrayList();

        for(int i=0; i<clientList.size();i++) {
            oblist.add(clientList.get(i).getFirstName() + " " + clientList.get(i).getLastName());
        }

        combobox.setItems(oblist);
    }
    //TODO metoda ktora mi vrati id z comboxu
    public void clientFill(ActionEvent actionEvent) {
       clientName.setText(combobox.getValue().toString());
    }
}
