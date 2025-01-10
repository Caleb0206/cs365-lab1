public class CreditCard {
    public enum CardType {
        Visa,
        MC,
        American_Express,
        Discover
    }

    private int number;
    private CardType type;
    private int limit;
    private double balance;
    private boolean active;

    public CreditCard(int number, CardType type, int limit, double balance) {
        this.number = number;
        this.type = type;
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

    public int getLimit() {
        return limit;
    }

    public CardType getType() {
        return type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        String formatBalance = "$%.2f";
        return "CreditCard " + number +
                ", type=" + type +
                ", limit = $" + limit +
                ", balance = " + String.format(formatBalance, balance) +
                ", active = " + active;
    }
}
