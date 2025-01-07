import java.util.HashSet;
import java.util.Set;

public class OwnershipCollection {
    HashSet<Ownership> ownerships = new HashSet<>();

    public void createOwnership(Customer person, CreditCard card)
    {
        ownerships.add(new Ownership(person.getID(), card.getNumber(), true));
    }
    public void deactivateOwnership(int customerId, int cardNum)
    {
        Ownership cancelled = new Ownership(customerId, cardNum, true);
        if(ownerships.contains(cancelled))
        {
            ownerships.remove(cancelled);
            cancelled.setCurrent(false);
            ownerships.add(cancelled); // add the inactive ownership back to set
        }
    }
    public Set<Ownership> getByCustomer(int customerId)
    {
        Set<Ownership> queried = new HashSet<>();
        for (Ownership ownership : ownerships)
        {
            if(ownership.getCustomerId() == customerId && ownership.isCurrent())
                queried.add(ownership);
        }
        return queried;
    }
    public Set<Ownership> getByCard(int cardNum)
    {
        Set<Ownership> queried = new HashSet<>();
        for (Ownership ownership : ownerships)
        {
            if(ownership.getCardNum() == cardNum && ownership.isCurrent())
                queried.add(ownership);
        }
        return queried;
    }
}
