package sample;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import sample.Database.Database;


public class Controller {

    public TextField username;
    public TextField pass;

    public void login(ActionEvent actionEvent) {
        String name = username.getText();
        String password = pass.getText();
        Database db = Database.getInstance();
        Employee person = db.compareEmployee(name,password);
        System.out.println(person.getFirstname());
    }
}
