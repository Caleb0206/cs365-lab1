import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class OwnershipCollection {
    private HashSet<Ownership> ownerships;
    private HashMap<Integer, List<Ownership>> customerIndex;
    private HashMap<Integer, List<Ownership>> cardIndex;

    private CreditCardCollection cardCollection;
    private CustomerCollection customerCollection;

    public OwnershipCollection(CustomerCollection customerCollection, CreditCardCollection cardCollection) {
        ownerships = new HashSet<>();
        customerIndex = new HashMap<>();
        cardIndex = new HashMap<>();

        this.cardCollection = cardCollection;
        this.customerCollection = customerCollection;
    }

    public void createOwnership(Customer person, CreditCard card) {
        Ownership ownership = new Ownership(person.getID(), card.getNumber(), true);
        ownerships.add(ownership);
        if (!customerIndex.containsKey(person.getID()))
            customerIndex.put(person.getID(), new ArrayList<>());
        customerIndex.get(person.getID()).add(ownership);

        if (!cardIndex.containsKey(card.getNumber())) {
            cardIndex.put(card.getNumber(), new ArrayList<>());
        }
        cardIndex.get(card.getNumber()).add(ownership);

    }

    public void deactivateOwnership(int customerId, int cardNum) {
        Ownership cancelled = new Ownership(customerId, cardNum, true);
        if (ownerships.contains(cancelled)) {
            ownerships.remove(cancelled);
            cancelled.setCurrent(false);
            cardCollection.getCard(cardNum).setActive(false);
            ownerships.add(cancelled); // add the inactive ownership back to set

            customerIndex.get(customerId).removeIf(o -> o.getCardNum() == cardNum && o.isCurrent());
            cardIndex.get(cardNum).removeIf(o -> o.getCustomerId() == customerId && o.isCurrent());

            customerIndex.get(customerId).add(cancelled);
            cardIndex.get(cardNum).add(cancelled);
        }
    }

    /**
     * Queries for Credit Card Info by Customer Id
     */
    public void printCreditCardByCustomerId(int customerId) {
        String formatBalance = "%.2f";
        List<Ownership> ownList = customerIndex.get(customerId);
        if (ownList == null || ownList.isEmpty()) {
            System.out.println(ownList);
            System.out.println("Customer ID not found: " + customerId);
            return;
        }

        for (Ownership ownership : ownList) {
            if (ownership.getCustomerId() == customerId && ownership.isCurrent()) {
                CreditCard temp = cardCollection.getCard(ownership.getCardNum());
                if (temp != null) {
//                    System.out.println(temp);
                    System.out.println(temp.getNumber() +
                            " Limit = $" + temp.getLimit() +
                            " Balance = $" + String.format(formatBalance, temp.getBalance()));
                }
            }
        }
    }

    /**
     * Queries for Credit Card Info by Customer SSN
     */
    public void printCreditCardByCustomerSSN(String ssn) {
        Customer person = customerCollection.getBySSN(ssn);
        if (person == null) {
            System.out.println("No customer found with SSN: " + ssn);
            return;
        }
        printCreditCardByCustomerId(person.getID());
    }

    public void printByCard(int cardNum) {
        String formatBalance = "%.2f";
        List<Ownership> ownList = cardIndex.get(cardNum);
        if (ownList == null || ownList.isEmpty()) {
            System.out.println("No ownerships found with card: " + cardNum);
            return;
        }
        CreditCard card = cardCollection.getCard(cardNum);
        if (card == null) {
            System.out.println("Credit Card number not found: " + cardNum);
            return;
        }

        List<Integer> customerIds = new ArrayList<>();
        for (Ownership ownership : ownList) {
            if (ownership.isCurrent()) {
                customerIds.add(ownership.getCustomerId());
            }
        }

        System.out.println("Card Info: Balance = $" + String.format(formatBalance, card.getBalance()) +
                " Limit = $" + card.getLimit() +
                " Holders = " + customerIds);

    }


}
