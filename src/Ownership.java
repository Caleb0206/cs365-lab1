import java.util.Objects;

public class Ownership {
    private int customerId;
    private int cardNum;
    private boolean isCurrent;

    public Ownership(int customerId, int cardNum, boolean isCurrent) {
        this.customerId = customerId;
        this.cardNum = cardNum;
        this.isCurrent = isCurrent;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getCardNum() {
        return cardNum;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ownership ownership = (Ownership) o;
        return customerId == ownership.customerId && cardNum == ownership.cardNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, cardNum);
    }

    @Override
    public String toString() {
        return "Ownership{" +
                "customerId=" + customerId +
                ", cardNum=" + cardNum +
                ", isCurrent=" + isCurrent +
                '}';
    }
}
