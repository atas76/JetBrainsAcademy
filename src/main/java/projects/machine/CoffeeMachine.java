package projects.machine;

import java.util.Scanner;

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
        coffeeMachine.displayState();

        System.out.println("Write action (buy, fill, take)");
        String action = scanner.next();

        switch(action) {
            case "buy":
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                int coffeeSelection = scanner.nextInt();
                coffeeMachine.processPurchase(coffeeSelection);
                break;
            case "fill":
                System.out.println("Write how many ml of water you want to add:");
                int mlWater = scanner.nextInt();
                System.out.println("Write how many ml of milk you want to add:");
                int mlMilk = scanner.nextInt();
                System.out.println("Write how many grams of coffee beans you want to add:");
                int gCoffeeBeans = scanner.nextInt();
                System.out.println("Write how many disposable cups you want to add:");
                int cups = scanner.nextInt();
                coffeeMachine.updateStateAfterFilling(mlWater, mlMilk, gCoffeeBeans, cups);
                break;
            case "take":
                coffeeMachine.takeMoney();
                System.out.println("I gave you $550");
                break;
            default:
                System.out.println("Unsupported");
        }
        coffeeMachine.displayState();
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

    public void processPurchase(int coffeeSelection) {
        updateStateAfterPurchase(Coffee.values()[coffeeSelection - 1]);
    }

    public void updateStateAfterFilling(int mlWater, int mlMilk, int gCoffeeBeans, int cups) {
        this.mlWater += mlWater;
        this.mlMilk += mlMilk;
        this.gCoffeeBeans += gCoffeeBeans;
        this.cups += cups;
    }

    public void takeMoney() {
        this.money = 0;
    }

    private void updateStateAfterPurchase(Coffee coffeeOrder) {
        this.mlWater -= coffeeOrder.getMlWater();
        this.gCoffeeBeans -= coffeeOrder.getGramsCoffeeBeans();
        this.mlMilk -= coffeeOrder.getMlMilk();
        this.cups--;
        this.money += coffeeOrder.getCost();
    }
}
