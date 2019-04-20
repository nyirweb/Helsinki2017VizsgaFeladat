package helsinki2017.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class VegeredmenytLetrehozo 
{
    String[] egyOszlopEleme;
    String sor;
    public static void vegEredmenytLetrehoz(ArrayList<String> sorok,String fajlNev)
    {
        RandomAccessFile rFile;
        try 
        {
            rFile = new RandomAccessFile(fajlNev,"rw");
            for (String egySor : sorok) 
            {
                rFile.writeBytes(egySor+"\r"+"\n");
            }
        } 
        catch (FileNotFoundException ex) 
        {
            System.out.println("Fajl irás hiba: "+ex.getMessage());
        }
        catch (IOException ex) 
        {
            System.out.println("Fajl irás hiba: "+ex.getMessage());
        }
    }
    
}
