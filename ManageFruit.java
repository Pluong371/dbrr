/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit_0023;

import java.util.ArrayList;
import jdk.nashorn.internal.ir.BreakNode;

/**
 *
 * @author admin
 */
public class ManageFruit {

    ArrayList<Fruit> fruitList = new ArrayList<>();
    ArrayList<Invoice> invoiceList = new ArrayList<>();
    Utility utility = new Utility();

    void createfruit() {
        //enter attribute

        while (true) {
            String id, name;
            double price;
            int quantity;
            String origin;

            while (true) {
                id = Utility.getString("Enter id: ", "Wrong", utility.REGEX_STRING);
                if (checkDuplicate(id)) {
                    System.out.println("Duplicate!!");

                } else {
                    break;
                }
            }
            name = Utility.getString("Enter name: ", "Wrong", utility.REGEX_STRING);
            price = Utility.getDouble("Enter price: ", "Wrong", 0, Double.MAX_VALUE);
            quantity = Utility.getInteger("Enter quantity: ", "Wrong", 0, Integer.MAX_VALUE);
            origin = Utility.getString("Enter origin: ", "Wrong", utility.REGEX_STRING);

            Fruit fruit = new Fruit(id, name, price, quantity, origin);
            fruitList.add(fruit);

            if (!checkYesNo("continue")) {
                break;
            }
        }
        displayListFruit(fruitList);

    }

    private boolean checkDuplicate(String id) {
        for (Fruit fruit : fruitList) {
            if (fruit.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkYesNo(String message) {
        String result = utility.getString("Do you want to " + message + " (Y/N)?", "Wrong", utility.REGEX_YN);
        if (result.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }
    }

    private void displayListFruit(ArrayList<Fruit> fruitList) {
        System.out.format("%-15s %-15s %-15s %-15s %-15s\n", "Item", "Name", "Price", "Quantity", "Origin");
        for (Fruit fruit : fruitList) {
            System.out.println(fruit);
        }
    }

    void shopping() {
        if (fruitList.isEmpty()) {
            System.out.println("Empty shop!!");
            return;
        }
        Invoice invoice = new Invoice();
        while (true) {

            displayListFruit(fruitList);

            //enter item and display item
            Fruit fruitChoice;
            while (true) {
                String item = Utility.getString("Enter item: ", "Wrong", utility.REGEX_STRING);

                fruitChoice = getFruitById(item);
                // if fruitchoice = null => enter wrong item
                //display name of item
                if (fruitChoice == null) {
                    System.out.println("Wrong item, choose agian!!");
                } else {
                    System.out.println("Your selected: " + fruitChoice.getName());
                    break;
                }
            }
            //enter quantity
            int quantityOrder = Utility.getInteger("Enter quantity: ", "Wrong", 0,
                    fruitChoice.getQuantity());
            //set quantity remaining
            fruitChoice.setQuantity(fruitChoice.getQuantity() - quantityOrder);

            if (fruitChoice.getQuantity() == 0) {
                fruitList.remove(fruitChoice);
            }

            Fruit fruitOrder = new Fruit(fruitChoice.getId(), fruitChoice.getName(),
                    fruitChoice.getPrice(), quantityOrder, fruitChoice.getOrigin());

            //add fruitorder to invoice
            invoice.addFruit(fruitOrder);

            if (checkYesNo("order now")) {
                break;
            }

            if (fruitList.isEmpty()) {
                System.out.println("Empty shop!!");
                break;
            }
        }
        displayInvoice(invoice);
        String customer = Utility.getString("Enter customer: ", "Wrong", utility.REGEX_STRING);
        invoice.setCustomer(customer);
        invoiceList.add(invoice);
    }

    private Fruit getFruitById(String item) {
        for (Fruit fruit : fruitList) {
            if (fruit.getId().equalsIgnoreCase(item)) {
                return fruit;
            }
        }
        return null;
    }
    
    
    void displayInvoice(Invoice invoice) {
        System.out.format("%-10s %-10s %-10s %-10s\n", "Product", "Quantity", "Price", "Amount");
        for (Fruit fruit : invoice.getListFruitOrder()) {
            System.out.format("%-10s %-10s %-10s %-10s\n", fruit.getName(),
                    fruit.getQuantity(), fruit.getPrice(), fruit.getMoney());
        }

        System.out.println("Total: "+invoice.getTotal() + "$");
    }

    void viewOrder() {
        if (invoiceList.isEmpty()) {
            System.out.println("Empty List!!");
            return;
        }

        for (Invoice invoice : invoiceList) {
            System.out.println("Customer: " + invoice.getCustomer());
            displayInvoice(invoice);
            System.out.println("");
        }
    }

}
