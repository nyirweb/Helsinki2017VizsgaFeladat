package helsinki2017.view;

import helsinki2017.controller.DontosVersenyzokLista;
import helsinki2017.controller.RovidProgramListaz;
import helsinki2017.controller.VegeredmenytLetrehozo;
import helsinki2017.model.DontosVersenyzokModel;
import helsinki2017.model.RovidProgramModel;
import helsinki2017.model.TobbVersenyzosOrszagokModel;
import helsinki2017.model.VegeredmenyModel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;


public class FeladatotKonzolraKiir {

    // 1. feladat, fájlok beolvasása és tárolása a memóriában
    private static ArrayList<RovidProgramModel> rovidProgramLista = 
            RovidProgramListaz.rovidProgramLista("rovidprogram.csv", 5);

    private static ArrayList<DontosVersenyzokModel> dontosok = 
            DontosVersenyzokLista.dontosok("donto.csv", 5);
    
    public static void main(String[] args) 
    {
        masodikFeladat();
        harmadikFeladat();
        System.out.println(otodikFeladat());
        hetedikFeladat(); 
        nyolcadikFeladat();
    }
    
    // 2. feladat: rövid programban indult versenyzők száma
    private static void masodikFeladat()
    {
        // A lista mérete (elemeinek száma) -1 (a fejléc) megadja, 
        // mennyi elem van a listában összesen. Ez a válasz: 
        int rovidProgramVersenyzoi = rovidProgramLista.size()-1;
        System.out.println("2. Feladat: \r\n A rövid programban résztvevő versenyzők száma: "
                +String.valueOf(rovidProgramVersenyzoi));
    }
    
    // 3. Feladat: írjuk ki, hogy a magyar versenyző tovább jutott-e
    private static void harmadikFeladat()
    {
        // Deklarálunk egy String tipusú változót
        String magyarVersenyzo = "";
        
        // Bejárjuk a listánkat, amelyik tartalmazza a már döntős versenyzőket
        for (DontosVersenyzokModel dontosVersenyzokModel : dontosok) {
            
            // A korábban deklarált változónak az értékét, 
            // az országkód értékével tesszük egyenlővé
            magyarVersenyzo = dontosVersenyzokModel.getOrszag();
                       
        }
  
        // Megvizsgáljuk a változót, benne van e a "HUN érték"
        if(magyarVersenyzo.equals("HUN"))
        {
            // Ha az érték bele került a változóba ezt írjuk ki
            System.out.println("3. feladat:\r\n A magyar versenyző bejutott a kűrbe!");
        }else
        {
            // Ha az érték nem került bele a változóba ezt írjuk ki
            System.out.println("3. feladat:\r\n A magyar versenyző bejutott a kűrbe!");
        }
    }
        
    // 4. Feladat a versenyző összpontszámának meghatározása!
    private static String OsszPontszam(String versenyzoNeve)
    {
        // Összeadjuk a rövid program és a döntőben elért pontszámokat!
        float osszPontszam = rovidProgramPontszam(versenyzoNeve) 
                + dontoProgramPontszam(versenyzoNeve);
    
        // Elvárásoknak megfelelő formázás meghatározása! 
        DecimalFormat df = new DecimalFormat("##.00");
        
        // Visszatér a függvény a megfelelő formázással és az összes elért pontszámmal.
        return String.valueOf(df.format(osszPontszam));
    }
    
    // 4. feladat részeként: rövid program pontszámok meghatározása
    private static float rovidProgramPontszam(String versenyzoNeve)
    {
        // Kivesszük a lista első elemét -> mert az fejléc ezért 1-től indul a ciklus
        // Bejárjuk a listát és keressük, a nevet, amit a lokális változóban kapott
        // a függvény. Ha van ilyen név, visszatérünk a technikai és komponens pontok összegével
        for (int i = 1; i < rovidProgramLista.size(); i++) 
        {
            if(rovidProgramLista.get(i).getNev().equals(versenyzoNeve))
            {
                float osszesPontRovidProgram =  Float.valueOf(rovidProgramLista.get(i).getTechnikaiPontszam())
                        + Float.valueOf(rovidProgramLista.get(i).getKomponensPontszam())
                        - Float.valueOf(rovidProgramLista.get(i).getHibapont());
                return osszesPontRovidProgram;
            }
        }
        
        return 0;
    }

