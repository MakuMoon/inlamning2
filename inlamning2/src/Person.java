import java.time.LocalDate;

public class Person {

    private String name;
    private long pNumber;
    private LocalDate membershipDate;

    public Person(String name, long pNumber, LocalDate membershipDate) {
        this.name = name;
        this.pNumber = pNumber;
        this.membershipDate = membershipDate;
    }

    public String getName() {
        return name;
    }

    public long getpNumber() {
        return pNumber;
    }

    public LocalDate getDate() {
        return membershipDate;
    }

}
