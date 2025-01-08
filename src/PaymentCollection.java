import java.util.ArrayList;

public class PaymentCollection {
    private ArrayList<Payment> payments;
    private CreditCardCollection cardCollection;

    public PaymentCollection(CreditCardCollection cardCollection) {
        payments = new ArrayList<>();
        this.cardCollection = cardCollection;
    }

    public void addPayment(Payment pay, double amount)
    {
        payments.add(pay);
        CreditCard card = cardCollection.getCard(pay.getCardNum());
        card.setBalance(card.getBalance() - amount);
    }
}
