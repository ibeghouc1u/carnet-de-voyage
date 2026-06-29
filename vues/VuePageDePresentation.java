package carnetDeVoyage.vues;

import carnetDeVoyage.outils.FabriqueDate;
import carnetDeVoyage.pages.CarnetDeVoyage;
import carnetDeVoyage.pages.Page;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;


public class VuePageDePresentation extends VuePage implements Observateur, Initializable {


    @FXML private  Label dateDeDebut;
    @FXML private  Label dateDeFin;
    @FXML private TextField titreDuLivre;
    @FXML private TextField  auteurDuLivre;
    @FXML private TextArea participants;

    public VuePageDePresentation(CarnetDeVoyage carnet, Page page) {
        super(carnet,page);
        //this.carnet.ajouterObservateur(this);

    }

    @Override
    public void reagir() { }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM,YYYY");
        dateDeDebut.setText(sdf.format(carnet.getLaPageDePresenation().getDebutDuVoyage()));
        dateDeFin.setText(sdf.format(carnet.getLaPageDePresenation().getFinDuVoyage()));
        titreDuLivre.setText(page.getTitre());
        auteurDuLivre.setText(page.getAuteur());
        participants.setText(page.getParticipant());

    }

    public void miseAJouerPaticipants() { page.setParticipant(participants.getText());}

    public void miseAJouerTitreDuCarnet() {
        this.page.setTitre(titreDuLivre.getText());
    }


    public void miseAJouerAuteur() { page.setAuteur(auteurDuLivre.getText()); }

    public void changerLaDateDeDebut(){
        //Stage stage = new Stage();
        //BorderPane root = new BorderPane();
        //root.setCenter(new DatePicker());

        DatePicker date= new DatePicker();
        date.show();
        //stage.setTitle("Changer une Date");
        //stage.setScene(new Scene(root,300,180));
        //stage.show();
    }

}
