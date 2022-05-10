package edu.sjsu.mdani;

import edu.sjsu.mdani.entity.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStorage {
    private static InMemoryStorage instance;

    public synchronized static InMemoryStorage getInstance() throws FileNotFoundException {
        if(instance == null ){
            instance = new InMemoryStorage();
        }
        return instance;
    }

    private InMemoryStorage() throws FileNotFoundException {
        items = new HashMap<>();
        initInventoryData();

        cards = new HashSet<>();
        initCardData();
    }

    private void initInventoryData() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("inventory.csv"));
        sc.useDelimiter("\n");
        while (sc.hasNext()) {
            String tmp = sc.next();
            String[] each = tmp.split(",");
            items.put(each[0].toLowerCase(), new Item(each[1], each[0].toLowerCase(), Integer.valueOf(each[2]), Integer.valueOf(each[3])));
        }
        sc.close();

        System.out.println("Inventory Data Loaded!");

        for (Map.Entry<String,Item> each : items.entrySet()) {
            System.out.println(each.getValue());
        }

    }

    private void initCardData() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("cards.csv"));
        sc.useDelimiter("\n");
        while (sc.hasNext()) {
            Float tmp = sc.nextFloat();
            cards.add(tmp);
        }
        sc.close();

        System.out.println("Card Data Loaded!");

        for (Float each : cards) {
            System.out.println(each);
        }
    }




    public Map<String, Item> items;
    public Set<Float> cards;
}

