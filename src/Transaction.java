import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private int customerId;
    private int cardNum;
    private int venderId;

    public Transaction(String date, int customerId, int cardNum, int venderId) {
        this.date = LocalDate.parse(date); // (yyyy-mm-dd) from https://www.geeksforgeeks.org/java-time-localdate-class-in-java/
        this.customerId = customerId;
        this.cardNum = cardNum;
        this.venderId = venderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getCardNum() {
        return cardNum;
    }

    @Override
    public String toString() {
        return "Transaction: " + date +
                ", customerId = " + customerId +
                ", cardNum = " + cardNum +
                ", venderId = " + venderId;
    }
}
