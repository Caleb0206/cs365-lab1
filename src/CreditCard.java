public class CreditCard {
    private int number;
    private enum cardType {
        Visa,
        MC,
        American_Express,
        Discover
    }
    private int limit;
    private double balance;
    private boolean active;

    public CreditCard(int number, int limit, double balance) {
        this.number = number;
        this.limit = limit;
        this.balance = balance;
        active = false;
    }

    public double getBalance() {
        return balance;
    }

    public int getNumber() {
        return number;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
