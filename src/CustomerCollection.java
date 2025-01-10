import java.util.HashMap;

public class CustomerCollection {
    static int id_counter = 0;
    private HashMap<Integer, Customer> customers;

    public CustomerCollection() {
        customers = new HashMap<>();
    }

    public void createCustomer(String ssn, String name, String address, String phone) {
        Customer buyer = new Customer(ssn, name, address, phone);
        addCustomer(buyer);
    }

    public void addCustomer(Customer buyer) {
        buyer.setId(id_counter++);
        customers.put(buyer.getID(), buyer);
    }

    public Customer getById(int id) {
        return customers.get(id);
    }

    public Customer getBySSN(String ssn) {
        for (Customer customer : customers.values()) {
            if (customer.getSSN().equals(ssn))
                return customer;
        }
        return null;
    }


}
