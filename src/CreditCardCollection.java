import java.util.HashMap;

public class CreditCardCollection {
    private HashMap<Integer, CreditCard> cards;

    public CreditCardCollection() {
        cards = new HashMap<>();
    }

    public void createCard(int number, CreditCard.CardType type, int limit, double balance) {
        CreditCard card = new CreditCard(number, type, limit, balance);
        addCard(card);
    }

    public void addCard(CreditCard card) {
        cards.put(card.getNumber(), card);
    }

    public CreditCard getCard(int num) {
        if (cards.containsKey(num))
            return cards.get(num);
        return null;
    }

}
