import java.time.LocalDate;

public class Person {

    private String name;
    private int pNumber;
    private LocalDate membershipDate;

    public Person(String name, int pNumber, LocalDate membershipDate) {
        this.name = name;
        this.pNumber = pNumber;
        this.membershipDate = membershipDate;
    }

    public String getName() {
        return name;
    }

    public int getpNumber() {
        return pNumber;
    }

    public LocalDate getDate() {
        return membershipDate;
    }

}
