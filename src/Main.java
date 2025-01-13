import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main caleb = new Main();
        caleb.run();
//        caleb.loop();
    }

    public void loop() {
        CustomerCollection customers = new CustomerCollection();
        CreditCardCollection cards = new CreditCardCollection();
        VenderCollection venders = new VenderCollection();
        OwnershipCollection owns = new OwnershipCollection(customers, cards);
        TransactionCollection transactions = new TransactionCollection(cards);
        /*
        String input = "";
        while (!input.equalsIgnoreCase("q")) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter a function 1-8, 'q' to quit, and a-d for query.");
            System.out.println(
                    "1. Create a new customer.\n" +
                            "2. Create a new credit card for an existing customer (will affect both the credit card and ownership data). Initially, the credit card will not be active.\n" +
                            "3. Issue a credit card duplicate for additional customer (will affect only the ownership data)\n" +
                            "4. Cancel a credit card.\n" +
                            "5. Active a credit card.\n" +
                            "6. Add a new vendor.\n" +
                            "7. Create a new transaction. This will affect the balance of the credit card.\n" +
                            "8. Allow a customer to pay off credit card. This will affect both the payment data and credit card balance.\n" +
                            "\nQueries:" +
                            "a. Locate customer by ID or SSN.\n" +
                            "b. For a given customer (specified ID or SSN), print credit card information (i.e., credit card number, credit limit, and balance for each credit card).\n" +
                            "c. For a given credit card (specified by CC number), print credit card information (e.g., balance, credit limit, card holders).\n" +
                            "d. For a given credit card, print all transactions that are in a specified date range.\n"
            );
            input = sc.nextLine();
            switch (input) {
                case "1":
                    System.out.println("Create a new customer: SSN, name, address, phone (Separate by spaces)");
                    String[] words = sc.nextLine().split(" ");
                    customers.createCustomer(words[0], words[1], words[2], words[3]);
                    break;
                case "2":
                    System.out.println("Create a credit card for a customer: customer ID, ");
            }

        }
        */
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nMenu:");
            System.out.println("1. Create a new customer");
            System.out.println("2. Create a new credit card for an existing customer");
            System.out.println("3. Issue a credit card duplicate for an additional customer");
            System.out.println("4. Cancel a credit card");
            System.out.println("5. Activate a credit card");
            System.out.println("6. Add a new vendor");
            System.out.println("7. Create a new transaction");
            System.out.println("8. Pay off a credit card");
            System.out.println("9. Locate customer by ID or SSN");
            System.out.println("10. Print credit card information for a customer");
            System.out.println("11. Print credit card information for a specific card");
            System.out.println("12. Print transactions for a credit card within a date range");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter SSN: ");
                    String ssn = scanner.nextLine();
                    customers.addCustomer(new Customer(ssn, name, address, phone));
                    System.out.println("Customer created successfully.");
                    break;
                }
                case 2 -> {
                    CreditCard.CardType cardType = null;
                    System.out.print("Enter customer ID: ");
                    int customerId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter credit card number: ");
                    int number = scanner.nextInt();
                    scanner.nextLine(); // consumes leftover line
                    System.out.print("Enter credit card type (Visa, MC, American Express, Discover): ");
                    String type = scanner.nextLine();

                    System.out.print("Enter credit card limit: ");
                    int limit = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double balance = scanner.nextDouble();
                    scanner.nextLine();

                    if (type.equalsIgnoreCase("Visa"))
                        cardType = CreditCard.CardType.Visa;
                    else if (type.equalsIgnoreCase("MC"))
                        cardType = CreditCard.CardType.MC;
                    else if (type.equalsIgnoreCase("American Express"))
                        cardType = CreditCard.CardType.American_Express;
                    else if (type.equalsIgnoreCase("Discover"))
                        cardType = CreditCard.CardType.Discover;

                    Customer customer = customers.getById(customerId);
                    if (customer != null) {
                        CreditCard card = new CreditCard(number, cardType, limit, balance);
                        cards.addCard(card);
                        owns.createOwnership(customer, card);
                        System.out.println("Credit card created and associated with customer.");
                    } else {
                        System.out.println("Customer not found.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter original card number: ");
                    int cardNum = scanner.nextInt();
                    System.out.print("Enter additional customer ID: ");
                    int customerId = scanner.nextInt();

                    CreditCard card = cards.getCard(cardNum);
                    Customer customer = customers.getById(customerId);

                    if (card != null && customer != null) {
                        owns.createOwnership(customer, card);
                        System.out.println("Duplicate card issued successfully.");
                    } else {
                        System.out.println("Invalid card or customer ID.");
                    }
                }
                case 4 -> {
                    System.out.print("Enter card number: ");
                    int cardNum = scanner.nextInt();
                    System.out.print("Enter customer ID: ");
                    int customerId = scanner.nextInt();

                    owns.deactivateOwnership(customerId, cardNum);
                    System.out.println("Credit card canceled successfully.");
                }
                case 5 -> {
                    System.out.print("Enter card number: ");
                    int cardNum = scanner.nextInt();

                    CreditCard card = cards.getCard(cardNum);
                    if (card != null) {
                        card.setActive(true);
                        System.out.println("Credit card activated.");
                    } else {
                        System.out.println("Card not found.");
                    }
                }
                case 6 -> {
                    System.out.print("Enter vendor name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter vendor location: ");
                    String location = scanner.nextLine();

                    venders.addVender(new Vender(name, location));
                    System.out.println("Vendor added successfully.");
                }
                case 7 -> {
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter customer ID: ");
                    int customerId = scanner.nextInt();
                    System.out.print("Enter card number: ");
                    int cardNum = scanner.nextInt();
                    System.out.print("Enter vendor ID: ");
                    int vendorId = scanner.nextInt();
                    System.out.print("Enter transaction cost: ");
                    double cost = scanner.nextDouble();

                    transactions.createTransaction(date, customerId, cardNum, vendorId, cost);
                }
                case 8 -> {
                    System.out.print("Enter card number: ");
                    int cardNum = scanner.nextInt();
                    System.out.print("Enter payment amount: ");
                    double amount = scanner.nextDouble();

                    CreditCard card = cards.getCard(cardNum);
                    if (card != null) {
                        card.setBalance(card.getBalance() - amount);
                        System.out.println("Payment successful. Remaining balance: " + card.getBalance());
                    } else {
                        System.out.println("Card not found.");
                    }
                }
                case 9 -> {
                    System.out.print("Enter ID or SSN: ");
                    String input = scanner.nextLine();

                    Customer customer;
                    customer = customers.getById(Integer.parseInt(input));
                    System.out.println("*** " + customer);
                    if (customer == null) {
                        customer = customers.getBySSN(input);
                    }

                    if (customer != null) {
                        System.out.println(customer);
                    } else {
                        System.out.println("Customer not found.");
                    }
                }
                case 10 -> {
                    System.out.print("Enter customer ID(1) or SSN(2): ");
                    String input = scanner.nextLine();
                    if (input.equals("1")) {
                        System.out.print("Enter customer ID: ");
                        input = scanner.nextLine();

                        owns.printCreditCardByCustomerId(Integer.parseInt(input));
                    } else if (input.equals("2")) {
                        System.out.print("Enter customer SSN: ");
                        input = scanner.nextLine();
                        owns.printCreditCardByCustomerSSN(input);
                    }
                }
                case 11 -> {
                    System.out.print("Enter credit card number: ");
                    int cardNum = scanner.nextInt();

                    owns.printByCard(cardNum);
                }
                case 12 -> {
                    System.out.print("Enter card number: ");
                    int cardNum = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter start date (YYYY-MM-DD): ");
                    String start = scanner.nextLine();
                    System.out.print("Enter end date (YYYY-MM-DD): ");
                    String end = scanner.nextLine();

                    transactions.printTransactionsBetweenDates(cardNum, start, end);
                    break;
                }
                case 0 -> {
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
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
        System.out.println("Issuing credit card duplicates to customer 1 with cards 1234567, 1111111.");
        owns.createOwnership(coll.getById(1), cards.getCard(1234567));
        owns.createOwnership(coll.getById(1), cards.getCard(1111111));
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
        System.out.println("Cards owned by Customer 0: ");
        System.out.print("\t");
        owns.printCreditCardByCustomerId(0);
        System.out.print("\t");
        owns.printByCard(1111111);
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