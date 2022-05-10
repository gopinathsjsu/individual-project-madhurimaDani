package edu.sjsu.mdani.entity;

import edu.sjsu.mdani.InMemoryStorage;
import edu.sjsu.mdani.exception.InvalidOrder;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Order {
    Map<String, Integer> itemQuantity;
    Float card;

    public Order(OrderBuilder builder) {
        this.itemQuantity = builder.itemQuantity;
        this.card = builder.card;
    }

    public Map<String, Integer> getItemQuantity() {
        return itemQuantity;
    }

    public Float getCard() {
        return card;
    }

    public static class OrderBuilder {
        Map<String, Integer> itemQuantity;
        Float card;

        public OrderBuilder(Float card) {
            this.card = card;
            itemQuantity = new HashMap<>();
        }

        public OrderBuilder item(String item, int quantity) {
            itemQuantity.put(item, quantity);
            return this;
        }

        //Return the finally consrcuted User object
        public Order build() throws FileNotFoundException, InvalidOrder {
            Order order =  new Order(this);
            validateOrder(order);
            return order;
        }
        private void validateOrder(Order order) throws FileNotFoundException, InvalidOrder {
            String errorMessage = "";
            if(order.card == null) {
                errorMessage = errorMessage + "No card number found;";
            } else {
                if(InMemoryStorage.getInstance().cards.contains(card)) {
                    System.out.println("Card already exist in memory; No need to add new");
                } else {
                    System.out.println("Card does not exist in memory; Adding new entry :" + card);
                    InMemoryStorage.getInstance().cards.add(card);
                }
            }

            for(Map.Entry<String, Integer> each : order.itemQuantity.entrySet()) {
                if(InMemoryStorage.getInstance().items.get(each.getKey().toLowerCase()) == null) {
                    System.out.println("Invalid Item Found: " + each.getKey());
                    errorMessage = errorMessage + "Invalid Item Found: " + each.getKey() +";\n";
                } else if ((InMemoryStorage.getInstance().items.get(each.getKey().toLowerCase()).category.equals("Essentials") &&  each.getValue() >3)
                        || (InMemoryStorage.getInstance().items.get(each.getKey().toLowerCase()).category.equals("Luxury") &&  each.getValue() >4)
                        || (InMemoryStorage.getInstance().items.get(each.getKey().toLowerCase()).category.equals("Misc") &&  each.getValue() >6)) {

                    System.out.println("Item: " + each.getKey() + " is ordered more than permitted quantity;");
                    errorMessage = errorMessage + "Item: " + each.getKey() + " is ordered more than permitted quantity;\n";

                } else if( each.getValue() > InMemoryStorage.getInstance().items.get(each.getKey().toLowerCase()).availableQuantity){
                    System.out.println("Item not available in required quantity in inventory: " + each.getKey());
                    errorMessage = errorMessage + "Item not available in required quantity in inventory: " + each.getKey() +";\n";
                }
            }

            if(!errorMessage.equals("")) {
                throw new InvalidOrder(errorMessage);
            }
        }
    }
}