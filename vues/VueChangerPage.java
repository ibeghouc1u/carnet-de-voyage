package carnetDeVoyage.vues;

import carnetDeVoyage.exception.CarnetDeVoyageException;
import carnetDeVoyage.pages.CarnetDeVoyage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class VueChangerPage implements Observateur, Initializable {

    @FXML
    private TextField pageActuel;
    @FXML
    private Button supprimerPageGauche;
    @FXML
    private Button supprimerPageDroite;
    @FXML
    private Tooltip tooltip1;
    @FXML
    private Tooltip tooltip2;
    @FXML
    private Tooltip tooltip3;
    @FXML
    private Tooltip tooltip4;

    private CarnetDeVoyage canet;

    public VueChangerPage(CarnetDeVoyage canet) {
        this.canet = canet;
        this.canet.ajouterObservateur(this);
    }

    @Override
    public void reagir() {
        pageActuel.setText(""+this.canet.getNumPageActuel());

        if (canet.nbPagesDuCanet() == 2 || canet.nbPagesDuCanet() == 0) {
            this.supprimerPageGauche.setDisable(true);
            this.supprimerPageDroite.setDisable(true); }
        else if((canet.getNumPageActuel() ==1 || canet.getNumPageActuel()==2) && canet.nbPagesDuCanet() >2) {
            this.supprimerPageDroite.setDisable(false);
            this.supprimerPageGauche.setDisable(true); }
        else {
            this.supprimerPageDroite.setDisable(false);
            this.supprimerPageGauche.setDisable(false);
        }
    }

    public void passerALaPageSuivante( ){
        try {
            this.canet.changerDePage(+1);
            this.canet.notifierObservateur();
        } catch (CarnetDeVoyageException e) {
            System.out.println(e);
        }
    }
    public void retourALaPagePrecedante()
    {

        try {
            this.canet.changerDePage(-1);
            this.canet.notifierObservateur();
        } catch (CarnetDeVoyageException e) {
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        tooltip1.setShowDelay(Duration.millis(50));
        tooltip2.setShowDelay(Duration.millis(50));
        tooltip3.setShowDelay(Duration.millis(50));
        tooltip4.setShowDelay(Duration.millis(50));

    }


    public void supprimerPageGauche()
    {
        this.canet.supprimerAGauche();
    }

    public void supprimerPageDroite()
    {
        this.canet.supprimerADroite();
    }
}
