/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit_0023;

/**
 *
 * @author admin
 */
public class Main {

    public static void main(String[] args) {
        ManageFruit manage = new ManageFruit();
        Utility utility = new Utility();
        while (true) {
            displayMenu();

            int option = Utility.getInteger("Enter option.", "Wrong", 1, 4);

            switch (option) {
                case 1:
                    manage.createfruit();
                    break;
                case 2:
                    manage.viewOrder();
                    break;
                case 3:
                    manage.shopping();
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("FRUIT SHOP SYSTEM\n"
                + "1.Create Fruit\n"
                + "2.View orders\n"
                + "3.Shopping (for buyer)\n"
                + "4.Exit");
    }
}
