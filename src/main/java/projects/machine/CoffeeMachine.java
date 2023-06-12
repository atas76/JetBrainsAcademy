package projects.machine;

import java.util.Map;
import java.util.Scanner;

import static projects.machine.Ingredient.*;

public class CoffeeMachine {

    private static Map<Ingredient, Integer> ingredientQuantities;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write how many cups of coffee you will need:");
        int cups = scanner.nextInt();
        ingredientQuantities = IngredientCalculator.calculateIngredients(cups);
        System.out.println("For " + cups + " of coffee you will need:");
        displayIngredientQuantity(WATER);
        displayIngredientQuantity(MILK);
        displayIngredientQuantity(COFFEE);
    }

    private static void displayIngredientQuantity(Ingredient ingredient) {
        System.out.println(ingredientQuantities.get(ingredient) +
                " " + ingredient.getUnit() + " of " + ingredient.getLabel());
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
