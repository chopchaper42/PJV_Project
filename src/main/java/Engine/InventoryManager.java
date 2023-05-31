package Engine;

import Engine.Entity.Items.*;
import Logs.Logger;

import java.io.*;
import java.util.HashMap;

public class InventoryManager {

    private static Inventory inventory = new Inventory();
    private static String inventoryPath = "./src/main/inventory/inventory.txt";

    public static void readInventory() {
        try {
            File file = new File(inventoryPath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            parseInventory(reader);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Logger.log(e.getMessage());
            inventory = new Inventory();
        }
    }

    private static void parseInventory(BufferedReader reader) throws IOException {
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            String[] parts = currentLine.split(" ");
            if (parts.length != 2) {
                continue;
            }
            try {
                switch (parts[0]) {
                    case "KEY" -> inventory.add(Type.KEY, Integer.parseInt(parts[1]));
                    case "AMMO" -> inventory.add(Type.AMMO, Integer.parseInt(parts[1]));
                    default -> {}
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
    }

    public static void saveInventory() {
        File file = new File(inventoryPath);
        try (PrintWriter writer = new PrintWriter(file)) {
            inventory.getInventory().forEach((type, amount) -> {
                writer.println(type.name() + " " + amount);
            });
        } catch (IOException e) {
            Logger.log("Unable to save inventory");
        }
    }

    public static Inventory getInventory() {
        if (inventory == null)
            readInventory();
        return inventory;
    }
}
