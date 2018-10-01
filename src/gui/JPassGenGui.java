package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JPassGenGui extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("jpassgenpanel.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("JPassGen - Java Password Generator");
        stage.setWidth(400);
        stage.setScene(scene);
        stage.show();
    }

}
