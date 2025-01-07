import java.util.ArrayList;

public class TransactionCollection {
    ArrayList<Transaction> transactions = new ArrayList<>();
    public void addTransaction(Transaction tr)
    {
        transactions.add(tr);
    }
}
