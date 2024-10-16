import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class BGETest {

    private ArrayList<Person> members = new ArrayList<Person>();

    public void setMembers() {
        BGE bge = new BGE("");
        members = bge.getMembers();
    }

    @Test
    public void testNames() {
        setMembers();

        Boolean testFound = false;
        String falseName = "null";

        for (Person p : members) {
            BGE bgeTest = new BGE(p.getName());
            if (!bgeTest.getTestFound()) {
                falseName = p.getName();
                testFound = false;
                break;
            }
            testFound = true;
        }

        assertEquals(true, testFound, "Name not found: " + falseName);
    }

    @Test
    public void testPNumbers() {
        setMembers();

        Boolean testFound = false;
        long falsePNumber = 0;

        for (Person p : members) {
            BGE bgeTest = new BGE(p.getpNumber());
            if (!bgeTest.getTestFound()) {
                falsePNumber = p.getpNumber();
                testFound = false;
                break;
            }
            testFound = true;
        }

        assertEquals(true, testFound, "PNumber not found: " + falsePNumber);
    }

    @Test
    public void testOneName() {

        BGE bge = new BGE("Hilmer Heur");
        Boolean testFound = bge.getTestFound();

        assertEquals(true, testFound, "Name not found");
    }

    @Test
    public void testOneNameNotFound() {

        BGE bge = new BGE("Hlr");
        Boolean testFound = bge.getTestFound();

        assertEquals(false, testFound, "Name not found");
    }

    @Test
    public void testOneNumber() {
        BGE bge = new BGE(5711121234L);
        Boolean testFound = bge.getTestFound();

        assertEquals(true, testFound, "Name not found");
    }

    @Test
    public void testOneNumberNotFound() {

        BGE bge = new BGE(3732687653L);
        Boolean testFound = bge.getTestFound();

        assertEquals(false, testFound, "Number not found");
    }

    @Test
    public void testCorrectPtPrint() {
        LocalDateTime todayDateTime = LocalDateTime.now();

        String lastLine = correctPtPrint("Greger Ganache");
        assertEquals("Greger Ganache, 7512166544, " + todayDateTime.format(DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")), lastLine);

        lastLine = correctPtPrint("Nahema Ninsson");
        assertEquals("Nahema Ninsson, 7805211234, " + todayDateTime.format(DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")), lastLine);

    }

    public String correctPtPrint(String name){
        BGE bge = new BGE(name);
        Boolean testFound = bge.getTestFound();
    
        assertEquals(true, testFound, "Name not found");

        FileReader fr;
        String lastLine = null;
        try {
            fr = new FileReader("./inlamning2/src/ptData.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                lastLine = currentLine;
            }
    
    
        } catch (FileNotFoundException e) {
            fail("Test failed due to file not found");
        } catch (IOException e) {
            fail("Test failed due to file read error"); 
        }
        return lastLine;
    }

    @Test
    public void testTest() {
        assertEquals(true, true);
    }

}
