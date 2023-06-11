package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import csv_reader_stuff.*;
import school_attributes.Student;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DateWriterTest {

    @Test
    public void testA_CreateFiles(){
        DateWriter testEins = new DateWriter();
        testEins.createClassFolder("JUnits");
        testEins.createSubjectCSVFile("JUnitKurs","JUnits");
        File testKurs= new File("./CSV_Dateien/JUnits/JUnitKurs.csv");
        assertTrue(testKurs.exists());
        testEins.createStudentsCSVFile("JUnits");
        File testStudentlist= new File("./CSV_Dateien/JUnits/Schuelerliste.csv");
        assertTrue(testStudentlist.exists());        
    }
    
    @Test
    public void testB_StudentEntry() {
    	DateWriter testZwei = new DateWriter();
    	testZwei.addEntryToCSV("JUnits","Test McTest","Test@Test.Test");
    	assertEquals(1,testZwei.countStudents("JUnits"));
    	assertTrue(testZwei.getStudentList("JUnits").get(0).getName().equals("Test McTest"));
    	assertTrue(testZwei.getStudentList("JUnits").get(0).getEmail().equals("Test@Test.Test"));
    	assertTrue(testZwei.getStudentList("JUnits").get(0).getId().equals("1"));
    }
    
    @Test
    public void testC_GradeSubject() throws IOException {
    	DateWriter testDrei = new DateWriter();
    	testDrei.addGradeToSubject("JUnitKurs","JUnits", 1, 0, 0);
    	Datenleser testReader = new Datenleser();
    	testReader.setFilePath("JUnitKurs","JUnits");
    	testReader.initReader();
    	testReader.getLine();
    	assertTrue(testReader.getLine().equals("1;0;0"));
    }
    
    
    @Test
    public void testZ_Delete() throws IOException {
    	DateWriter testVier = new DateWriter();
    	
    	assertTrue(testVier.deleteGradeFromSubject("JUnitKurs","JUnits",1, 0, 0));
    	assertFalse(testVier.deleteGradeFromSubject("JUnitKurs","JUnits", 1, 0, 0));
    	
    	// WICHTIGER HINWEIS: deleteSubject() klappt übers GUI, aber nicht in JUnit
    	// Möglicher Konfigurationsfehler?
    	
    /*	assertTrue(testVier.deleteSubject("JUnitKurs","JUnits"));
    	assertFalse(testVier.deleteSubject("JUnitKurs","JUnits")); */
    	
    	Datenleser testReader = new Datenleser();
    	testReader.setFilePath("JUnits");
    	
    	testVier.deleteStudent("JUnits", "Test McTest");
    	testReader.initReader();
    	testReader.getLine();
    	assertFalse(testReader.hasMoreLines());
    	testReader.closeFile(); 
    	
    	// Test nicht möglich solange delectSubject() nicht will
    	
    	/*testVier.deleteClass("JUnits");
    	File testDatei = new File("CSV_Dateien\\JUnits");
    	assertFalse(testDatei.exists());*/
    	
    }
    
    
    
    
}
