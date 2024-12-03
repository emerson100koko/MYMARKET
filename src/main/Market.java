package main;

import model.Product;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Market {

    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Product> products;
    private static Map<Product, Integer> cart;

    public static void main(String[] args) {
        products = new ArrayList<>();
        cart = new HashMap<>();
        menu();
    }

    private static void menu() {

        System.out.println("------------------------------------------------------------");
        System.out.println("----------Welcome to Emerson Market----------");
        System.out.println("------------------------------------------------------------");
        System.out.println("********* Select an option you want to perform *************");
        System.out.println("------------------------------------------------------------");
        System.out.println("|   Option 1 - Register      |");
        System.out.println("|   Option 2 - List          |");
        System.out.println("|   Option 3 - Buy           |");
        System.out.println("|   Option 4 - Cart          |");
        System.out.println("|   Option 5 - Leave         |");

        int option = input.nextInt();

        switch (option) {

            case 1:
                registerProduct();
                break;
            case 2:
                ListProduct();
                break;
            case 3:
                buyProduct();
                break;
            case 4:
                LookProduct();
                break;
            case 5:
                System.out.println("Thanks for your preference!");
                System.exit(0);
            default:
                System.out.println("Invalid Option");
                menu();
                break;
        }
    }

    private static void registerProduct() {
        System.out.println("Name of Product: ");
        String name = input.next();

        System.out.println("Price of Product: ");
        Double price = input.nextDouble();

        Product product = new Product(price, name);
        products.add(product);

        System.out.println(product.getName() + " Successfully Registered!");
        menu();
    }

    private static void ListProduct() {
        if (products.size() > 0) {
            System.out.println("List of Products! \n");

            for (Product p : products) {
                System.out.println(p);
            }
        } else {

            System.out.println("No Products Registered");
        }

        menu();
    }

    private static void buyProduct() {
        if (products.size() > 0) {
            System.out.println("Code of Products:  \n");

            System.out.println("---------------- Products Availables ------------");
            for (Product p : products) {
                System.out.println(p + "\n");
            }
            int id = Integer.parseInt(input.next());
            boolean isPresent = false;

            for (Product p : products) {
                if (p.getId() == id) {
                    int qtd = 0;
                    try {
                        qtd = cart.get(p);
                        cart.put(p, qtd +1);
                    }catch (NullPointerException e) {
                        cart.put(p, 1);
                    }

                    System.out.println(p.getName() + " added to cart.");
                    isPresent = true;

                    if (isPresent) {
                        System.out.println("Want to add another product to the cart?   ");
                        System.out.println("Enter 1 for the yes, or 0 to complete the purchase. \n");
                        int option = Integer.parseInt(input.next());

                        if (option == 1) {
                            buyProduct();
                        }else {
                            finalizePurchase();
                        }
                    }
                }else {
                    System.out.println("Product not found");
                    menu();
                }
            }
        } else {
            System.out.println("No Products registered!!");
            menu();
        }
    }

    private static void LookProduct() {
        System.out.println("---Product in your cart!---");
        if (cart.size() > 0) {
            for (Product p : cart.keySet()) {
                System.out.println("Product: " + p + "\nQuantity: " + cart.get(p));
            }
        } else {
            System.out.println("Cart Empty");
        }
        menu();
    }

    private static void finalizePurchase() {
        Double valueOfPurchase = 0.0;
        System.out.println("Your Products");

        for (Product p : cart.keySet()) {
            int qtd = cart.get(p);
            valueOfPurchase += p.getPrice() * qtd;
            System.out.println(p);
            System.out.println("Quantity: " + qtd);
            System.out.println("-------------------");

        }

        System.out.println("This value of your purchase is: " + Utils.doubleToString(valueOfPurchase));
        cart.clear();
        System.out.println("Thanks for your preference!");
        menu();
    }
}