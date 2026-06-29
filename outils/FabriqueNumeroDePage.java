package carnetDeVoyage.outils;

public class FabriqueNumeroDePage{
    private int numeroDePage = 0;
    private static FabriqueNumeroDePage instance = new FabriqueNumeroDePage();

    private FabriqueNumeroDePage() { }

    public static FabriqueNumeroDePage getInstance() { return instance; }

    public int NumeroDeLaPageSuivante() {
        numeroDePage++;
        return numeroDePage;
    }

    public int getNumeroDePage() {
        return numeroDePage;
    }

    public void setNumeroDePage(int numeroDePage) {
        this.numeroDePage = numeroDePage;
    }
    public void reset(){ this.numeroDePage = 0 ;}


}
