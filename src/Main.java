public class Main {
    public static void main(String[] args) {
        System.out.println("Credit Card Database");
        Customer abby = new Customer("44", "Abby", "123 Lane", "408321232");
        Customer dustin = new Customer("45", "Dustin", "125 Lane", "4214242");
        CustomerCollection coll = new CustomerCollection();
        coll.addCustomer(abby);
        coll.addCustomer(dustin);
        CreditCard card1 = new CreditCard(1234567, 5000, 0);
        CreditCardCollection cards = new CreditCardCollection();
        cards.addCard(card1);
        OwnershipCollection owns = new OwnershipCollection();
        owns.createOwnership(abby, card1);

        System.out.println(owns.getByCustomer(coll.getCustomer(0).getID()));
    }
}