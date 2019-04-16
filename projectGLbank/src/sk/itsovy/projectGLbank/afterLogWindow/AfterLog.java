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
import sk.itsovy.projectGLbank.Account;
import sk.itsovy.projectGLbank.Client;
import sk.itsovy.projectGLbank.Employee;
import sk.itsovy.projectGLbank.Globals;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
    ArrayList<Account> accList;

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
    ComboBox combobox2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test");
        fillDropdown();
       // Globals.db.selectAccountsToList(2); select ide
        /*accList = Globals.db.selectAccountsToList(1);
        for(int i=0; i<accList.size();i++) {
            System.out.println(accList.get(i).getAccNum());
        }*/

    }

    public void fillDropdown(){
        clientList = Globals.db.selectClientsToList();
        ObservableList<String> oblist = FXCollections.observableArrayList();

        for(int i=0; i<clientList.size();i++) {
            oblist.add(clientList.get(i).getFirstName() + " " + clientList.get(i).getLastName());
        }

        combobox.setItems(oblist);
        //System.out.println(combobox.getItems().size());
    }


    public void fillDropdown2(){

        accList = Globals.db.selectAccountsToList(clientList.get(getIDClient()).getId());
        ObservableList<String> oblist = FXCollections.observableArrayList();

        for(int i=0; i<accList.size();i++) {
            oblist.add(accList.get(i).getIDacc() + " " + accList.get(i).getAccNum());
        }

        combobox2.setItems(oblist);
    }

    /*
    public void clientFill(ActionEvent actionEvent) {
       clientName.setText(combobox.getValue().toString());
    }*/

    public void clientInfo() throws SQLException {

        Client selectedUser=Globals.db.selectClientInfo(clientList.get(getIDClient()).getId());

       // System.out.println(selectedUser);

        clientName.setText(selectedUser.getFirstName());
        clientSurname.setText(selectedUser.getLastName());
        clientMail.setText(String.valueOf(selectedUser.getEmail()));

        fillDropdown2();

    }

    public int getIDClient() {
        //System.out.println(combobox.getSelectionmModel().getSelectedIndex());
        return combobox.getSelectionModel().getSelectedIndex();
    }

    /*public int getIDAccount() {
       // System.out.println(combobox.getSelectionModel().getSelectedIndex());
        return combobox.getSelectionModel().getSelectedIndex();
    }*/

}
