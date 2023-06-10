package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import csv_reader_stuff.DateWriter;
import school_attributes.Student;

public class DateWriterTest {

    @Test
    public void testCreateFiles(){
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
    public void testStudentEntry() {
    	DateWriter testZwei = new DateWriter();
    	testZwei.addEntryToCSV("JUnits","Test McTest","Test@Test.Test");
    	assertEquals(1,testZwei.countStudents("JUnits"));
    	assertTrue(testZwei.getStudentList("JUnits").get(0).getName().equals("Test McTest"));
    	assertTrue(testZwei.getStudentList("JUnits").get(0).getEmail().equals("Test@Test.Test"));
    	assertTrue(testZwei.getStudentList("JUnits").get(0).getId().equals("1"));
    }
    
    @Test
    public void test
    
    
}
