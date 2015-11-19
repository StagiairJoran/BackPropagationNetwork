package javaFXView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.NeuraalNetwerk;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Joachim De Schryver & Joran De Boever
 * on 18/11/15.
 */
public class ViewController implements Initializable {
    private NeuraalNetwerk neuraalNetwerk = new NeuraalNetwerk();

    @FXML
    private Button btnInitialise;
    @FXML
    private Button btnStart;

    @FXML
    private Label lblInput1;
    @FXML
    private Label lblInput2;
    @FXML
    private Label lblInput3;

    @FXML
    private Label ihWeight11;
    @FXML
    private Label ihWeight12;
    @FXML
    private Label ihWeight13;
    @FXML
    private Label ihWeight14;

    @FXML
    private Label ihWeight21;
    @FXML
    private Label ihWeight22;
    @FXML
    private Label ihWeight23;
    @FXML
    private Label ihWeight24;

    @FXML
    private Label ihWeight31;
    @FXML
    private Label ihWeight32;
    @FXML
    private Label ihWeight33;
    @FXML
    private Label ihWeight34;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnInitialise.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vulWaardenin();
            }
        });

    }

    private void vulWaardenin(){
        lblInput1.setText(String.format("%.1f",neuraalNetwerk.getInputWaarden()[0]));
        lblInput2.setText(String.format("%.1f",neuraalNetwerk.getInputWaarden()[1]));
        lblInput3.setText(String.format("%.1f",neuraalNetwerk.getInputWaarden()[2]));

        ihWeight11.setText(String.format("%.3f", neuraalNetwerk.getEersteAxonen()[0][0]));
        ihWeight12.setText(String.format("%.3f", neuraalNetwerk.getEersteAxonen()[0][1]));
        ihWeight13.setText(String.format("%.3f", neuraalNetwerk.getEersteAxonen()[0][2]));
        ihWeight14.setText(String.format("%.3f", neuraalNetwerk.getEersteAxonen()[0][3]));

        ihWeight21.setText(String.format("%.3f", neuraalNetwerk.getEersteAxonen()[1][0]));
        ihWeight22.setText(String.format("%.3f", neuraalNetwerk.getEersteAxonen()[1][1]));
        ihWeight23.setText(String.format("%.3f", neuraalNetwerk.getEersteAxonen()[1][2]));
        ihWeight24.setText(String.format("%.3f", neuraalNetwerk.getEersteAxonen()[1][3]));

        ihWeight31.setText(String.format("%.3f", neuraalNetwerk.getEersteAxonen()[2][0]));
        ihWeight32.setText(String.format("%.3f", neuraalNetwerk.getEersteAxonen()[2][1]));
        ihWeight33.setText(String.format("%.3f", neuraalNetwerk.getEersteAxonen()[2][2]));
        ihWeight34.setText(String.format("%.3f", neuraalNetwerk.getEersteAxonen()[2][3]));


    }
}
