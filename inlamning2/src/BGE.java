
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;


public class BGE {

    private BufferedReader br;
    private ArrayList<Person> members = new ArrayList<Person>();
    private Scanner sc = new Scanner(System.in);
    private LocalDate todayDate = LocalDate.now();
    private LocalDateTime todayDateTime = LocalDateTime.now();
    private Boolean testFound;

    public BGE() {
        readFile();
        runScanner();
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
        System.out.println("\nTryck på enter för att skriva in en ny medlem");
        sc.nextLine();
        
    }

    }

    public void readFile(){
        try {
            FileReader fr = new FileReader("./inlamning2/src/data.txt");
            br = new BufferedReader(fr);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
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
        person = person.trim();

        for (Person p : members) {
            if (person.equalsIgnoreCase(p.getName().trim()) || person.equals(String.valueOf(p.getpNumber()))) {
                if (p.getDate().isAfter(todayDate.minus(1, java.time.temporal.ChronoUnit.YEARS))) {
                    System.out.println("(kund)");
                    System.out.println(p.getName() + " är en nuvarande medlem");
                    ptPrint(p);
                } else {
                    System.out.println("(fd. kund)");
                    System.out.println(p.getName() + " är en före detta kund");
                }
                found = true;
            }
        }
        if (!found) {
            System.out.println("(obehörig)");
            System.out.println("Personen finns inte i filen och har sålunda aldrig varit medlem och är obehörig.");
        }


        return found;
    }

    public void ptPrint(Person p) {
        try {
            FileWriter fw = new FileWriter("./inlamning2/src/ptData.txt", true);
            fw.write(p.getName() + ", " + p.getpNumber() + ", " + todayDateTime.format(DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")) + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }

}
