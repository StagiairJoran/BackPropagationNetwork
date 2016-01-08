package javaFXView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by JoachimDs on 15/12/2015.
 */
public class startController implements Initializable {

    @FXML
    private Button simple, letter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        simple.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Stage stage = (Stage) simple.getScene().getWindow();
                Stage stage = new Stage();
                Parent root= null;
                try {
                    root = FXMLLoader.load(getClass().getResource("view.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(root);
                stage.setTitle("Simple Backpropagation");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();


            }
        });

        letter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                Parent root= null;
                try {
                    root = FXMLLoader.load(getClass().getResource("letterView.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);

                stage.setTitle("Letter Backpropagation");
                stage.show();
            }
        });
    }

}
