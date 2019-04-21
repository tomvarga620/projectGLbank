package sk.itsovy.projectGLbank.afterLogWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sk.itsovy.projectGLbank.*;

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

    //account labels
    public Label accountIDField;
    public Label accNumField;
    public Label amountField;

    //IB
    public TextField IBusername;
    public TextField IBpass;

    ArrayList<Client> clientList;
    ArrayList<Account> accList;
    ArrayList<Card> cardList;

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
    @FXML
    ComboBox comboboxAcc;
    @FXML
    ComboBox comboboxCards;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test");
        fillDropdown();
       // Globals.db.selectAccountsToList(2); select ide
    }

    public void fillDropdown(){
        clientList = Globals.db.selectClientsToList();
        ObservableList<String> oblist = FXCollections.observableArrayList();

        for(int i=0; i<clientList.size();i++) {
            oblist.add(clientList.get(i).getFirstName() + " " + clientList.get(i).getLastName());
        }

        combobox.setItems(oblist);
        System.out.println(combobox.getItems().size());
    }


    public void fillDropdown2(){
        //accList = Globals.db.selectAccountsToList(clientList.get(getIDClient()).getId());
        accList = Globals.db.selectAccountsToList(getIDClient());
        ObservableList<String> oblist = FXCollections.observableArrayList();

        for(int i=0; i<accList.size();i++) {
            oblist.add(accList.get(i).getIDacc() + " " + accList.get(i).getAccNum());
        }

        comboboxAcc.setItems(oblist);
    }

    public void fillDropdown3(){
        cardList = Globals.db.clientCards(getIDAccount());
        ObservableList<String> oblist = FXCollections.observableArrayList();

        for(int i=0; i<cardList.size();i++) {
            oblist.add(cardList.get(i).getId() + " " + cardList.get(i).getIda() + " yy/mm " +cardList.get(i).getExpireM() +"/"+ cardList.get(i).getExpireY());
        }

        comboboxCards.setItems(oblist);
        System.out.println(comboboxCards.getItems().size());
    }

    /*
    public void clientFill(ActionEvent actionEvent) {
       clientName.setText(combobox.getValue().toString());
    }*/

    public void clientInfo() throws SQLException {

        Client selectedUser=Globals.db.selectClientInfo(getIDClient());

       // System.out.println(selectedUser);

        clientName.setText(selectedUser.getFirstName());
        clientSurname.setText(selectedUser.getLastName());
        clientMail.setText(String.valueOf(selectedUser.getEmail()));

        fillDropdown2();

    }

    public void AccInfo() throws SQLException {

        Account acc = Globals.db.selectAccInfo(getIDAccount());
        accountIDField.setText(String.valueOf(acc.getIDacc()));
        accNumField.setText(acc.getAccNum());
        amountField.setText(String.valueOf(acc.getMoney()));

        fillDropdown3();
    }

    public int getIDClient() {
        //System.out.println(combobox.getSelectionModel().getSelectedIndex());
        return clientList.get(combobox.getSelectionModel().getSelectedIndex()).getId();
    }

    public int getIDAccount() {
       // System.out.println(combobox.getSelectionModel().getSelectedIndex());
        //return combobox.getSelectionModel().getSelectedIndex();
        //return comboboxAcc.getSelectionModel().getSelectedIndex();
        return accList.get(comboboxAcc.getSelectionModel().getSelectedIndex()).getIDacc();
    }

}