package test;
import static org.junit.Assert.assertEquals;

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
    }

    @Test
    public void testAverage() throws IOException {
        Datenleser testZwei = new Datenleser();
        testZwei.setFilePath("Deutsch", "BSIT22b");
        testZwei.initReader();
        assertEquals(testZwei.getAverage(1),10,0.2);       
    }

    @Test
    public void testNotenGetter() throws IOException {
        Datenleser testDrei = new Datenleser();
        testDrei.setFilePath("Deutsch", "BSIT22b");
        testDrei.initReader();
        int[] value={10};
        int[] actualValue=testDrei.getNoten(1);
        assertEquals(actualValue.length,value.length);
        assertEquals(actualValue[0],value[0]);       
    }


}
