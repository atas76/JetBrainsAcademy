package projects.machine;

import java.util.*;

import static projects.machine.State.*;

public class CoffeeMachine {
    private int mlWater;
    private int mlMilk;
    private int gCoffeeBeans;

    private int cups;

    private int money;

    private State state = MAIN_MENU;

    public void execute(String action) {
        switch (state) {
            case MAIN_MENU -> {
                switch (action) {
                    case "buy" -> {
                        this.state = COFFEE_MENU;
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                    }
                    case "fill" -> {
                        this.state = FILL_WATER;
                        System.out.println("Write how many ml of water you want to add:");
                    }
                    case "take" -> {
                        int moneyReceived = takeMoney();
                        System.out.println("I gave you $" + moneyReceived);
                        returnToMainMenu();
                    }
                    case "remaining" -> {
                        displayState();
                        returnToMainMenu();
                    }
                    default -> {
                        System.out.println("Unsupported");
                        returnToMainMenu();
                    }
                }
            }
            case COFFEE_MENU -> {
                try {
                    int coffeeSelection = Integer.parseInt(action);
                    List<String> missingResources = processOrder(coffeeSelection);
                    if (missingResources.isEmpty()) {
                        System.out.println("I have enough resources, making you a coffee!");
                    } else {
                        missingResources.forEach(resource -> System.out.println("Sorry, not enough " + resource + "!"));
                    }
                } catch (NumberFormatException nfe) {
                    if (!"back".equals(action)) {
                        System.out.println("I have to say that this option is unsupported although it doesn't make any difference");
                        System.out.println("Just write 'back' next time if you don't want this message to appear again");
                    }
                }
                returnToMainMenu();
            }
            case FILL_WATER -> {
                this.mlWater += Integer.parseInt(action);
                this.state = FILL_MILK;
                System.out.println("Write how many ml of milk you want to add:");
            }
            case FILL_MILK -> {
                this.mlMilk += Integer.parseInt(action);
                this.state = FILL_COFFEE_BEANS;
                System.out.println("Write how many grams of coffee beans you want to add:");
            }
            case FILL_COFFEE_BEANS -> {
                this.gCoffeeBeans += Integer.parseInt(action);
                this.state = PUT_CUPS;
                System.out.println("Write how many disposable cups you want to add:");
            }
            case PUT_CUPS -> {
                this.cups += Integer.parseInt(action);
                returnToMainMenu();
            }
        }
    }

    private void returnToMainMenu() {
        this.state = MAIN_MENU;
        System.out.println();
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CoffeeMachine coffeeMachine = new CoffeeMachine.Builder()
                .mlWater(400)
                .mlMilk(540)
                .gCoffeeBeans(120)
                .cups(9)
                .money(550)
                .build();

        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String action = scanner.next();

        while (!"exit".equals(action)) {
            coffeeMachine.execute(action);
            action = scanner.next();
        }
    }

    public CoffeeMachine(Builder builder) {
        this.mlWater = builder.mlWater;
        this.mlMilk = builder.mlMilk;
        this.gCoffeeBeans = builder.gCoffeeBeans;
        this.cups = builder.cups;
        this.money = builder.money;
    }

    public static class Builder {
        private int mlWater;
        private int mlMilk;
        private int gCoffeeBeans;
        private int cups;
        private int money;

        public Builder() {
        }

        public Builder mlWater(int mlWater) {
            this.mlWater = mlWater;
            return this;
        }

        public Builder mlMilk(int mlMilk) {
            this.mlMilk = mlMilk;
            return this;
        }

        public Builder gCoffeeBeans(int gCoffeeBeans) {
            this.gCoffeeBeans = gCoffeeBeans;
            return this;
        }

        public Builder cups(int cups) {
            this.cups = cups;
            return this;
        }

        public Builder money(int money) {
            this.money = money;
            return this;
        }

        public CoffeeMachine build() {
            return new CoffeeMachine(this);
        }
    }

    private void displayState() {
        System.out.println("The coffee machine has: ");
        System.out.println(mlWater + " ml of water");
        System.out.println(mlMilk + " ml of milk");
        System.out.println(gCoffeeBeans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + money + " of money");
    }

    private List<String> processOrder(int coffeeSelection) {
        Coffee order = Coffee.values()[coffeeSelection - 1];
        List<String> missingResources = checkResources(order);
        if (missingResources.isEmpty()) {
            updateState(order);
        }
        return missingResources;
    }

    private void updateState(Coffee order) {
        this.mlWater -= order.getMlWater();
        this.gCoffeeBeans -= order.getGramsCoffeeBeans();
        this.mlMilk -= order.getMlMilk();
        this.cups--;
        this.money += order.getCost();
    }

    private List<String> checkResources(Coffee order) {
        List<String> retVal = new ArrayList<>();
        if (order.getMlMilk() > this.mlMilk) {
            retVal.add("milk");
        }
        if (order.getGramsCoffeeBeans() > this.gCoffeeBeans) {
            retVal.add("coffee beans");
        }
        if (order.getMlWater() > this.mlWater) {
            retVal.add("water");
        }
        return retVal;
    }

    private int takeMoney() {
        int moneyBack = this.money;
        this.money = 0;
        return moneyBack;
    }
}
