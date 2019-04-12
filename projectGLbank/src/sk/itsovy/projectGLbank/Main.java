package sk.itsovy.projectGLbank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

        launch(args);
    }
}
