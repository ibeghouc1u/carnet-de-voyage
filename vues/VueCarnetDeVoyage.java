package carnetDeVoyage.vues;

import carnetDeVoyage.pages.CarnetDeVoyage;
import carnetDeVoyage.pages.Page;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import java.io.IOException;

public class VueCarnetDeVoyage implements Observateur{
        @FXML
        private HBox deuxPage;

        private CarnetDeVoyage canet;
        public VueCarnetDeVoyage(CarnetDeVoyage canet) {
                this.canet = canet;
                this.canet.ajouterObservateur(this);
        }

        @Override
        public void reagir(){
                deuxPage.getChildren().clear();
                Page page = canet.getPagesDuCarnetIndex(this.canet.getNumPageActuel()-1);
                try {
                        if(page.estUnePageDePresentation()) {
                                deuxPage.getChildren().add(this.ajouterVuePage(page).load());
                                if(this.canet.nbPagesDuCanet()>1)
                                        deuxPage.getChildren().add(this.ajouterVuePage(canet.getPagesDuCarnetIndex(this.canet.getNumPageActuel())).load());
                        }
                        else if (page.estUnePageDuJour()){
                                deuxPage.getChildren().add(this.ajouterVuePage(canet.getPagesDuCarnetIndex(this.canet.getNumPageActuel()-2)).load());
                                deuxPage.getChildren().add(this.ajouterVuePage(canet.getPagesDuCarnetIndex(this.canet.getNumPageActuel()-1)).load());
                        }

                } catch (IOException e) {
                        e.printStackTrace();
                }
        }



        public FXMLLoader ajouterVuePage(Page page ) {
                FXMLLoader loader = new FXMLLoader();
                if (page.estUnePageDePresentation()) {
                        loader.setLocation(getClass().getResource("vuePageDePresentation.fxml"));
                        loader.setControllerFactory(ic -> new VuePageDePresentation(canet, page));
                } else if (page.estUnePageDuJour()) {
                        loader.setLocation(getClass().getResource("vuePageDuJour.fxml"));
                        loader.setControllerFactory(ic -> new VuePageDuJour(canet, page));
                }
                return loader;
        }
}
