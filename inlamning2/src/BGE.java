import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class BGE {

    private BufferedReader br;
    private ArrayList<Person> members = new ArrayList<Person>();
    private Scanner sc = new Scanner(System.in);
    private LocalDate todayDate = LocalDate.now();
    private Boolean testFound;

    public BGE() {
        readFile();
        runScanner();
        System.out.println("\nTryck på enter för att skriva in en ny medlem");
        sc.nextLine();
    }

    public BGE(String name) {
        readFile();
        testFound = runName(name);
    }

    public BGE(long pNumber) {
        readFile();
        testFound = runPNumber(pNumber);
    }

    public static void main(String[] args) {
        BGE bge = new BGE();
    }

    public boolean getTestFound() {
        return testFound;
    }

    public ArrayList<Person> getMembers() {
        return members;
    }

    public boolean runName(String name) {
        return searchPerson(name);
    }

    public boolean runPNumber(long pNumber) {
        
        return searchPerson(String.valueOf(pNumber));
    }

    public void runScanner() {
        while (true) {
            
        System.out.println("\033[H\033[2J");
        System.out.println("Välkommen till BGE:s medlemsregister");
        System.out.print("Vem söker du efter: ");

        String person = sc.nextLine();
        System.out.println("\033[H\033[2J");
        searchPerson(person);

        
    }

    }

    public void readFile(){

        try {
            FileReader fr = new FileReader("./src/data.txt");
            br = new BufferedReader(fr);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        String line;
        try {
            while ((line = br.readLine()) != null) {
                String[] split = line.split(", ");
                if ((line = br.readLine()) != null) {

                    LocalDate date = LocalDate.parse(line);

                    Long pNumber = Long.parseLong(split[0]);

                    Person person = new Person(split[1], pNumber, date);

                    members.add(person);

                }

            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

    }

    public boolean searchPerson(String person){
        boolean found = false;

        for (Person p : members) {
            if (person.equals(p.getName()) || person.equals(String.valueOf(p.getpNumber()))) {
                if (p.getDate().isAfter(todayDate.minus(1, java.time.temporal.ChronoUnit.YEARS))) {
                    System.out.println(p.getName() + " är en nuvarande medlem");
                    //ptPrint(p);
                } else if (!p.getDate().isAfter(todayDate.minus(1, java.time.temporal.ChronoUnit.YEARS))) {
                    System.out.println(p.getName() + " är en före detta kund");
                }
                found = true;
            }
        }
        if (!found) {
            System.out.println("Personen inte finns i filen och har sålunda aldrig varit medlem och är obehörig.");
        }


        return found;
    }

    public void ptPrint(Person p) throws IOException{
        FileWriter fw = new FileWriter("./src/ptData.txt");
        fw.write(p.getName() + ", " + p.getpNumber() + ", " + p.getDate());
    }

}
