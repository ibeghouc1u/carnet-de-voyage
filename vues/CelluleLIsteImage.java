package carnetDeVoyage.vues;

import carnetDeVoyage.pages.CarnetDeVoyage;
import carnetDeVoyage.pages.Page;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;


public class CelluleLIsteImage extends ListCell <String>{
    @FXML
    private ImageView image ;
    private Parent graphic ;
    private CarnetDeVoyage carnet;
    private Page page;
    public CelluleLIsteImage(CarnetDeVoyage carnet, Page page) {
        this.carnet = carnet;
        this.page = page;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("celluleLIsteImage.fxml"));
        loader.setControllerFactory(ic-> this);
        try {
          graphic = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null){
            //label1.setText("Label 1");
            try {





                Random random = new Random();
                //int i = random.nextInt(2);
                //if (i == 0)
                    this.image.setImage(new Image(new FileInputStream(item)));
                //else
                //    this.image.setImage(new Image(new FileInputStream("hello")));"/home/zinedine/carnet/1.png"*/


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            setGraphic(graphic);
        } else {
            setGraphic(null);
        }
    }

}
