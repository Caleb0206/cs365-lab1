public class Vender {
    private int id;
    private String name;
    private String location;

    public Vender(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
