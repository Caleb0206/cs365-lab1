import java.util.HashMap;

public class CustomerCollection {
    static int id_counter = 0;
    HashMap<Integer, Customer> customers = new HashMap<>();
    public void addCustomer(Customer buyer)
    {
        buyer.setId(id_counter++);
        customers.put(buyer.getID(), buyer);
    }
    public Customer getById(int id)
    {
        return customers.get(id);
    }
    public Customer getBySSN(String ssn)
    {
        for(Customer customer : customers.values())
        {
            if(customer.getSSN().equals(ssn))
                return customer;
        }
        return null;
    }


}
