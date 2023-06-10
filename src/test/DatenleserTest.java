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
		assertEquals(testEins.getLine(), "Schuelerinfo_ID;Note(MSS);Notenart_ID");
		assertEquals(true, testEins.hasMoreLines());
		testEins.closeFile();
	}

	@Test
	public void testGetSchuelerliste() throws IOException {
		Datenleser testZwei = new Datenleser();
		testZwei.setFilePath("BSIT22b");
		testZwei.initReader();
		assertEquals(testZwei.getLine(), "Name;E-Mail;ID");
		assertEquals(true, testZwei.hasMoreLines());
		testZwei.closeFile();
	}

	@Test
	public void testNotenGetter() throws IOException {
		Datenleser testDrei = new Datenleser();
		testDrei.setFilePath("Deutsch", "BSIT22b");
		testDrei.initReader();
		int[] value = { 11 };
		int[] actualValue = testDrei.getNoten(2, 1);
		assertEquals(actualValue.length, value.length);
		assertEquals(actualValue[0], value[0]);
		testDrei.closeFile();
	}

	@Test
	public void testImportKlasse() throws IOException {
		Datenleser testVier = new Datenleser();
		testVier.importKlasse();
		File file = new File("./CSV_Dateien/Test/Schuelerliste.csv");
		assertEquals(true, file.exists());
		file.delete();
	}

	@Test
	public void testGetFaecherNamen() throws IOException {
		Datenleser testFuenf = new Datenleser();
		assertTrue("Deutsch.csv".equals(testFuenf.getFaecherNamen("BSIT22b").get(0)));
	}

	@Test
	public void testGetKlassenNamen() throws IOException {
		Datenleser testSechs = new Datenleser();
		assertTrue("BSIT22a".equals(testSechs.getKlassenNamen().get(0)));
		assertTrue("BSIT22b".equals(testSechs.getKlassenNamen().get(1)));
		assertTrue(testSechs.getClassNames().contains("BSIT22a"));
		assertTrue(testSechs.getClassNames().contains("BSIT22b"));
	}

	@Test
	public void testGetAverage() throws IOException {
		Datenleser testSieben = new Datenleser();
		testSieben.setFilePath("Deutsch", "BSIT22b");
		testSieben.initReader();
		assertTrue(testSieben.getAverage(0) == 10.0);
		testSieben.closeFile();
		testSieben.initReader();
		assertTrue(testSieben.getAverage(1) == 12.0);
		testSieben.closeFile();
		testSieben.initReader();
		assertTrue(testSieben.getAverage(2) == 11.0);
		testSieben.closeFile();
	}

	@Test
	public void testWriteNote() throws IOException {
		Datenleser testAcht = new Datenleser();
		testAcht.setFilePath("Deutsch", "BSIT22b");
		testAcht.writeNote(10,0, 3);
		testAcht.initReader();
		int skippedLines = 0;
		while (skippedLines != 4) {
			testAcht.getLine();
			skippedLines++;
		}
		assertEquals("1;10;3",testAcht.getLine());
		// Eintrag muss wieder gelöscht werden
		testAcht.closeFile();
		
	}

	@Test // Test muss allgemeiner gehalten werden, klappt durch Testpfad nur auf Max
			// Laptop
	public void testExportKlasse() throws IOException {
		Datenleser testNeun = new Datenleser();
		testNeun.exportKlasse();
		File file = new File("C:/Users/LG/Desktop/ExportTest/BSIT22b/Schuelerliste.csv");
		assertTrue(file.exists());
		file.delete();
	}

}
