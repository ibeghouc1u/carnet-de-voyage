package carnetDeVoyage.vues;


import carnetDeVoyage.outils.FabriqueDate;
import carnetDeVoyage.pages.CarnetDeVoyage;
import carnetDeVoyage.pages.Page;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ChoisirDate  implements Observateur, Initializable {
    @FXML
    private DatePicker date;
    private CarnetDeVoyage carnet;
    private TextInputDialog dialog = new TextInputDialog();
    public ChoisirDate(CarnetDeVoyage carnet){
        this.carnet = carnet;
    }

    @Override
    public void reagir() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void confirmer()
    {
        LocalDate localDate = this.date.getValue();
        if (localDate != null){
            FabriqueDate.getInstance().setDate(localDate.getYear(),localDate.getMonthValue()-1,localDate.getDayOfMonth());
            this.carnet.changerDateDesPages();
            this.dialog.close();
        }else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Il faut choisir une date correcte");
                alert.showAndWait();
            }

    }
    public void annuler()
    {
        this.dialog.close();
    }

    public TextInputDialog getDialog() {
        return dialog;
    }
}
