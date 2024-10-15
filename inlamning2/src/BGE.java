import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class BGE {

    private BufferedReader br;
    private ArrayList<Person> members = new ArrayList<Person>();

    public BGE() {
        run();
    }

    public static void main(String[] args)  {
        BGE bge = new BGE();
    }

    public void run() {

        try {
            FileReader fr = new FileReader("./src/data.txt");
            br = new BufferedReader(fr);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        String line;
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] split = line.split(",");
                if ((line = br.readLine()) != null) {
                    
                    LocalDate date = LocalDate.parse(line);

                    int pNumber = Integer.parseInt(split[0]);

                    Person person = new Person(split[1], pNumber, date);
                    
                    members.add(person);
                    
                }

            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
        
        for (Person p : members) {
            System.out.println(p.getName() + " " + p.getpNumber() + " " + p.getDate());
        }

    }

}
