package carnetDeVoyage.pages;

import java.util.ArrayList;

public class PageDuJour extends  Page  {
    private String textDuJour="no text";
    private String dateDuJour="jj/mm/aaa";
    private String pathImage ="";
    private String localisation="";
    private Double latitude = 0.;
    private Double longitude = 0.;
    private boolean estLocaliser = false;
    private ArrayList<String> pathImageEnPlus = new ArrayList<>();
    private boolean estSurLaListeDesImage = false;
    private boolean estSurLaMap = false;


    public PageDuJour() { }
    @Override
    public String getTextDuJour() {
        return textDuJour;
    }
    @Override
    public void setTextDuJour(String textDuJour) {
        this.textDuJour = textDuJour;
    }
    @Override
    public String getDateDuJour() {
        return dateDuJour;
    }
    @Override
    public void setDateDuJour(String dateDuJour) {
        this.dateDuJour = dateDuJour;
    }
    @Override
    public String getPathImage() {
        return pathImage;
    }
    @Override
    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }
    @Override
    public String getLocalisation() {
        return localisation;
    }
    @Override
    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    @Override
    public boolean estUnePageDuJour() {
        return true;
    }

    @Override
    public String toString() {
        return "PageDuJour{" +
                "textDuJour='" + textDuJour + '\'' +
                ", dateDuJour='" + dateDuJour + '\'' +
                ", PathImage='" + pathImage + '\'' +
                ", localisation='" + localisation + '\'' +
                ", titre='" + titre + '\'' +
                '}';
    }

    @Override
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    @Override
    public boolean estLocaliser() {
        return estLocaliser;
    }
    @Override
    public void setEstLocaliser(boolean estLocaliser) {
        this.estLocaliser = estLocaliser;
    }
    @Override
    public ArrayList<String> getPathImageEnPlus() {
        return pathImageEnPlus;
    }
    @Override
    public void setPathImageEnPlus(ArrayList<String> pathImageEnPlus) {
        this.pathImageEnPlus = pathImageEnPlus;
    }
    @Override
    public String getPathImageEnPlusIndex(int index) {
        return pathImageEnPlus.get(index);
    }
    @Override
    public void addImageEnPlus(String pathImageEnPlus) {
        this.pathImageEnPlus.add(pathImageEnPlus);
    }

    @Override
    public boolean estSurLaListeDesImage() {
        return estSurLaListeDesImage;
    }
    @Override
    public void setEstSurLaListeDesImage(boolean estSurLaListeDesImage) {
        this.estSurLaListeDesImage = estSurLaListeDesImage;
    }
    @Override
    public boolean estSurLaMap() {
        return estSurLaMap;
    }
    @Override
    public void setEstSurLaMap(boolean estSurLaMap) {
        this.estSurLaMap = estSurLaMap;
    }
}
