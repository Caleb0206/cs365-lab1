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


        OwnershipCollection owns = new OwnershipCollection(coll, cards);
        owns.createOwnership(abby, card1);
        //dual ownership
        owns.createOwnership(dustin, card1);
        card1.setActive(true);


        owns.printCreditCardByCustomerId(0);
        Vender macys = new Vender("Macys", "Newark");
        VenderCollection venders = new VenderCollection();
        venders.addVender(macys);

        Transaction tra = new Transaction("2025-01-01", 0, 1234567, 0);
        TransactionCollection transactions = new TransactionCollection(cards);
        transactions.addTransaction(tra, 99.99);
        transactions.printTransactionsBetweenDates(1234567, "2024-12-12", "2025-02-02");

//        System.out.println(owns.getByCustomer("44").);
    }
}