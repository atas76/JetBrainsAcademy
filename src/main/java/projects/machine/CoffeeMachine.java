package projects.machine;

public class CoffeeMachine {
    public static void main(String[] args) {
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