    // 4. feladat részeként: döntő program pontszámok meghatározása
    private static float dontoProgramPontszam(String versenyzoNeve)
    {
        // Kivesszük a lista első elemét, az a fejléc! Ezért itt is 1-től indul a ciklis!
        // Bejárjuk a listát és ha megfelelő névvel vizsgáljuk, akkor 
        // visszatérünk a versenyző döntőben elért pontjaival. 
        for (int i = 1; i < dontosok.size(); i++) 
        {
            if(dontosok.get(i).getNev().equals(versenyzoNeve))
            {
                float osszesDontosPontszam = (Float.valueOf(dontosok.get(i).getKurTechnikaiPontszam())
                        +Float.valueOf(dontosok.get(i).getKurKomponensiPontszam()))
                        -Float.valueOf(dontosok.get(i).getKurHibaPontszam());
                return osszesDontosPontszam;
            }            
        }
        return 0;
    }
     
    // 5. feladat 
    // Bekérünk egy nevet a felhasználótól.
    // Ellenőrizzük, hogy van e olyan nevű versenyző.
    private static String otodikFeladat()
    {
        // A Scanner osztály segítségével kérünk egy nevet a felhasználótóé
        Scanner nevetKer = new Scanner(System.in);
    
        // Kiírjuk a képernyőre a felhasználónak, hogy mit várunk tőle!
        System.out.println("5. feladat \n Kérem a versenyző nevét: ");
        
        // A felhasználó által beírt adatot String-é deklaráljuk (soronként)
        String nev = nevetKer.nextLine();
        
        // Meghatározunk egy String tipusú változót, amivel majd visszatér a metódus
        String visszaTeresiErtek = "";

        // Bejárjuk a listát, és ha van benne olyan név, amit a felhasználó beírt
        // egyből a hatodik feladattal tér vissza a metódusunk, ha viszont ...*
        for (int i = 0; i < rovidProgramLista.size(); i++) 
        {
            String egyNev = rovidProgramLista.get(i).getNev();
            if(egyNev.equals(nev))
            {
                return hatodikFeladat(egyNev);
            }
        }
        
        // *.. nincs ilyen név a listában, a feladatban meghatározott üzenettel térünk vissza
        return visszaTeresiErtek.concat("Ilyen Nevű versenyző nem indult!");
    }
    
    
    // 6. feladat
    // A versenyző pontszámaival térjünk vissza! 
    private static String hatodikFeladat(String versenyzoNeve)
    {
        //Használjuk a negyedik feladatban elkészített metódust! 
        return " 6. feladat:\n A versenyző összpontszáma: " + OsszPontszam(versenyzoNeve);
    }
    
    
    // 7. feladat
    // Egy új model(constructor) segítségével, 
    // A listát bejárva átadjuk az aktuális országkódot  
    // a lentebb lévő hetedikFeladatOrszagotSzamol() metódusnak.
    // Ez a metódus pedig visszatér egy számmal ami az összesített értéke 
    // az egyes országok előfordulásának. 
    private static void hetedikFeladat()
    {
        ArrayList<TobbVersenyzosOrszagokModel> versenyzok = new ArrayList<>();
        HashSet<String> listabanEllenoriz = new HashSet<>();
        
        TobbVersenyzosOrszagokModel egyVersenyzo;
        int max = 0;
        for (DontosVersenyzokModel dontosVersenyzokModel : dontosok) 
        {
            String orszagAktualisKod = dontosVersenyzokModel.getOrszag();
            
            max = hetedikFeladatOrszagotSzamol(orszagAktualisKod);
            
            egyVersenyzo = new TobbVersenyzosOrszagokModel();
            egyVersenyzo.setOrszagKod(orszagAktualisKod);
            egyVersenyzo.setVersenyzokSzama(String.valueOf(max));
            
            if(!listabanEllenoriz.contains(orszagAktualisKod) && max >= 2)
            {
                versenyzok.add(egyVersenyzo);
                listabanEllenoriz.add(orszagAktualisKod);
            }
        }
        
        System.out.println("7. Feladat: ");
        for (TobbVersenyzosOrszagokModel tobbVersenyzosOrszagokModel : versenyzok) 
            {
                String eredmeny = tobbVersenyzosOrszagokModel.getOrszagKod() 
                        + ": " 
                        + tobbVersenyzosOrszagokModel.getVersenyzokSzama();
                System.out.println(eredmeny+" versenyző");
            }
    }
    
    
    // 7. feladat 
    // Bejárjuk a tömböt a kapott országkóddal és megnézzük, hányszor, 
    // ismétlődik a listában. Ehhez deklarálunk egy számlálót, amivel 
    // a függvény a hetedikFeladat() nevű metódusban visszatér!
    private static int hetedikFeladatOrszagotSzamol(String orszagKodja)
    {
        int max = 0;
        int szamlalo = 0;

        for (DontosVersenyzokModel dontosVersenyzokModel : dontosok) 
        {
            if(dontosVersenyzokModel.getOrszag().equals(orszagKodja))
            {
                szamlalo++; 
                
                if(szamlalo > max)
                {
                    max = szamlalo;                   
                }
            }
        }
        
        return max;
    }
    
