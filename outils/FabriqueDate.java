package carnetDeVoyage.outils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class FabriqueDate{
    private Calendar calendar = Calendar.getInstance(); // mois de janvier commence par 0
    private static FabriqueDate instance = new FabriqueDate();
    private FabriqueDate() { }

    public static FabriqueDate getInstance() {
        return instance;
    }

    public Calendar dateSuivante() {
        calendar.add(Calendar.DATE, 1);
        return calendar;
    }

    public Date getDate() { return calendar.getTime(); }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, YYYY");
        return sdf.format(this.getDate());
    }

    public void reset()
    {
        this.calendar = Calendar.getInstance();
    }
    public void setDate(int annee, int mois , int jour) {  this.calendar.set(annee,mois,jour);}


}

