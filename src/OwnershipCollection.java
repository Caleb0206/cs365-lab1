import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OwnershipCollection {
    private HashSet<Ownership> ownerships;
    private CreditCardCollection cardCollection;
    private CustomerCollection customerCollection;

    public OwnershipCollection(CustomerCollection customerCollection, CreditCardCollection cardCollection) {
        this.ownerships = new HashSet<>();
        this.cardCollection = cardCollection;
        this.customerCollection = customerCollection;
    }

    public void createOwnership(Customer person, CreditCard card) {
        ownerships.add(new Ownership(person.getID(), card.getNumber(), true));
    }

    public void deactivateOwnership(int customerId, int cardNum) {
        Ownership cancelled = new Ownership(customerId, cardNum, true);
        if (ownerships.contains(cancelled)) {
            ownerships.remove(cancelled);
            cancelled.setCurrent(false);
            cardCollection.getCard(cardNum).setActive(false);
            ownerships.add(cancelled); // add the inactive ownership back to set
        }
    }

    /**
     * Queries for Credit Card Info by Customer Id
     */
    public void printCreditCardByCustomerId(int customerId) {
        String formatBalance = "%.2f";

        for (Ownership ownership : ownerships) {
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
        List<Integer> customerIds = new ArrayList<>();
        CreditCard card = null;

        for (Ownership ownership : ownerships) {
            if (ownership.getCardNum() == cardNum && ownership.isCurrent()) {
                CreditCard temp = cardCollection.getCard(ownership.getCardNum());
                if (temp != null) {
                    card = temp;
                    customerIds.add(ownership.getCustomerId());
                }
            }
        }
        if (card == null) {
            System.out.println("Card not found with number " + cardNum);
            return;
        }
        System.out.println("Card Info: Balance = $" + String.format(formatBalance, card.getBalance()) +
                " Limit = $" + card.getLimit() +
                " Holders = " + customerIds);

    }


}
