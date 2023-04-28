package test;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import csv_reader_stuff.Datenleser;

public class DatenleserTest {

    @Test
    public void testDatenleser() throws IOException {
        Datenleser testEins = new Datenleser();
        testEins.setFilePath("Deutsch", "BSIT22b");
        testEins.initReader();
        assertEquals(testEins.getLine(),"Das ist eine Test Datei");
    }
}
