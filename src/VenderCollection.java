import java.util.HashMap;

public class VenderCollection {
    static int id_counter = 0;
    HashMap<Integer, Vender> venders = new HashMap<>();
    public void addVender(Vender shop)
    {
        shop.setId(id_counter++);
        venders.put(shop.getId(), shop);
    }
}
