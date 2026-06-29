package carnetDeVoyage.vues;

import carnetDeVoyage.pages.CarnetDeVoyage;
import carnetDeVoyage.pages.Page;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public abstract class VuePage implements Observateur {
    protected CarnetDeVoyage carnet;
    protected Page page ;

    public VuePage(CarnetDeVoyage carnet, Page page) {
        this.carnet = carnet;
        this.page = page;
    }

}
