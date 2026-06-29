package carnetDeVoyage.pages;

import carnetDeVoyage.outils.FabriqueNumeroDePage;

import java.util.ArrayList;
import java.util.Date;

public abstract class Page {
    protected String  titre = "no title";
    protected int numeroDePage ;
    public Page(){
        numeroDePage = FabriqueNumeroDePage.getInstance().NumeroDeLaPageSuivante();
    }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public boolean estUnePageDePresentation() { return false; }
    public boolean estUnePageDuJour() { return false;}
    public Date getDebutDuVoyage() { return null; }
    public void setDebutDuVoyage(Date debutDuVoyage) { }
    public void setDebutDuVoyage(int annee , int mois ,int jour) {}
    public Date getFinDuVoyage() { return null; }
    public void setFinDuVoyage(Date finDuVoyage) { }
    public void setFinDuVoyage(int annee , int mois ,int jour) {}
    public String getDateDuJour() { return  "";}
    public void setDateDuJour(String dateDuJour) { }
    public String getAuteur() { return ""; }
    public void setAuteur(String auteur) { }
    public int getNumeroDePage() { return numeroDePage; }
    public String getParticipant() { return ""; }
    public void setParticipant(String participant) { }
    public String getTextDuJour() { return ""; }
    public void setTextDuJour(String textDuJour) { }
    public String getPathImage() { return "";}
    public void setPathImage(String pathImage) { }
    public String getLocalisation() { return ""; }
    public void setLocalisation(String localisation) { }
    public String toString() {
        return "Page{" +
                "titre='" + titre + '\'' +
                '}';
    }

    public void setNumeroDePage(int numeroDePage) {
        this.numeroDePage = numeroDePage;
    }
    public Double getLatitude() { return 0.0; }
    public void setLatitude(Double latitude) { }
    public Double getLongitude() { return 0.0; }
    public void setLongitude(Double longitude) { }
    public boolean estLocaliser() { return false; }
    public void setEstLocaliser(boolean estLocaliser) { }
    public ArrayList<String> getPathImageEnPlus() { return null; }

    public void setPathImageEnPlus(ArrayList<String> pathImageEnPlus) { }
    public String getPathImageEnPlusIndex(int index) { return  null; }
    public void addImageEnPlus(String pathImageEnPlus) { }
    public boolean estSurLaListeDesImage() { return false; }
    public void setEstSurLaListeDesImage(boolean estSurLaListeDesImage) { }
    public boolean estSurLaMap() { return false; }
    public void setEstSurLaMap(boolean estSurLaMap) { }

}
