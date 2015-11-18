package javaFXView;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblInput1.setText(String.format("%.1f",neuraalNetwerk.getInputWaarden()[0]));
        lblInput2.setText(String.format("%.1f",neuraalNetwerk.getInputWaarden()[1]));
        lblInput3.setText(String.format("%.1f",neuraalNetwerk.getInputWaarden()[2]));


    }
}
