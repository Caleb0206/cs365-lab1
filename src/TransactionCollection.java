import java.time.LocalDate;
import java.util.ArrayList;

public class TransactionCollection {
    private ArrayList<Transaction> transactions;
    private CreditCardCollection cardCollection;

    public TransactionCollection(CreditCardCollection cardCollection) {
        transactions = new ArrayList<>();
        this.cardCollection = cardCollection;
    }

    public void addTransaction(Transaction tr, double cost) {
        transactions.add(tr);
        CreditCard card = cardCollection.getCard(tr.getCardNum());
        if (card == null) {
            System.out.println("\tTransactionCollection: Card number not found");
            return;
        }
        card.setBalance(card.getBalance() + cost);
    }

    public void printTransactionsBetweenDates(int cardNum, String start, String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        CreditCard card = cardCollection.getCard(cardNum);
        for (Transaction tr : transactions) {
            if (tr.getCardNum() == cardNum && !tr.getDate().isBefore(startDate) && !tr.getDate().isAfter(endDate)) {
                System.out.println("\t" + tr);
            }
        }
    }
}
