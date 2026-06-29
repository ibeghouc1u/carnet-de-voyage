package carnetDeVoyage;

import carnetDeVoyage.pages.CarnetDeVoyage;
import carnetDeVoyage.vues.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        CarnetDeVoyage canetDeVoyage = new CarnetDeVoyage();
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #CECECE;");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("vues/vueCarnetDeVoyage.fxml"));
        loader.setControllerFactory(ic -> new VueCarnetDeVoyage(canetDeVoyage));
        root.setCenter(loader.load());
        BorderPane.setAlignment(root.getCenter(), Pos.CENTER);

        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("vues/vueChangerPage.fxml"));
        loader.setControllerFactory(ic-> new VueChangerPage(canetDeVoyage));
        root.setBottom(loader.load());
        BorderPane.setAlignment(root.getBottom(), Pos.TOP_CENTER);


        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("vues/vueOutils.fxml"));
        loader.setControllerFactory(ic-> new VueOutils(canetDeVoyage));
        root.setLeft(loader.load());
        BorderPane.setAlignment(root.getLeft(),Pos.TOP_CENTER);


        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("vues/vueMenu.fxml"));
        loader.setControllerFactory(ic-> new VueMenu(canetDeVoyage));
        root.setTop(loader.load());

        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("vues/vueListePages.fxml"));
        loader.setControllerFactory(ic-> new VueListePages(canetDeVoyage));
        root.setRight(loader.load());
        BorderPane.setAlignment(root.getRight(),Pos.CENTER_RIGHT);
        primaryStage.setTitle("Carnet De Voyage");
        Scene scene = new Scene(root, 1420,850);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
