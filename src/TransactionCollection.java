import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.Map;

public class TransactionCollection {
    private HashMap<Integer, TreeMap<LocalDate, List<Transaction>>> transactions;
    //HashMap<String (DATE), TreeMap<Date, List<> of transactions>> mapHash;
    private CreditCardCollection cardCollection;

    public TransactionCollection(CreditCardCollection cardCollection) {
        transactions = new HashMap<>();
        this.cardCollection = cardCollection;
    }

    public void createTransaction(String date, int customerId, int cardNum, int venderId, double cost) {

        CreditCard card = cardCollection.getCard(cardNum);
        if (card == null) {
            System.out.println("\tTransactionCollection: Card number not found");
            return;
        }
        if (!card.isActive()) {
            System.out.println("\tCard is not activated. Cannot make transaction.");
            return;
        }
        // Update the balance of the credit card
        card.setBalance(card.getBalance() + cost);


        Transaction transaction = new Transaction(date, customerId, cardNum, venderId);
        // make sure card has an entry in the HashMap
        if (!transactions.containsKey(cardNum))
            transactions.put(cardNum, new TreeMap<>());

        LocalDate parsedDate = LocalDate.parse(date); // parsed String to LocalDate

        // make sure the date has an entry in the TreeMap
        TreeMap<LocalDate, List<Transaction>> cardTransactions = transactions.get(cardNum);
        if (!cardTransactions.containsKey(parsedDate)) {
            cardTransactions.put(parsedDate, new ArrayList<>());
        }

        // add the transaction to the List in the specified TreeMap in the CardNum HashMap
        List<Transaction> dailyTransactions = cardTransactions.get(parsedDate);
        dailyTransactions.add(transaction);

    }


    public void printTransactionsBetweenDates(int cardNum, String start, String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        TreeMap<LocalDate, List<Transaction>> cardTransactions = transactions.get(cardNum);
        Map<LocalDate, List<Transaction>> withinRange = cardTransactions.subMap(startDate, true, endDate, true);

        // Print out the transactions only within the range of start and end dates
        if (!withinRange.isEmpty()) {
            for (Map.Entry<LocalDate, List<Transaction>> entry : withinRange.entrySet()) {
                for (Transaction transaction : entry.getValue()) {
                    System.out.println(transaction);
                }
            }
        }
    }
}
