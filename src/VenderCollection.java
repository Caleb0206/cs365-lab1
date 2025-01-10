import java.util.HashMap;

public class VenderCollection {
    static int id_counter = 0;
    private HashMap<Integer, Vender> venders;

    public VenderCollection() {
        venders = new HashMap<>();
    }

    public void createVender(String name, String location) {
        Vender shop = new Vender(name, location);
        addVender(shop);
    }

    public void addVender(Vender shop) {
        shop.setId(id_counter++);
        venders.put(shop.getId(), shop);
    }

    public void printVenders() {
        for (Vender v : venders.values()) {
            System.out.println(v);
        }
    }
}
