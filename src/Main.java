public class Main {
    public void run()
    {
        System.out.println("-----Credit Card Database-----");
        // Create Customers
        Customer abby = new Customer("44", "Abby", "123 Lane", "408321232");
        Customer dustin = new Customer("92", "Dustin", "125 Lane", "4214242");
        CustomerCollection coll = new CustomerCollection();
        coll.addCustomer(abby);
        coll.addCustomer(dustin);
        System.out.println("Locate customer with ID of 0: " + coll.getById(0));
        System.out.println("Locate customer with SSN of 92: " + coll.getBySSN("92"));
        System.out.println();

        // Create new credit card for owner Abby
        CreditCard card1 = new CreditCard(1234567, 5000, 0);
        CreditCard card2 = new CreditCard(1111111, 1000, 0);
        CreditCardCollection cards = new CreditCardCollection();
        cards.addCard(card1);
        cards.addCard(card2);
        OwnershipCollection owns = new OwnershipCollection(coll, cards);
        owns.createOwnership(abby, card1);
        owns.createOwnership(abby, card2);

        System.out.println("Creating credit card for user.");
        owns.printCreditCardByCustomerId(0);
        System.out.println();

        //dual ownership
        System.out.println("Issuing credit card duplicate.");
        owns.createOwnership(dustin, card1);
        //print credit cards by SSN
        System.out.println("printing credit card by customer ssn " + dustin.getSSN());
        owns.printCreditCardByCustomerSSN("92");
        System.out.println();

        // Activate a credit card
        System.out.println("Activating cards.");
        card1.setActive(true);
        card2.setActive(true);
        owns.printCreditCardByCustomerId(0);
        System.out.println();

        // Cancel a credit card
        System.out.println("Cancelling card 111111 for user 0.");
        owns.deactivateOwnership(0, 1111111);
        owns.printCreditCardByCustomerId(0);
        System.out.println();

        // Add a new vendor
        Vender macys = new Vender("Macys", "Newark");
        VenderCollection venders = new VenderCollection();
        venders.addVender(macys);
        System.out.println("Adding a new vendor!");
        venders.printVenders(); // just to see vender was created
        System.out.println();

        // Create a new transaction
        Transaction tra1 = new Transaction("2025-01-01", 0, 1234567, 0);

        TransactionCollection transactions = new TransactionCollection(cards);
        transactions.addTransaction(tra1, 99.99);
        System.out.println("Creating a new transaction of $99.99 from user 0 on card 1234567");
        System.out.print("\t");
        owns.printCreditCardByCustomerId(0); // see card status by printing Cards

        System.out.println("Printing transactions of 1234567 between 2024-12-12 and 2025-02-02");
        System.out.print("\t");
        transactions.printTransactionsBetweenDates(1234567, "2024-12-12", "2025-02-02");

        Transaction tra2 = new Transaction("2025-04-01", 1, 1234567, 0);
        System.out.println("Creating a new transaction of $20.00 from user 1 on card 1234567.");
        transactions.addTransaction(tra2, 20);
        System.out.print("\t");
        owns.printCreditCardByCustomerId(0);

        System.out.println("Printing transactions of 1234567 between 2024-12-12 and 2025-02-02");
        System.out.print("\t");
        transactions.printTransactionsBetweenDates(1234567, "2024-12-12", "2025-02-02");

        System.out.println("Printing transactions of 1234567 between 2024-12-12 and 2025-04-02");
        System.out.print("\t");
        transactions.printTransactionsBetweenDates(1234567, "2024-12-12", "2025-04-02");

        System.out.println();
        // Make a payment to card 1234567
        Payment pay1 = new Payment("2025-01-12", 1234567);
        PaymentCollection payments = new PaymentCollection(cards);
        payments.addPayment(pay1, 50.0);
        System.out.println("After payment of $50.00");
        owns.printCreditCardByCustomerId(0);
    }
    public static void main(String[] args) {
        Main caleb = new Main();
        caleb.run();

//        System.out.println(owns.getByCustomer("44").);
    }
}