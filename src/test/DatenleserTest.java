package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import org.junit.Test;
import csv_reader_stuff.Datenleser;

public class DatenleserTest {

    @Test
    public void testDatenleser() throws IOException {
        Datenleser testEins = new Datenleser();
        testEins.setFilePath("Deutsch", "BSIT22b");
        testEins.initReader();
        assertEquals(testEins.getLine(),"Schuelerinfo_ID;Note(MSS);Notenart_ID");
        assertEquals(true,testEins.hasMoreLines());
        testEins.closeFile();
    }

    @Test
    public void testAverage() throws IOException {
        Datenleser testZwei = new Datenleser();
        testZwei.setFilePath("Deutsch", "BSIT22b");
        testZwei.initReader();
        assertEquals(testZwei.getAverage(0),10,0.2);       
    }

    @Test
    public void testNotenGetter() throws IOException {
        Datenleser testDrei = new Datenleser();
        testDrei.setFilePath("Deutsch", "BSIT22b");
        testDrei.initReader();
        int[] value={10};
        int[] actualValue=testDrei.getNoten(0,3);
        assertEquals(actualValue.length,value.length);
        assertEquals(actualValue[0],value[0]);       
    }

    @Test
    public void testImportKlasse() throws IOException{
        Datenleser testVier = new Datenleser();
        testVier.importKlasse();
        File file = new File("./CSV_Dateien/Test/Schuelerliste.csv");
        assertEquals(true,file.exists());
        file.delete();
    }

    @Test
    public void testGetFaecherNamen() throws IOException{
        Datenleser testFuenf = new Datenleser();
        assertTrue("Deutsch.csv".equals(testFuenf.getFaecherNamen("BSIT22b").get(0)));
    }

    @Test
    public void testGetKlassenNamen() throws IOException{
        Datenleser testSechs = new Datenleser();
        assertTrue("BSIT22a".equals( testSechs.getKlassenNamen().get(0)));
        assertTrue("BSIT22b".equals( testSechs.getKlassenNamen().get(1)));
    }

    @Test
    public void testWriteNote() throws IOException{
        Datenleser testSieben = new Datenleser();
        System.out.println("test");
        testSieben.setFilePath("Deutsch", "BSIT22b");
        testSieben.writeNote(1, 1, 1);
    }
    
}
