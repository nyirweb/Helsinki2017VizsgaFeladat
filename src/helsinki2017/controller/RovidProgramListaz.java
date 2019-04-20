package helsinki2017.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import helsinki2017.model.RovidProgramModel;


public class RovidProgramListaz 
{
    public static ArrayList<RovidProgramModel> rovidProgramLista(String fajlNeve,int oszlopokSzama)
    {
        ArrayList<RovidProgramModel> rovidProgramFeltoltottLista = new ArrayList<>();
        RovidProgramModel egyRovidProgram;
        
        String egySor;
        String[] szeletek = new String[oszlopokSzama];
        
        RandomAccessFile fajltBeolvas;
        
        try 
        {
            fajltBeolvas = new RandomAccessFile(fajlNeve,"r");
            egySor = fajltBeolvas.readLine();
            
            while(egySor != null)
            {
                String utf = new String(egySor.getBytes("ISO-8859-1"));
                szeletek = utf.split(";");
                
                egyRovidProgram = new RovidProgramModel();
                egyRovidProgram.setNev(szeletek[0]);
                egyRovidProgram.setOrszagKod(szeletek[1]);
                egyRovidProgram.setTechnikaiPontszam(szeletek[2]);
                egyRovidProgram.setKomponensPontszam(szeletek[3]);
                egyRovidProgram.setHibapont(szeletek[4]);

                rovidProgramFeltoltottLista.add(egyRovidProgram);
            
                egySor = fajltBeolvas.readLine();
            }
        }
        catch (FileNotFoundException ex) 
        {
            ex.printStackTrace();
        } catch (IOException ex) 
        {
            ex.printStackTrace();
        }
        
        return rovidProgramFeltoltottLista;
    }
}
