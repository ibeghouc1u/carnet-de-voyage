package carnetDeVoyage.vues;

import carnetDeVoyage.pages.CarnetDeVoyage;
import carnetDeVoyage.pages.Page;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tooltip;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ListeImage implements Observateur, Initializable {
    @FXML
    private ListView listeImage;
    @FXML
    private Tooltip tooltip1;
    @FXML
    private Tooltip tooltip2;
    @FXML
    private Tooltip tooltip3;

    private CarnetDeVoyage carnet;
    private Page page;

    public ListeImage(CarnetDeVoyage carnet, Page page) {
        this.carnet = carnet;
        this.page = page;
        this.carnet.ajouterObservateur(this);
    }


    @Override
    public void reagir(){
        //this.listeImage.getItems().clear();
        //for (String str : page.getPathImageEnPlus())
        //    this.listeImage.getItems().add(str);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listeImage.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listeImage.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView param) {
                return new CelluleLIsteImage(carnet,page);
            }
        });
        for(String pathImage : page.getPathImageEnPlus())
            listeImage.getItems().add(pathImage);

        this.tooltip1.setShowDelay(Duration.millis(50));
        this.tooltip2.setShowDelay(Duration.millis(50));
        this.tooltip3.setShowDelay(Duration.millis(50));

    }

    public void retournerALaPage()
    {
        this.page.setEstSurLaListeDesImage(false);
        this.carnet.notifierObservateur();
    }

    public void ajouterImageALaListe()
    {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save carnet");
        fileChooser.setInitialFileName("carnetDeVouyage");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("png","*.png"),new FileChooser.ExtensionFilter("bmp","*.bmp"));
        File file= fileChooser.showOpenDialog(this.listeImage.getScene().getWindow());
        if(file != null)
        {
            page.addImageEnPlus(file.getAbsolutePath());
            fileChooser.setInitialDirectory(file.getParentFile());
        }
        this.listeImage.getItems().clear();
        for (String str : page.getPathImageEnPlus())
            this.listeImage.getItems().add(str);
    }
    @FXML
    public void handleDragOver(DragEvent event)
    {
        if(event.getDragboard().hasFiles())
            event.acceptTransferModes(TransferMode.ANY);
    }
    @FXML
    public void HandleDrop(DragEvent event)
    {
        for(File file1 : event.getDragboard().getFiles())
            page.addImageEnPlus(file1.getAbsolutePath());
        this.listeImage.getItems().clear();
        for (String str : page.getPathImageEnPlus())
            this.listeImage.getItems().add(str);
    }

    public void supprmierLaSelection()
    {

        ListeImage liste = this;
         Runnable command = new Runnable() {
                @Override
                public void run() {
                    ObservableList<Integer>  index = listeImage.getSelectionModel().getSelectedIndices();
                    CarnetDeVoyage carnet = liste.carnet;
                    Page page = liste.page;
                    Task<Void> task = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            for (int i=0 ;i<index.size();i++)
                                carnet.supprimerLesImageSelectionnerLaPage(page,index.get(index.size()-1)-i);
                            Runnable command = new Runnable() {
                                @Override
                                public void run() {
                                    liste.listeImage.getItems().clear();
                                    for (String str : page.getPathImageEnPlus())
                                        liste.listeImage.getItems().add(str);
                                }
                            };
                            if(Platform.isFxApplicationThread())
                                command.run();
                            else
                                Platform.runLater(command);
                            return null;

                        }
                    };
                    Thread thread = new Thread(task);
                    thread.start();
                };

            };
            if(Platform.isFxApplicationThread())
                command.run();
            else
                Platform.runLater(command);
    }




}