    // 8. feladat két listát használunk, illetve egy segítő metódust, 
    // ami String értékkel tér vissza
    private static void nyolcadikFeladat()
    {
        String nev = "";
        // Egy Létrehozott modell segítségével feltöltünk egy listát,
        // Ez megkönnyíti az elemek sorba rendezését.
        VegeredmenyModel egyVersenyzoEredmenye;
        // A lista a VegeredmenyModel típust veszi fel, így ebben a 
        // modelben v. konstruktorban szereplő elemeket várja, majd elemeinek
        ArrayList<VegeredmenyModel> ideiglenesLista = new ArrayList<>();
        // Erre a listára, majd a fájl létrehozásánál lesz szükségünk,
        // ebben a listában tároljuk el sorba rendezve az egyes versenyzőket!
        // Ezen lista elemeket írjuk majd soronként a fájlba
        ArrayList<String> kiirandLista = new ArrayList<>();
        
        
        // Bejárjuk a döntősök listáját, hiszen a végeredményben
        for (int i = 1; i < dontosok.size(); i++) 
        {
            // Megkeressük a döntősök nevét, 
            // kiszámoltatjuk a segítő függvényt "nyolcadikFeladatPontokatSzamol(versenyzőNeve)" használva,
            // adott versenyzőnek a pontjait, majd az adatokat feltöltjük a modellbe!
            nev = dontosok.get(i).getNev();
            String pontok = nyolcadikFeladatPontokatSzamol(nev);
            String orszag = dontosok.get(i).getOrszag();
            
            // Adatok, értékek átadása a modellnek
            egyVersenyzoEredmenye = new VegeredmenyModel();
            egyVersenyzoEredmenye.setNev(nev);
            egyVersenyzoEredmenye.setOrszagKod(orszag);
            egyVersenyzoEredmenye.setOsszPontszam(String.valueOf(pontok));
            // Az ideiglenes listába betesszük, még a for ciklusban az összes elemet!
            ideiglenesLista.add(egyVersenyzoEredmenye);
        }
        
        /* A Collections osztály sort() metódusa segítségével sorba rendezzük a listát, 
           A reverseOrder() függvény változtatja meg a sorok irányát >>csökkenőre<<
           konstruktorában vár egy utasítást, hogy mi alapján rendezze sorba a listát!
           Mivel a lista típusa egyedi álltalunk megírt osztály
           szükségünk van a Comparator interface comparing() függvényére, 
           ebben a függvényben pedig meghatározzuk, hogy 
           A model >>VegeredmenyModel<< :: (melyik eleme alapján) >>getOsszPontszam<< legyen rendezve a lista.
        */         
        Collections.sort(ideiglenesLista, Collections.reverseOrder
                                    (Comparator.comparing(VegeredmenyModel::getOsszPontszam)));

        // A már rendezett listát bejárjuk
        for (int j = 0; j < ideiglenesLista.size(); j++) 
        {
            // A lista elemeit használva egy helyi változó értékének átadjuk 
            // az egyes elemek értékeit, majd ezt hozzáadjuk a kiirandoListához!
            String egySor = String.valueOf(j+1)+";"+ideiglenesLista.get(j).getNev()
                                    +";"+ideiglenesLista.get(j).getOrszagKod()
                                    +";"+ideiglenesLista.get(j).getOsszPontszam();
            kiirandLista.add(egySor);
            
            // Meghívjuk a VegeredmenytLetrehozo osztály vegEredmenytLetrehoz függvényét
            // paraméterének átadjuk a kiirandó listát és meghatározzuk a létrehozandó fájl nevét.
            VegeredmenytLetrehozo.vegEredmenytLetrehoz(kiirandLista, "vegeredmeny.csv");
        }
    }
    
    // 8. feladat segítő metódus egyes versenyzők összpontszámának meghatározása
    private static String nyolcadikFeladatPontokatSzamol(String vNeve)
    {
        // Deklarálunk két változót, amiknek adnunk kell valamilyen értéket
        String nev = "";
        String osszPont = "";

        // Bejárjuk a döntősök listáját, a függvény konstruktorában kért értéket keressük,
        // ha ez az érték a listában valóban szerepel, akkor a 4. feladatban megírt OsszPontszam(versenyzoNeve) fg.
        // segítségével a korábban deklarált osszPont változónak átadjuk a fg. értékét és ezzel térünk vissza.
        // Amennyiben nincs meg a versenyző, "" nem térünk vissza releváns értékkel. 
        for (int i = 1; i < dontosok.size(); i++) 
        {
            nev = dontosok.get(i).getNev();
            if(nev.equals(vNeve))
            {
                osszPont = OsszPontszam(nev);                
                return osszPont;
            }

        }
        return "";
    }
    
    
}

