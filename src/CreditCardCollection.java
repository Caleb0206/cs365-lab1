import java.util.HashMap;

public class CreditCardCollection {
    HashMap<Integer, CreditCard> cards = new HashMap<>();

    public void addCard(CreditCard card)
    {
        cards.put(card.getNumber(), card);
    }
    public CreditCard getCatd(int num)
    {
        if(cards.containsKey(num))
            return cards.get(num);
        return null;
    }

}
