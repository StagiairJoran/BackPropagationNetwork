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
    private Label lblInput1, lblInput2, lblInput3;

    @FXML
    private Label ihWeight11, ihWeight12, ihWeight13, ihWeight14;

    @FXML
    private Label ihWeight21, ihWeight22, ihWeight23, ihWeight24;

    @FXML
    private Label ihWeight31, ihWeight32, ihWeight33, ihWeight34;

    @FXML
    private Label lblHidden1, lblHidden2, lblHidden3, lblHidden4;

    @FXML
    private Label hoWeight11, hoWeight12, hoWeight21, hoWeight22, hoWeight31, hoWeight32, hoWeight41, hoWeight42;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnInitialise.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vulWaardenin();
            }
        });

    }

    private void vulWaardenin() {
        lblInput1.setText(String.format("%.1f", neuraalNetwerk.getInputWaarden()[0]));
        lblInput2.setText(String.format("%.1f", neuraalNetwerk.getInputWaarden()[1]));
        lblInput3.setText(String.format("%.1f", neuraalNetwerk.getInputWaarden()[2]));

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

        lblHidden1.setText(String.format("%.3f", neuraalNetwerk.getHiddenWaarden()[0]));
        lblHidden2.setText(String.format("%.3f", neuraalNetwerk.getHiddenWaarden()[1]));
        lblHidden3.setText(String.format("%.3f", neuraalNetwerk.getHiddenWaarden()[2]));
        lblHidden4.setText(String.format("%.3f", neuraalNetwerk.getHiddenWaarden()[3]));

        hoWeight11.setText(String.format("%.3f", neuraalNetwerk.getTweedeAxonen()[0][0]));
        hoWeight12.setText(String.format("%.3f", neuraalNetwerk.getTweedeAxonen()[0][1]));
        hoWeight21.setText(String.format("%.3f", neuraalNetwerk.getTweedeAxonen()[1][0]));
        hoWeight22.setText(String.format("%.3f", neuraalNetwerk.getTweedeAxonen()[1][1]));
        hoWeight31.setText(String.format("%.3f", neuraalNetwerk.getTweedeAxonen()[2][0]));
        hoWeight32.setText(String.format("%.3f", neuraalNetwerk.getTweedeAxonen()[2][1]));
        hoWeight41.setText(String.format("%.3f", neuraalNetwerk.getTweedeAxonen()[3][0]));
        hoWeight42.setText(String.format("%.3f", neuraalNetwerk.getTweedeAxonen()[3][1]));



    }
}
