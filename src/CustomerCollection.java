import java.util.HashMap;

public class CustomerCollection {
    static int id_counter = 0;
    private HashMap<Integer, Customer> customersByID;
    private HashMap<String, Customer> customersBySSN;

    public CustomerCollection() {
        customersByID = new HashMap<>();
        customersBySSN = new HashMap<>();
    }

    public void createCustomer(String ssn, String name, String address, String phone) {
        Customer buyer = new Customer(ssn, name, address, phone);
        addCustomer(buyer);
    }

    public void addCustomer(Customer buyer) {
        buyer.setId(id_counter++);
        customersByID.put(buyer.getID(), buyer);
        customersBySSN.put(buyer.getSSN(), buyer);
    }

    public Customer getById(int id) {
        return customersByID.get(id);
    }

    //change
    public Customer getBySSN(String ssn) {
        return customersBySSN.get(ssn);
    }


}
