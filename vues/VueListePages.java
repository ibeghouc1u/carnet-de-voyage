package carnetDeVoyage.vues;

import carnetDeVoyage.pages.CarnetDeVoyage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class VueListePages implements Observateur, Initializable {

    @FXML
    private ListView liste;
    private CarnetDeVoyage carnet;

    public VueListePages(CarnetDeVoyage carnet) {
        this.carnet = carnet;
        this.carnet.ajouterObservateur(this);
    }

    @Override
    public void reagir() {
        liste.getItems().clear();
        liste.getItems().addAll(this.carnet.getTitreDesPage());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        liste.getItems().addAll(this.carnet.getTitreDesPage());
        //liste.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->System.out.println(newValue)) ;

    }

    public void  selectionerUnePage()
    {
        String str =(String) liste.getSelectionModel().getSelectedItem();
        if (str!=null) {
            str = str.substring(0, str.indexOf(":"));
            this.carnet.selectionerUnePage(Integer.parseInt(str));
        }
    }


}
