package carnetDeVoyage.vues;

import carnetDeVoyage.pages.CarnetDeVoyage;
import carnetDeVoyage.pages.Page;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapLabel;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Marker;
import com.sothawo.mapjfx.event.MapViewEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VueMap implements Observateur, Initializable {

    @FXML
    private VBox pane;
    @FXML
    private Tooltip tooltip;

    private MapView mapView = new MapView();
    private Marker mark;
    private CarnetDeVoyage carnet;
    private ArrayList<Marker> markers = new ArrayList<>();
    private Page page;
    public VueMap(CarnetDeVoyage carnet, Page page) {
        this.carnet = carnet;
        this.page = page;
        this.mark = Marker.createProvided(Marker.Provided.RED);
        MapLabel mapLabel = new MapLabel(page.getDateDuJour());
        this.mark.attachLabel(mapLabel);
        mark.setPosition(new Coordinate(46.0,2.0)).setVisible(false);
        for (Page p : carnet){
            if(p.estLocaliser() && p.getNumeroDePage() != this.page.getNumeroDePage())
            {
                Marker m =  Marker.createProvided(Marker.Provided.GREEN);
                MapLabel label = new MapLabel(p.getDateDuJour());
                m.setPosition(new Coordinate(p.getLatitude(),p.getLongitude()));
                m.setVisible(true);
                m.attachLabel(label);
                this.markers.add(m);
            }
        }
        mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (! page.estLocaliser())
                    mapView.setCenter(new Coordinate(46.0,2.0));
                mapView.addMarker(mark);
                for (Marker m : this.markers)
                {
                    mapView.addMarker(m);
                }
            }
        });
        mapView.addEventHandler(MapViewEvent.MAP_CLICKED, event -> {
            event.consume();
            page.setEstLocaliser(true);
            mark.setVisible(true);
            final Coordinate newPosition = event.getCoordinate();
            page.setLatitude(newPosition.getLatitude());
            page.setLongitude(newPosition.getLongitude());
            mark.setPosition(newPosition);
        });
    }

    @Override
    public void reagir() {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (page.estLocaliser())
        {
                mark.setPosition(new Coordinate(page.getLatitude(),page.getLongitude()));
                mark.setVisible(true);
                mapView.setCenter(new Coordinate(page.getLatitude(),page.getLongitude()));
        }
        mapView.initialize();
        mapView.setZoom(4);
        mapView.setMaxSize(450,450);
        mapView.setMinSize(450,450);
        pane.getChildren().add(mapView);
        tooltip.setShowDelay(Duration.millis(50));
    }

    public void quitterlaMap()
    {
        this.page.setEstSurLaMap(false);
        this.carnet.notifierObservateur();
    }
}
