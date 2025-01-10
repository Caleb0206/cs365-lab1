public class Main {
    public static void main(String[] args) {
        Main caleb = new Main();
        caleb.run();
    }

    public void run() {
        System.out.println("-----Credit Card Database-----");

        // Create Customers
        CustomerCollection coll = new CustomerCollection();
        coll.createCustomer("44", "Abby", "123 Lane", "408321232");
        coll.createCustomer("92", "Dustin", "125 Lane", "4081230000");

        // Locate customers
        System.out.println("Locate customer with ID of 0: " + coll.getById(0));
        System.out.println("Locate customer with SSN of 92: " + coll.getBySSN("92"));
        System.out.println();

        // Create new credit card for owner Abby

        CreditCardCollection cards = new CreditCardCollection();
        cards.createCard(1234567, CreditCard.CardType.Visa, 5000, 0);
        cards.createCard(1111111, CreditCard.CardType.MC, 1000, 0);
        OwnershipCollection owns = new OwnershipCollection(coll, cards);
        owns.createOwnership(coll.getById(0), cards.getCard(1234567));
        owns.createOwnership(coll.getById(0), cards.getCard(1111111));

        System.out.println("Creating credit cards for user 0.");
        owns.printCreditCardByCustomerId(0);
        System.out.println();

        //dual ownership
        System.out.println("Issuing credit card duplicate.");
        owns.createOwnership(coll.getById(1), cards.getCard(1234567));
        //print credit cards by SSN
        System.out.println("Printing credit card by customer ssn " + coll.getById(1).getSSN());
        owns.printCreditCardByCustomerSSN("92");

        System.out.println("\nPrinting credit card info for number 1234567");
        owns.printByCard(1234567);
        System.out.println();

        // Activate a credit card
        System.out.println("Activating cards.");
        cards.getCard(1234567).setActive(true);
        cards.getCard(1111111).setActive(true);
        System.out.println("\nCards owned by customer 0");
        owns.printCreditCardByCustomerId(0);
        System.out.println();

        // Cancel a credit card
        System.out.println("Cancelling card 111111 for user 0.");
        owns.deactivateOwnership(0, 1111111);
        owns.printCreditCardByCustomerId(0);
        System.out.println();

        // Add a new vendor
        VenderCollection venders = new VenderCollection();
        venders.createVender("Macys", "123 Main St., Seattle, Washington");
        System.out.println("Adding a new vendor!");
        venders.printVenders(); // just to see vender was created
        System.out.println();

        // Create a new transaction
        TransactionCollection transactions = new TransactionCollection(cards);
        transactions.createTransaction("2025-01-01", 0, 1234567, 0, 99.99);

        System.out.println("2025-01-01: Creating a new transaction of $99.99 from user 0 on card 1234567");
        System.out.print("\t");
        owns.printByCard(1234567);
//        owns.printCreditCardByCustomerId(0); // see card status by printing Cards
        System.out.println();

        System.out.println("Printing transactions of 1234567 between 2024-12-12 and 2025-02-02");
        transactions.printTransactionsBetweenDates(1234567, "2024-12-12", "2025-02-02");
        System.out.println();

        transactions.createTransaction("2025-04-01", 1, 1234567, 0, 20.0);
        System.out.println("2025-04-01: Creating a new transaction of $20.00 from user 1 on card 1234567.");
        System.out.print("\t");
        owns.printByCard(1234567);
//        owns.printCreditCardByCustomerId(0);
        System.out.println();

        System.out.println("Printing transactions of 1234567 between 2024-12-12 and 2025-02-02");
//        System.out.print("\t");
        transactions.printTransactionsBetweenDates(1234567, "2024-12-12", "2025-02-02");
        System.out.println();

        System.out.println("Printing transactions of 1234567 between 2024-12-12 and 2025-04-01");
//        System.out.print("\t");
        transactions.printTransactionsBetweenDates(1234567, "2024-12-12", "2025-04-01");
        System.out.println();
        // Make a payment to card 1234567
        Payment pay1 = new Payment("2025-01-12", 1234567);
        PaymentCollection payments = new PaymentCollection(cards);
        payments.addPayment(pay1, 50.0);
        System.out.println("After payment of $50.00");
//        owns.printCreditCardByCustomerId(0);
        owns.printByCard(1234567);

        System.out.println();
    }
}