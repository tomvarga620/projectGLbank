package sk.itsovy.projectGLbank.afterLogWindow;

import com.sun.glass.events.WindowEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sk.itsovy.projectGLbank.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
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

    //checkbox blocking client
    public CheckBox blockCheck;

    //generated pass label
    public Label newPass;
    public Label ibPassRslt;

    //tabs
    public Tab tabAcc;
    public Tab tabIB;
    public Tab tabCards;
    public Tab tabTransactions;

    //adding account
    public Label afterAddAccount;
    public Label cardRslt;
    public Label pinHeader;
    public Label pinRslt;

    //transactions
    public TextField depositText;
    public TextField withdrawText;
    public Label addedMoney;
    public Label deletedMoney;

    ArrayList<Client> clientList;
    ArrayList<Account> accList;
    ArrayList<Card> cardList;

    Globals generated = new Globals();

    //comboboxes
    @FXML
    ComboBox combobox;
    @FXML
    ComboBox comboboxAcc;
    @FXML
    ComboBox comboboxCards;


    public void setupAfterlog(Employee person, String position) {

        String name = person.getFirstname();
        String surname = person.getSurname();

        afterLogName.setText(name);
        afterLogSurname.setText(surname);
        afterLogPosition.setText(position);
        tabAcc.setDisable(true);
        tabIB.setDisable(true);
        tabCards.setDisable(true);
        tabTransactions.setDisable(true);

    }

    public void logoutbtn(ActionEvent actionEvent) {
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test");
        fillDropdownClients();
    }

    public void fillDropdownClients(){
        clientList = Globals.db.selectClientsToList();
        ObservableList<String> oblist = FXCollections.observableArrayList();

        for(int i=0; i<clientList.size();i++) {
            oblist.add(clientList.get(i).getFirstName() + " " + clientList.get(i).getLastName());
        }

        combobox.setItems(oblist);
        System.out.println(combobox.getItems().size());
    }


    public void fillDropdownAccounts(){
        accList = Globals.db.selectAccountsToList(getIDClient());
        ObservableList<String> oblist = FXCollections.observableArrayList();

        for(int i=0; i<accList.size();i++) {
            oblist.add(accList.get(i).getIDacc() + " " + accList.get(i).getAccNum());
        }

        comboboxAcc.setItems(oblist);
    }

    public void fillDropdownCards(){
        cardList = Globals.db.clientCards(getIDAccount());
        ObservableList<String> oblist = FXCollections.observableArrayList();

        for(int i=0; i<cardList.size();i++) {
            oblist.add(cardList.get(i).getId() + " " + cardList.get(i).getIda() + " yy/mm " +cardList.get(i).getExpireM() +"/"+ cardList.get(i).getExpireY());
        }

        comboboxCards.setItems(oblist);
        System.out.println(comboboxCards.getItems().size());
    }

    public void clientInfo() throws SQLException {

        Client selectedUser=Globals.db.selectClientInfo(getIDClient());

       // System.out.println(selectedUser);
        tabAcc.setDisable(false);

        clientName.setText(selectedUser.getFirstName());
        clientSurname.setText(selectedUser.getLastName());
        clientMail.setText(String.valueOf(selectedUser.getEmail()));

        fillDropdownAccounts();

        accountIDField.setText("");
        accNumField.setText("");
        amountField.setText("");
        comboboxCards.getSelectionModel().clearSelection();
        comboboxCards.getItems().clear();

    }

    public void AccInfo() throws SQLException {

        Account acc = Globals.db.selectAccInfo(getIDAccount());
        accountIDField.setText(String.valueOf(acc.getIDacc()));
        accNumField.setText(acc.getAccNum());
        amountField.setText(String.valueOf(acc.getMoney()));

        tabIB.setDisable(false);
        tabCards.setDisable(false);
        tabTransactions.setDisable(false);
        fillDropdownCards();

    }

    public int getIDClient() {
        return clientList.get(combobox.getSelectionModel().getSelectedIndex()).getId();
    }

    public int getIDAccount() {
        return accList.get(comboboxAcc.getSelectionModel().getSelectedIndex()).getIDacc();
    }

    public int getIDcard() {
        return cardList.get(comboboxCards.getSelectionModel().getSelectedIndex()).getId();
    }

    public void createUser(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../createuserWindow/createUser.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage3 = new Stage();
            stage3.setTitle("Create User");
            stage3.setScene(new Scene(root1));

            stage3.show();

            stage3.setOnCloseRequest( event ->
            {
                System.out.println("CLOSING");
                fillDropdownClients();

            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //generate account number for client's account
    public String generateAccNum(){
        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        return String.valueOf(number);
    }

    public void unblockBtnAction(ActionEvent actionEvent) {
        Globals.db.unblockCard(getIDClient(),getIDAccount());
    }

    public void blockBtnAction(ActionEvent actionEvent) {
        Globals.db.blockCard(getIDClient(),getIDAccount());
    }


    public void blockCheckAction(ActionEvent actionEvent) {

        if(blockCheck.isSelected()) {
            System.out.println("test");
            Globals.db.blockUserLogin(getIDClient());
        }else {
            System.out.println("test2");
            Globals.db.unblockUserLogin(getIDClient());
        }

    }

    public void resetPassAction(ActionEvent actionEvent) {
        System.out.println("test btn");
        String newpass = generated.generatePass();
        Globals.db.resetPass(getIDClient(),newpass);
        ibPassRslt.setText("Password is reseted, new password is:");
        newPass.setText(newpass);
    }

    public void addAccountAction(ActionEvent actionEvent) {
        Globals.db.createAcc(getIDClient(),generateAccNum());
        afterAddAccount.setText("Account added");
        fillDropdownAccounts();
    }

    public void addCardAction(ActionEvent actionEvent) {
        String PIN = generated.generatePIN();
        int mm = getMonth();
        int yy = getYear();

        Globals.db.createCard(getIDAccount(),PIN,mm,yy);
        fillDropdownCards();
        cardRslt.setText("New Card Added");
        pinHeader.setText("New PIN is:");
        pinRslt.setText(PIN);
    }

    public void depositAction(ActionEvent actionEvent) {
        addedMoney.setText("");
        String amountTest = depositText.getText();

        if(amountTest.matches("[\\d]+")){
            System.out.println("num");
            int id = getIDAccount();
            double amount = Double.parseDouble(amountTest);
            Globals.db.depositMoney(id,amount);
            addedMoney.setText(amount+" € added to your account");
            depositText.setText("");
            fillDropdownClients();
            fillDropdownAccounts();
            fillDropdownCards();
        }
        else
        {
            addedMoney.setText("Wrong type format");
        }

    }

    public void withdrawAction(ActionEvent actionEvent) {
        deletedMoney.setText("");
        int id = getIDAccount();
        String amountTest = withdrawText.getText();
        if(amountTest.matches("[\\d]+")){
            System.out.println("num");
            double actualAmount = accList.get(comboboxAcc.getSelectionModel().getSelectedIndex()).getMoney();
            double amount = Double.parseDouble(amountTest);

            if((actualAmount-amount) < 0){
                deletedMoney.setText("You don't have enough money");
            }
            else{

                Globals.db.withdrawMoney(id,amount);
                deletedMoney.setText("You withdrawn"+" € "+amount);
                fillDropdownClients();
                fillDropdownAccounts();
                fillDropdownCards();
                withdrawText.setText("");
            }
        }
        else
        {
            System.out.println("bad");
            deletedMoney.setText("Wrong type format");
        }

    }

    public int getMonth(){

        LocalDateTime ldt = LocalDateTime.now().plusYears(4);
        DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        String date = ldt.toString();
        String mm = date.substring(5,7);

        return Integer.parseInt(mm);
    }

    public int getYear(){

        LocalDateTime ldt = LocalDateTime.now().plusYears(4);
        DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        String date = ldt.toString();
        String yy = date.substring(0,2);

        return Integer.parseInt(yy);

    }
}