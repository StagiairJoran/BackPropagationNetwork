package javaFXView;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import model.NeuraalNetwerk;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Joachim De Schryver & Joran De Boever
 * on 18/11/15.
 */
public class LetterViewController implements Initializable, Observer {

    private boolean initialized = false;

    private NeuraalNetwerk neuraalNetwerk;

    public LetterViewController() {
        neuraalNetwerk = new NeuraalNetwerk();
        neuraalNetwerk.addObserver(this);
    }

    @FXML
    private Button btnInitialise;

    @FXML
    private Button btnStart;


    @FXML
    private Label lblInput0, lblInput1, lblInput2, lblInput3, lblInput4, lblInput5, lblInput6, lblInput7, lblInput8, lblInput9;
    @FXML
    private Label lblInput10, lblInput11, lblInput12, lblInput13, lblInput14, lblInput15, lblInput16, lblInput17, lblInput18, lblInput19;
    @FXML
    private Label lblInput20, lblInput21, lblInput22, lblInput23, lblInput24, lblInput25, lblInput26, lblInput27, lblInput28, lblInput29;
    @FXML
    private Label lblInput30, lblInput31, lblInput32, lblInput33, lblInput34, lblInput35, lblInput36, lblInput37, lblInput38, lblInput39;
    @FXML
    private Label lblInput40, lblInput41, lblInput42, lblInput43, lblInput44, lblInput45, lblInput46, lblInput47, lblInput48, lblInput49;
    @FXML
    private Label lblInput50, lblInput51, lblInput52, lblInput53, lblInput54, lblInput55, lblInput56, lblInput57, lblInput58, lblInput59;
    @FXML
    private Label lblInput60, lblInput61, lblInput62;

   /* @FXML
    private Label ihWeight11, ihWeight12, ihWeight13, ihWeight14;

    @FXML
    private Label ihWeight21, ihWeight22, ihWeight23, ihWeight24;

    @FXML
    private Label ihWeight31, ihWeight32, ihWeight33, ihWeight34;
*/
    @FXML
    private Label lblHidden1, lblHidden2, lblHidden3, lblHidden4;

    @FXML
    private Label hoWeight11, hoWeight12, hoWeight21, hoWeight22, hoWeight31, hoWeight32, hoWeight41, hoWeight42;

    @FXML
    private Label lblOutput1, lblOutput2;

    @FXML
    private Label lblError;     // errorValueOutput1, errorValueOutput2, errorHidden1, errorHidden2;

    @FXML
    private Label target1, target2;

    @FXML
    private ProgressBar prgTeller, prgError, prgTarget;

    @FXML
    private TextField txtLearningRate, txtErrorThreshold;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnInitialise.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                neuraalNetwerk.initializeLetterNetwerk();
                vulWaardenin();
                initialized = true;
                btnStart.setDisable(false);
                btnStart.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        Task task = new Task<Void>() {
                            @Override
                            public Void call() throws Exception {
                                // updateProgress(1, 100);
                                neuraalNetwerk.startBackPropagation();
                                return null;
                            }

                        };
                        Thread th = new Thread(task);
                        th.setDaemon(true);
                        th.start();
                    }
                });
            }
        });



    }

    private void vulWaardenin() {
     /*   lblInput1.setText(String.format("%.1f", neuraalNetwerk.getInputWaarden()[0]));
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
        ihWeight34.setText(String.format("%.3f", neuraalNetwerk.getEersteAxonen()[2][3]));*/

        lblInput0.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[0]));
        lblInput1.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[1]));
        lblInput2.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[2]));
        lblInput3.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[3]));
        lblInput4.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[4]));
        lblInput5.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[5]));
        lblInput6.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[6]));
        lblInput7.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[7]));
        lblInput8.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[8]));
        lblInput9.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[9]));
        lblInput10.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[10]));
        lblInput11.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[11]));
        lblInput12.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[12]));
        lblInput13.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[13]));
        lblInput14.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[14]));
        lblInput15.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[15]));
        lblInput16.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[16]));
        lblInput17.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[17]));
        lblInput18.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[18]));
        lblInput19.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[19]));
        lblInput20.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[20]));
        lblInput21.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[21]));
        lblInput22.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[22]));
        lblInput23.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[23]));
        lblInput24.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[24]));
        lblInput25.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[25]));
        lblInput26.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[26]));
        lblInput27.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[27]));
        lblInput28.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[28]));
        lblInput29.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[29]));
        lblInput30.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[30]));
        lblInput31.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[31]));
        lblInput32.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[32]));
        lblInput33.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[33]));
        lblInput34.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[34]));
        lblInput35.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[35]));
        lblInput36.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[36]));
        lblInput37.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[37]));
        lblInput38.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[38]));
        lblInput39.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[39]));
        lblInput40.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[40]));
        lblInput41.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[41]));
        lblInput42.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[42]));
        lblInput43.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[43]));
        lblInput44.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[44]));
        lblInput45.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[45]));
        lblInput46.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[46]));
        lblInput47.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[47]));
        lblInput48.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[48]));
        lblInput49.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[49]));
        lblInput50.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[50]));
        lblInput51.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[51]));
        lblInput52.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[52]));
        lblInput53.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[53]));
        lblInput54.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[54]));
        lblInput55.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[55]));
        lblInput56.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[56]));
        lblInput57.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[57]));
        lblInput58.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[58]));
        lblInput59.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[59]));
        lblInput60.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[60]));
        lblInput61.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[61]));
        lblInput62.setText(String.format("%.0f", neuraalNetwerk.getInputWaarden()[62]));

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

        lblOutput1.setText(String.format("%.3f", neuraalNetwerk.getOutputWaarden()[0]));
        lblOutput2.setText(String.format("%.3f", neuraalNetwerk.getOutputWaarden()[1]));

       /* if (neuraalNetwerk.getOutputErrors() != null) {
            errorValueOutput1.setText(String.format("%.3f", neuraalNetwerk.getOutputErrors()[0]));
            errorValueOutput2.setText(String.format("%.3f", neuraalNetwerk.getOutputErrors()[1]));
        }

        if (neuraalNetwerk.getOutputErrors() != null) {
            errorHidden1.setText(String.format("%.3f", neuraalNetwerk.getHiddenErrors()[0]));
            errorHidden2.setText(String.format("%.3f", neuraalNetwerk.getHiddenErrors()[1]));
        }*/

        target1.setText(String.format("%.5f", neuraalNetwerk.getTargets()[0]));
        target2.setText(String.format("%.5f", neuraalNetwerk.getTargets()[1]));

        txtErrorThreshold.setText(String.format("%.7f", neuraalNetwerk.getErrorThreshold()));

        lblError.setText(String.format("%.7f", neuraalNetwerk.getError()));
        prgTeller.setProgress(neuraalNetwerk.getProgressTeller());
        prgError.setProgress(neuraalNetwerk.getProgressError());
        prgTarget.setProgress(neuraalNetwerk.getProgressTargets());
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vulWaardenin();
            }
        });
    }


}
