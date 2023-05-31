package Engine;

import Engine.Entity.Items.Item;
import Engine.Entity.Items.Type;
import Logs.Logger;

import java.util.HashMap;
import java.util.List;

public class Inventory {
    private final HashMap<Type, Integer> inventory;

    public Inventory() {
        inventory = new HashMap<>();
    }

    public Inventory(List<Item> items) {
        this();
        items.forEach(item -> {
            inventory.put(item.getType(), item.getAmount());
        });
    }

    public void use(Type type) {
        int amountInInventory = inventory.get(type);
        if (amountInInventory > 0) {
            decrease(type, amountInInventory - 1);
            Logger.log("1 " + type.name() + " used. " + (amountInInventory - 1) + " remains.");
        }
    }

    public int getAmount(Type type) {
        return inventory.get(type) == null ? 0 : inventory.get(type);
    }

    public void add(Type type, int amount) {
        int amountInInventory = inventory.get(type) == null ? 0 : inventory.get(type);
        inventory.put(type, amountInInventory + amount);
    }

    private void decrease(Type type, int newAmount) {
        inventory.replace(type, newAmount);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Player's inventory.txt:\n\t" + inventory.size() + " items:\n");
        inventory.forEach((key, value) -> {
            sb.append("\t\t").append(key.name()).append(" AMOUNT: ").append(value);
        });
        return sb.toString();
    }

    public HashMap<Type, Integer> getInventory() {
        return inventory;
    }
}
