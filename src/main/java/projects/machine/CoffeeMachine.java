package projects.machine;

import java.util.*;

public class CoffeeMachine {
    private int mlWater;
    private int mlMilk;
    private int gCoffeeBeans;

    private int cups;

    private int money;

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
            switch (action) {
                case "buy" -> {
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                    if (scanner.hasNextInt()) {
                        int coffeeSelection = scanner.nextInt();
                        List<String> missingResources = coffeeMachine.processOrder(coffeeSelection);
                        if (missingResources.isEmpty()) {
                            System.out.println("I have enough resources, making you a coffee!");
                        } else {
                            missingResources.forEach(resource -> System.out.println("Sorry, not enough " + resource + "!"));
                        }
                    } else {
                        String menuOption = scanner.next();
                        if (!"back".equals(menuOption)) {
                            System.out.println("I have to say that this option is unsupported although it doesn't make any difference");
                            System.out.println("Just write 'back' next time if you don't want this message to appear again");
                        }
                    }
                }
                case "fill" -> {
                    System.out.println("Write how many ml of water you want to add:");
                    int mlWater = scanner.nextInt();
                    System.out.println("Write how many ml of milk you want to add:");
                    int mlMilk = scanner.nextInt();
                    System.out.println("Write how many grams of coffee beans you want to add:");
                    int gCoffeeBeans = scanner.nextInt();
                    System.out.println("Write how many disposable cups you want to add:");
                    int cups = scanner.nextInt();
                    coffeeMachine.updateStateAfterFilling(mlWater, mlMilk, gCoffeeBeans, cups);
                }
                case "take" -> {
                    int moneyReceived = coffeeMachine.takeMoney();
                    System.out.println("I gave you $" + moneyReceived);
                }
                case "remaining" -> coffeeMachine.displayState();
                default -> System.out.println("Unsupported");
            }
            System.out.println();
            System.out.println("Write action (buy, fill, take, remaining, exit):");
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

    public void displayState() {
        System.out.println("The coffee machine has: ");
        System.out.println(mlWater + " ml of water");
        System.out.println(mlMilk + " ml of milk");
        System.out.println(gCoffeeBeans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + money + " of money");
    }

    public List<String> processOrder(int coffeeSelection) {
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

    public void updateStateAfterFilling(int mlWater, int mlMilk, int gCoffeeBeans, int cups) {
        this.mlWater += mlWater;
        this.mlMilk += mlMilk;
        this.gCoffeeBeans += gCoffeeBeans;
        this.cups += cups;
    }

    public int takeMoney() {
        int moneyBack = this.money;
        this.money = 0;
        return moneyBack;
    }
}
