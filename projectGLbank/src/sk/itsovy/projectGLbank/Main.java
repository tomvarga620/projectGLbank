package sk.itsovy.projectGLbank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("loginWindow/sample.fxml"));
        primaryStage.setTitle("Internet Bank");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {

        /*Database db = Database.getInstance();
        db.getConnection();*/
        /*long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        System.out.println(number);*/

        launch(args);

    }
}
