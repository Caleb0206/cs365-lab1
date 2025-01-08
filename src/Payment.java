public class Payment {
    private String date;
    private int cardNum;

    public Payment(String date, int cardNum) {
        this.date = date;
        this.cardNum = cardNum;
    }

    public String getDate() {
        return date;
    }

    public int getCardNum() {
        return cardNum;
    }
}
