public class Customer {
    private String ssn;
    private int id;
    private String name;
    private String address;
    private String phone;

    public Customer(String ssn, String name, String address, String phone)
    {
        this.ssn = ssn;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
    /** sets the ID of customer */
    public void setId(int id) {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }
    public int getID()
    {
        return id;
    }
    public String getSSN()
    {
        return ssn;
    }
    public String getAddress()
    {
        return address;
    }
    public String getPhone()
    {
        return phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ssn='" + ssn + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
