package helsinki2017.controller;

import helsinki2017.model.DontosVersenyzokModel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;



public class DontosVersenyzokLista 
{
    public static ArrayList<DontosVersenyzokModel> dontosok(String fajlNeve,int oszlopok)
    {
        ArrayList<DontosVersenyzokModel> dontosokListaja = new ArrayList<>();
        DontosVersenyzokModel egyDontos;
        
        String[] szeletek = new String[oszlopok];
        String egySor;
        
        RandomAccessFile raf;
        
        try 
        {
            raf = new RandomAccessFile(fajlNeve, "r");
            egySor = raf.readLine();
            
            while(egySor != null)
            {
                String utf = new String(egySor.getBytes("ISO-8859-1"));
                szeletek = utf.split(";");
                
                egyDontos = new DontosVersenyzokModel();
                egyDontos.setNev(szeletek[0]);
                egyDontos.setOrszag(szeletek[1]);
                egyDontos.setKurTechnikaiPontszam(szeletek[2]);
                egyDontos.setKurKomponensiPontszam(szeletek[3]);
                egyDontos.setKurHibaPontszam(szeletek[4]);
                
                dontosokListaja.add(egyDontos);
                
                egySor = raf.readLine();
            }
        }
        catch (FileNotFoundException ex) 
        {
            ex.printStackTrace();
        }
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
        return dontosokListaja;
    }
    
}
