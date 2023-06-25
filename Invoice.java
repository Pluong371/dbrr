/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit_0023;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Invoice {

    private String customer;
    private ArrayList<Fruit> listFruitOrder = new ArrayList<>();

    public Invoice() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public ArrayList<Fruit> getListFruitOrder() {
        return listFruitOrder;
    }

    public void setListFruitOrder(ArrayList<Fruit> listFruitOrder) {
        this.listFruitOrder = listFruitOrder;
    }

    public void addFruit(Fruit fruit) {
        Fruit fruitInList = getFruitById(fruit.getId());
        if (fruitInList == null) {
            listFruitOrder.add(fruit);
        } else {
            fruitInList.setQuantity(fruitInList.getQuantity() + fruit.getQuantity());
        }
    }

    public double getTotal() {
        double total = 0;
        for (Fruit fruit : listFruitOrder) {
            total += fruit.getMoney();
        }
        return total;
    }

    private Fruit getFruitById(String id) {
        for (Fruit fruit : listFruitOrder) {
            if (fruit.getId().equalsIgnoreCase(id)) {
                return fruit;
            }
        }
        return null;
    }
}
