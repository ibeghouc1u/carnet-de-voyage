package carnetDeVoyage.vues;

import carnetDeVoyage.pages.CarnetDeVoyage;
import carnetDeVoyage.outils.GestionDesFichierJson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VueOutils implements Observateur, Initializable {
    @FXML
    private VBox barOutils;
    @FXML
    private Tooltip tooltip1;
    @FXML
    private Tooltip tooltip2;
    @FXML
    private Tooltip tooltip3;
    @FXML
    private Button save;


    private CarnetDeVoyage canet;

    public VueOutils(CarnetDeVoyage canet) {
        this.canet = canet;
        this.canet.ajouterObservateur(this);
    }

    @Override
    public void reagir() {
        if(canet.nbPagesDuCanet()>0)
            this.save.setDisable(false);
    }

    public void ajouterUnePage(){
        canet.ajouter();
    }

    public void sauvegarder()  {
        Window stage = barOutils.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save carnet");
        fileChooser.setInitialFileName("carnetDeVouyage.carnet");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("carnet","*.carnet"));
        File file= fileChooser.showSaveDialog(null);
        if(file != null)
        {
            GestionDesFichierJson gestionDesFichierJson = new GestionDesFichierJson();
            gestionDesFichierJson.creerUnFichierJson(canet,file);
            gestionDesFichierJson.lireUnFichierJson(canet,file.getPath());
            fileChooser.setInitialDirectory(file.getParentFile());
        }
    }

     public void open() {
        Window stage = barOutils.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save carnet");
        fileChooser.setInitialFileName("carnetDeVouyage.carnet");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("carnet","*.carnet"));
        File file= fileChooser.showOpenDialog(stage);

        if(file != null)
        {
            canet.regenerUnMondeDejaSauvegarder(file.getPath());
            fileChooser.setInitialDirectory(file.getParentFile());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.tooltip1.setShowDelay(Duration.millis(50));
        this.tooltip2.setShowDelay(Duration.millis(50));
        this.tooltip3.setShowDelay(Duration.millis(50));
        if(canet.nbPagesDuCanet()==0)
            this.save.setDisable(true);
    }
}
