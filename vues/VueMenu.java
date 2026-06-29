package carnetDeVoyage.vues;

import carnetDeVoyage.outils.GestionDesFichierJson;
import carnetDeVoyage.pages.CarnetDeVoyage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class VueMenu implements Observateur, Initializable {
    @FXML
    private MenuItem supprimerADroite;
    @FXML
    private MenuItem supprimerAGauche;
    @FXML
    private MenuItem changerDate;
    @FXML
    private MenuItem save;

    private CarnetDeVoyage carnet;

    public VueMenu(CarnetDeVoyage carnet){
        this.carnet = carnet;
        this.carnet.ajouterObservateur(this);
    }

    @Override
    public void reagir() {
        if (this.carnet.nbPagesDuCanet() == 2) {
            this.supprimerADroite.setDisable(true);
            this.supprimerAGauche.setDisable(true); }
        else if((this.carnet.getNumPageActuel()==1 || this.carnet.getNumPageActuel()==2) && this.carnet.nbPagesDuCanet() >2) {
            this.supprimerADroite.setDisable(false);
            this.supprimerAGauche.setDisable(true); }
        else {
            this.supprimerADroite.setDisable(false);
            this.supprimerAGauche.setDisable(false);
        }
        if(this.carnet.nbPagesDuCanet()>0){
            this.changerDate.setDisable(false);
            this.save.setDisable(false);
        }

    }


    public void changerLaDateDeDebutDeVoyage()
    {
        ChoisirDate choisirDate= new ChoisirDate(this.carnet);
        TextInputDialog dialog = choisirDate.getDialog();
        dialog.getDialogPane().getChildren().clear();
        dialog.setWidth(450);
        dialog.setHeight(200);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("choisirDeta.fxml"));
        loader.setControllerFactory(ic -> choisirDate);
        try {
            Pane pane = new Pane();
            pane.getChildren().add(loader.load());
            dialog.getDialogPane().getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.show();
    }


    public void nouveauCarnet()
    {

        ChoisirDate choisirDate= new ChoisirDate(this.carnet);
        TextInputDialog dialog = choisirDate.getDialog();
        dialog.getDialogPane().getChildren().clear();
        dialog.setWidth(450);
        dialog.setHeight(200);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("choisirDeta.fxml"));
        loader.setControllerFactory(ic -> choisirDate);
        try {
            Pane pane = new Pane();
            pane.getChildren().add(loader.load());
            dialog.getDialogPane().getChildren().add(pane);
        } catch (IOException e){
            e.printStackTrace();
        }
        dialog.show();
        this.carnet.nouveauCarnet();
    }
    public void supprimerAGauche()
    {
        this.carnet.supprimerAGauche();
    }

    public void supprimerADroite()
    {
        this.carnet.supprimerADroite();
    }

    public void ajouterUnePage()
    {
        this.carnet.ajouter();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.changerDate.setDisable(true);
        this.supprimerADroite.setDisable(true);
        this.supprimerAGauche.setDisable(true);
        if (this.carnet.nbPagesDuCanet()==0)
            this.save.setDisable(true);
    }

    public void sauvegarde()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save carnet");
        fileChooser.setInitialFileName("carnetDeVouyage.carnet");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("carnet","*.carnet"));
        File file= fileChooser.showSaveDialog(null);
        if(file != null)
        {
            GestionDesFichierJson gestionDesFichierJson = new GestionDesFichierJson();
            gestionDesFichierJson.creerUnFichierJson(this.carnet,file);
            gestionDesFichierJson.lireUnFichierJson(this.carnet,file.getPath());
            fileChooser.setInitialDirectory(file.getParentFile());
        }
    }


     public void open() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save carnet");
        fileChooser.setInitialFileName("carnetDeVouyage.carnet");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("carnet","*.carnet"));
        File file= fileChooser.showOpenDialog(null);

        if(file != null)
        {
            this.carnet.regenerUnMondeDejaSauvegarder(file.getPath());
            fileChooser.setInitialDirectory(file.getParentFile());
        }
    }
}
