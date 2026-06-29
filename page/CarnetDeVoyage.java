package carnetDeVoyage.pages;

import carnetDeVoyage.exception.CarnetDeVoyageException;
import carnetDeVoyage.outils.FabriqueDate;
import carnetDeVoyage.outils.FabriqueNumeroDePage;
import carnetDeVoyage.outils.GestionDesFichierJson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class CarnetDeVoyage extends SujetObserve implements Iterable<Page>{
    private ArrayList <Page> pagesDuCarnet = new ArrayList<>();
    private int numPageActuel = 1;
    private LinkedList<String> TitreDesPage = new LinkedList();//
    public CarnetDeVoyage() {
    }

    public int nbPagesDuCanet(){
        return pagesDuCarnet.size();
    }

    public void ajouter(int nbrpage)
    {
        FabriqueDate dateDuJour = FabriqueDate.getInstance();
        Page page;

        for(int i= 0; i<nbrpage; i++) {
            page = new PageDuJour();
            dateDuJour.dateSuivante();
            page.setDateDuJour(dateDuJour.toString());
            this.pagesDuCarnet.add(page);
            this.getLaPageDePresenation().setFinDuVoyage(dateDuJour.getDate());
        }
    }

    public void ajouter(){

        FabriqueDate dateDuJour = FabriqueDate.getInstance();
        Page page;
        if(this.nbPagesDuCanet() ==0 ){
            page = new PageDePresentation();
            this.pagesDuCarnet.add(page);
            page = new PageDuJour();
            page.setDateDuJour(dateDuJour.toString());
            this.pagesDuCarnet.add(page);
            this.getLaPageDePresenation().setFinDuVoyage(dateDuJour.getDate());// modifier la date de fin de voyage pour chaque ajout d'une pag
            dateDuJour.dateSuivante();//incrementer la frabriqueDate
        }
        else{
            page = new PageDuJour();
            page.setDateDuJour(dateDuJour.toString());
            this.pagesDuCarnet.add(page);
            this.getLaPageDePresenation().setFinDuVoyage(dateDuJour.getDate());// modifier la date de fin de voyage pour chaque ajout d'une pag
            dateDuJour.dateSuivante();//incrementer la frabriqueDate
        }
        numPageActuel= page.getNumeroDePage();
        this.notifierObservateur();
    }
    public void ajouter(Page page)
    {
        this.pagesDuCarnet.add(page);
    }





    public int nbrJourDuVoyage()
    {
        Date debut = this.getLaPageDePresenation().getDebutDuVoyage();
        Date fin = this.getLaPageDePresenation().getFinDuVoyage();
        long diff = fin.getTime() - debut.getTime();
        float res = (diff / (1000*60*60*24));
        return  (int) res+1; // le monbre de jour du vouyage!
    }

    public Page getLaPageDePresenation()
    {
        return this.pagesDuCarnet.get(0);
    }

    public Page getPagesDuCarnetIndex(int index) {
        return pagesDuCarnet.get(index);
    }

    public Iterator<Page> iterator(){
        return this.pagesDuCarnet.iterator();
    }

    public int getNumPageActuel() {
        return numPageActuel;
    }

    public void setNumPageActuel(int numPageActuel) {
        this.numPageActuel = numPageActuel;
    }

    public void changerDePage(int delta) throws CarnetDeVoyageException{
        if(!(numPageActuel+delta > this.nbPagesDuCanet()) && !(numPageActuel+delta<1))
            numPageActuel=numPageActuel+delta;
        else
            throw new CarnetDeVoyageException("depacer l'intervalle de page");
        if(numPageActuel==1)
            numPageActuel =2;
    }

    public void regenerUnMondeDejaSauvegarder(String path)
    {
        GestionDesFichierJson gestionDesFichierJson = new GestionDesFichierJson();
        gestionDesFichierJson.lireUnFichierJson(this,path);
        this.notifierObservateur();
    }

    public void supprimerTouteLesPages()
    {
        FabriqueNumeroDePage.getInstance().reset();
        FabriqueDate.getInstance().reset();
        this.pagesDuCarnet.clear();
    }

    @Override
    public String toString() {
        return "CarnetDeVoyage{" +
                "pagesDuCarnet=" + pagesDuCarnet +
                ", numPageActuel=" + numPageActuel +
                '}';
    }

    public void ajouterUneNouvelleImage(Page page,String nouveauPath)
    {
        page.setPathImage(nouveauPath);
        notifierObservateur();
    }

    public void supprimerUneImage(Page page)
    {
        page.setPathImage("");
        this.notifierObservateur();
    }

    public LinkedList<String> getTitreDesPage() {
        TitreDesPage.clear();
        for (Page page : pagesDuCarnet) {
            TitreDesPage.add(page.getNumeroDePage()+": "+page.getTitre());
        }
        return TitreDesPage;
    }

    public void changerLeTitreDuLivre(String titre)
    {
        this.getLaPageDePresenation().setTitre(titre);
        this.notifierObservateur();
    }

    public void changerLeTitreDeLaPage(Page page, String titre)
    {
        page.setTitre(titre);
        this.notifierObservateur();
    }

    public void selectionerUnePage(int numPage)
    {
        this.numPageActuel = numPage;
        this.notifierObservateur();
    }

    public void nouveauCarnet()
    {
        this.supprimerTouteLesPages();
        this.ajouter();
    }

    public ArrayList<Page> getPagesDuCarnet() {
        return pagesDuCarnet;
    }

    public void setPagesDuCarnet(ArrayList<Page> pagesDuCarnet) {
        this.pagesDuCarnet = pagesDuCarnet;
    }

    public void supprimerAGauche()
    {
        //int i = this.numPageActuel;
        boolean finLivre = false;
        if (this.numPageActuel == this.nbPagesDuCanet())
            finLivre = true;
        FabriqueNumeroDePage.getInstance().reset();
        this.pagesDuCarnet.remove(this.numPageActuel-2);
        if (finLivre)
            this.numPageActuel = this.nbPagesDuCanet();
        //int i = this.numPageActuel;
        for (Page page : this){
            page.setNumeroDePage(FabriqueNumeroDePage.getInstance().NumeroDeLaPageSuivante());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        String[] date = sdf.format(this.getLaPageDePresenation().getDebutDuVoyage()).split("/");
        FabriqueDate.getInstance().setDate(Integer.parseInt(date[2]),Integer.parseInt(date[1])-1,Integer.parseInt(date[0]));
        this.changerDateDesPages(this.getNumPageActuel());
        this.notifierObservateur();
    }

    public void supprimerADroite()
    {
        //int i = this.nbPagesDuCanet(); // n-1
        boolean finLivre = false;
        if (this.numPageActuel == this.nbPagesDuCanet())
            finLivre = true;
        FabriqueNumeroDePage.getInstance().reset();
        //FabriqueDate.getInstance().setDate();
        this.pagesDuCarnet.remove(this.numPageActuel-1);//
        if (finLivre)
            this.numPageActuel = this.nbPagesDuCanet();

        for (Page page : this){
            page.setNumeroDePage(FabriqueNumeroDePage.getInstance().NumeroDeLaPageSuivante());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        String[] date = sdf.format(this.getLaPageDePresenation().getDebutDuVoyage()).split("/");
        FabriqueDate.getInstance().setDate(Integer.parseInt(date[2]),Integer.parseInt(date[1])-1,Integer.parseInt(date[0]));
        this.changerDateDesPages(this.getNumPageActuel());
        this.notifierObservateur();
    }

    public void changerDateDesPages()
    {
        for(Page page : this)
        {

            if (page.estUnePageDePresentation()) {
                Date date=FabriqueDate.getInstance().getDate();
                page.setFinDuVoyage(FabriqueDate.getInstance().getDate());
                page.setDebutDuVoyage(date);
            }
            else
            {
                page.setDateDuJour(FabriqueDate.getInstance().toString());
                page.setFinDuVoyage(FabriqueDate.getInstance().getDate());
                this.getLaPageDePresenation().setFinDuVoyage(FabriqueDate.getInstance().getDate());
                FabriqueDate.getInstance().dateSuivante();
            }
        }
        this.numPageActuel = 2;
        this.notifierObservateur();
    }

    public void changerDateDesPages(int i)
    {
        for(Page page : this)
        {

            if (page.estUnePageDePresentation()) {
                Date date=FabriqueDate.getInstance().getDate();
                page.setFinDuVoyage(FabriqueDate.getInstance().getDate());
                page.setDebutDuVoyage(date);
            }
            else
            {
                page.setDateDuJour(FabriqueDate.getInstance().toString());
                page.setFinDuVoyage(FabriqueDate.getInstance().getDate());
                this.getLaPageDePresenation().setFinDuVoyage(FabriqueDate.getInstance().getDate());
                FabriqueDate.getInstance().dateSuivante();
            }
        }
        this.numPageActuel = i;
        this.notifierObservateur();
    }


    public void changerDateDesPage()
    {
        for(Page page : this)
        {

            /*if (page.estUnePageDePresentation()) {
                Date date=FabriqueDate.getInstance().getDate();
                page.setFinDuVoyage(FabriqueDate.getInstance().getDate());
                page.setDebutDuVoyage(date);
            }
            else
            {
                page.setDateDuJour(FabriqueDate.getInstance().toString());
                page.setFinDuVoyage(FabriqueDate.getInstance().getDate());
                this.getLaPageDePresenation().setFinDuVoyage(FabriqueDate.getInstance().getDate());
                FabriqueDate.getInstance().dateSuivante();
            }*/
        }
        this.notifierObservateur();
    }
    public void supprimerLesImageSelectionnerLaPage(Page page,int i)
    {
        page.getPathImageEnPlus().remove(i);
    }

    public void setPosition(Page page,Double latitude, Double longitude)
    {
        page.setLatitude(latitude);
        page.setLongitude(longitude);
        this.notifierObservateur();
    }


}
