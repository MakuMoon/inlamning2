import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

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
    public void testTest() {
        assertEquals(true, true);
    }

}
