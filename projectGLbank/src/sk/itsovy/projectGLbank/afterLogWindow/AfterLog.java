package sk.itsovy.projectGLbank.afterLogWindow;

import javafx.scene.control.Label;
import sk.itsovy.projectGLbank.Employee;

public class AfterLog {

    public Label afterLogName;
    public Label afterLogSurname;
    public Label afterLogPosition;

    public void setupAfterlog(Employee person, String position) {

        String name = person.getFirstname();
        String surname = person.getSurname();

        afterLogName.setText(name);
        afterLogSurname.setText(surname);
        afterLogPosition.setText(position);

        //afterLogName.setText("test");

    }

}
