package carnetDeVoyage.pages;

import carnetDeVoyage.outils.FabriqueDate;
import carnetDeVoyage.vues.Observateur;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class PageDePresentation extends  Page {
    private String auteur = "no autor";
    private Date debutDuVoyage ;
    private Date finDuVoyage;
    private String participant = "aucun participants";

    public PageDePresentation(){
        debutDuVoyage=FabriqueDate.getInstance().getDate(); // par defauts la date du jour
        finDuVoyage= FabriqueDate.getInstance().getDate();
    }

    @Override
    public String getAuteur() {
        return auteur;
    }

    @Override
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    @Override
    public Date getDebutDuVoyage() {
        return debutDuVoyage;
    }
    @Override
    public void setDebutDuVoyage(Date debutDuVoyage) {
        this.debutDuVoyage = debutDuVoyage;
    }

    @Override
    public void setDebutDuVoyage(int annee , int mois ,int jour) {
        FabriqueDate  fabriqueDate = FabriqueDate.getInstance();
        fabriqueDate.setDate(annee,mois,jour);//initialiser la fabrique de de date
        Calendar calendar = Calendar.getInstance();
        calendar.set(annee,mois,jour);
        this.debutDuVoyage =  calendar.getTime();
    }


    @Override
    public Date getFinDuVoyage() {
        return finDuVoyage;
    }
    @Override
    public void setFinDuVoyage(Date finDuVoyage) {
        this.finDuVoyage = finDuVoyage;
    }

    @Override
    public void setFinDuVoyage(int annee , int mois ,int jour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(annee,mois,jour);
        this.finDuVoyage =  calendar.getTime();
    }

    @Override
    public String getParticipant() {
        return participant;
    }
    @Override
    public void setParticipant(String participant) {
        this.participant = participant;
    }

    @Override
    public boolean estUnePageDePresentation() {
        return true;
    }

    @Override
    public String toString() {
        return "PageDePresentation{" +
                "auteur='" + auteur + '\'' +
                ", debutDuVoyage=" + debutDuVoyage +
                ", finDuVoyage=" + finDuVoyage +
                ", participant='" + participant + '\'' +
                ", titre='" + titre + '\'' +
                '}';
    }

}
