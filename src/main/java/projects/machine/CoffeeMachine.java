package projects.machine;

import java.util.Scanner;

import static projects.machine.Ingredient.*;

public class CoffeeMachine {
    private int mlWater;
    private int mlMilk;
    private int gCoffeeBeans;

    private int orderCups;

    private int orderServings;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CoffeeMachine machine = new CoffeeMachine();

        System.out.println("Write how many ml of water the coffee machine has:");
        machine.mlWater = scanner.nextInt();

        System.out.println("Write how many ml of milk the coffee machine has:");
        machine.mlMilk = scanner.nextInt();

        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        machine.gCoffeeBeans = scanner.nextInt();

        System.out.println("Write how many cups of coffee you will need:");
        machine.orderCups = scanner.nextInt();

        machine.calculateServings();
        machine.displayOrderMessage();
    }

    private void displayOrderMessage() {
        if (orderCups == orderServings) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (orderCups < orderServings) {
            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)%n",
                    orderServings - orderCups);
        } else {
            System.out.printf("No, I can make only %d cup(s) of coffee%n", orderServings);
        }
    }

    private void calculateServings() {
        int waterServings = mlWater / WATER.getCupQuantity();
        int milkServings = mlMilk / MILK.getCupQuantity();
        int coffeeServings = gCoffeeBeans / COFFEE.getCupQuantity();

        this.orderServings = Math.min(waterServings, Math.min(milkServings, coffeeServings));
    }

    private static void makeCoffee() {
        begin();
        grind();
        boil();
        mix();
        pourCoffee();
        pourMilk();
        end();
    }

    public static void begin() {
        System.out.println("Starting to make a coffee");
    }

    public static void grind() {
        System.out.println("Grinding coffee beans");
    }

    public static void boil() {
        System.out.println("Boiling water");
    }

    public static void mix() {
        System.out.println("Mixing boiled water with crushed coffee beans");
    }

    public static void pourCoffee() {
        System.out.println("Pouring coffee into the cup");
    }

    public static void pourMilk() {
        System.out.println("Pouring some milk into the cup");
    }

    public static void end() {
        System.out.println("Coffee is ready!");
    }
}
