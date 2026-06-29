package carnetDeVoyage.vues;

import carnetDeVoyage.pages.CarnetDeVoyage;
import carnetDeVoyage.pages.Page;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VuePageDuJour extends VuePage implements Observateur, Initializable {

    @FXML
    private Label numDePage;
    @FXML
    private Label dateDeLaPage;
    @FXML
    private TextArea textDuJour;
    @FXML
    private TextField titreDeLaPage;
    @FXML
    private VBox vbox;
    @FXML
    private ImageView image;
    @FXML
    private HBox infoPage;
    @FXML
    private Tooltip tooltip1;
    @FXML
    private Tooltip tooltip2;
    @FXML
    private Tooltip tooltip3;
    @FXML
    private Tooltip tooltip4;





    public VuePageDuJour(CarnetDeVoyage canet, Page page) {
        super(canet,page);
    }
    @Override
    public void reagir(){
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numDePage.setText(""+page.getNumeroDePage());
        dateDeLaPage.setText(""+this.page.getDateDuJour());
        textDuJour.setText(""+this.page.getTextDuJour());
        titreDeLaPage.setText(""+this.page.getTitre());
        if(!page.getPathImage().isEmpty()) {
            try {
                image.setImage(new Image(new FileInputStream(page.getPathImage())));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
             image.setImage(null);
        tooltip1.setShowDelay(Duration.millis(50));
        tooltip2.setShowDelay(Duration.millis(50));
        tooltip3.setShowDelay(Duration.millis(50));
        tooltip4.setShowDelay(Duration.millis(50));
        /*if(page.estSurLaMap())
        {
            this.localisationDeLImage();
        }
        if(page.estSurLaListeDesImage())
        {
            this.listeDesImages();
        }*/
        VuePageDuJour vue = this;
        Runnable command = new Runnable() {
            @Override
	        public void run() {
                    if(vue.page.estSurLaMap())
                    {
                        vue.infoPage.getChildren().clear();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("vueMap.fxml"));
                        loader.setControllerFactory(ic-> new VueMap(carnet,page));
                        try {
                            vue.infoPage.getChildren().add(loader.load());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        if(Platform.isFxApplicationThread())
            command.run();
        else
            Platform.runLater(command);
    }


    public void miseAJourTitreDeLaPage()
    {
        page.setTitre(this.titreDeLaPage.getText());
    }

    public void miseAJourTextDuJour()
    {
        page.setTextDuJour(this.textDuJour.getText());
    }

    public void ajouterUneImage()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save carnet");
        fileChooser.setInitialFileName("carnetDeVouyage");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("png","*.png"),new FileChooser.ExtensionFilter("bmp","*.bmp"));
        File file= fileChooser.showOpenDialog(this.vbox.getScene().getWindow());

        if(file != null)
        {
            carnet.ajouterUneNouvelleImage(this.page,file.getAbsolutePath());
            fileChooser.setInitialDirectory(file.getParentFile());
        }

    }

    public void supprimerUneImage()
    {
        carnet.supprimerUneImage(this.page);
    }

    public void  listeDesImages() {
        System.out.println("bonjour");
        this.infoPage.getChildren().clear();
        this.page.setEstSurLaListeDesImage(true);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("listeImage.fxml"));
        loader.setControllerFactory(ic-> new ListeImage(carnet,page));
        try {
            this.infoPage.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void localisationDeLImage()
    {
        System.out.println("hello");
        this.page.setEstSurLaMap(true);
        this.infoPage.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("vueMap.fxml"));
        loader.setControllerFactory(ic-> new VueMap(carnet,page));
        try {
            this.infoPage.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void dragOver(DragEvent event)
    {
        if(event.getDragboard().hasFiles())
            event.acceptTransferModes(TransferMode.ANY);
    }


    @FXML
    public void drop(DragEvent event)
    {
        List<File> file = event.getDragboard().getFiles();
        carnet.ajouterUneNouvelleImage(this.page,file.get(0).getAbsolutePath());
    }


}




