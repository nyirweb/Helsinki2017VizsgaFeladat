package helsinki2017.model;

public class TobbVersenyzosOrszagokModel 
{
    String orszagKod;
    String versenyzokSzama;

    public TobbVersenyzosOrszagokModel() {
    }

    public String getOrszagKod() {
        return orszagKod;
    }

    public void setOrszagKod(String orszagKod) {
        this.orszagKod = orszagKod;
    }

    public String getVersenyzokSzama() {
        return versenyzokSzama;
    }

    public void setVersenyzokSzama(String versenyzokSzama) {
        this.versenyzokSzama = versenyzokSzama;
    }

    public void setVersenyzokSzama(int max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
