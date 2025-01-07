public class Transaction {
    private String date;
    private int customerId;
    private String cardNum;
    private int venderId;

    public Transaction(String date, int customerId, String cardNum, int venderId) {
        this.date = date;
        this.customerId = customerId;
        this.cardNum = cardNum;
        this.venderId = venderId;
    }

}
