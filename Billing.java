package edu.sjsu.mdani;

import edu.sjsu.mdani.entity.Item;
import edu.sjsu.mdani.entity.Order;
import edu.sjsu.mdani.exception.InvalidOrder;
import edu.sjsu.mdani.writer.ErrorOutputStrategy;
import edu.sjsu.mdani.writer.ResultWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Billing {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        ResultWriter resultWriter = new ResultWriter(); // default strategy is success
        InMemoryStorage.getInstance();
        String again = "n";

        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter order file path: ");
            String orderFile = sc.next();

            System.out.println("Reading data from " + orderFile);
            int rowCounter = 0;
            Scanner sc1 = new Scanner(new File(orderFile));
            sc1.useDelimiter("\n");
            Order.OrderBuilder orderBuilder = null;
            Order order = null;
            try {
                while (sc1.hasNext()) {
                    rowCounter++;
                    if (rowCounter == 1) {
                        sc1.next();
                        continue;
                    }

                    String[] row = sc1.next().split(",");
                    if (rowCounter == 2) {
                        try {
                            orderBuilder = new Order.OrderBuilder(Float.valueOf(row[2]));
                        } catch (Exception ex) {
                            System.out.println("Error while reading the card number; It is not present in 2nd row 3rd column. As 1st row will be headers");
                            throw new InvalidOrder("Error while reading the card number; It is not present in 2nd row 3rd column. As 1st row will be headers");
                        }
                    }

                    orderBuilder.item(row[0], Integer.valueOf(row[1]));
                }
                sc1.close();

                //This will throw exception if its invalid
                order = orderBuilder.build();

            } catch (InvalidOrder ex) {
                //write text to file
                String errorFilePath = orderFile.substring(0, orderFile.lastIndexOf(".csv")) + "-output.txt";
                resultWriter.changeStrategy(new ErrorOutputStrategy()); // change strategy to error
                resultWriter.write(ex.getMessage(), errorFilePath);
                System.out.print("Do you wish to continue? (y|n) ");
                again = sc.next();
                continue;
//                return;
            }

            //processing order
            int cost = 0;
            //calculate price
            Map<Map.Entry<String, Integer>, Integer> costMapPerItem = new HashMap<>();
            for (Map.Entry<String, Integer> eachOrderItem : order.getItemQuantity().entrySet()) {
                Item itemDetails = InMemoryStorage.getInstance().items.get(eachOrderItem.getKey().toLowerCase());
                cost = cost + (itemDetails.getPrice() * eachOrderItem.getValue());

                //Call by reference so it should reflect in in-memory store
                itemDetails.setAvailableQuantity(itemDetails.getAvailableQuantity() - eachOrderItem.getValue());

                //Get value for putting in final o/p file
                costMapPerItem.put(eachOrderItem, (itemDetails.getPrice() * eachOrderItem.getValue()));
            }

            //write o/p file
            String outputFilePath = orderFile.substring(0, orderFile.lastIndexOf(".csv")) + "-output.csv";
            String message = "Item,Quantity,Price,TotalPrice\n";

            boolean totalPriceAdded = false;
            for (Map.Entry<Map.Entry<String, Integer>, Integer> each : costMapPerItem.entrySet()) {
                message = message + each.getKey().getKey() + "," + each.getKey().getValue() + "," + each.getValue() + "," + (totalPriceAdded ? "" : cost) + "\n";
                totalPriceAdded = true;
            }
            resultWriter.write(message, outputFilePath); // no need to change strategy as its success by default
            System.out.print("Do you wish to continue? (y|n) ");
            again = sc.next();
            continue;
        } while (again.equalsIgnoreCase("y"));
    }
}
