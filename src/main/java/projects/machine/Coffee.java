package projects.machine;

public enum Coffee {

    ESPRESSO(250, 16, 0, 4),
    LATTE(350, 20, 75, 7),
    CAPPUCCINO(200, 12, 100, 6);

    private int mlWater;
    private int gCoffeeBeans;
    private int mlMilk;
    private int cost;

    Coffee(int mlWater, int gCoffeeBeans, int mlMilk, int cost) {
        this.mlWater = mlWater;
        this.gCoffeeBeans = gCoffeeBeans;
        this.mlMilk = mlMilk;
        this.cost = cost;
    }

    public int getMlWater() {
        return mlWater;
    }

    public int getGramsCoffeeBeans() {
        return gCoffeeBeans;
    }

    public int getMlMilk() {
        return mlMilk;
    }

    public int getCost() {
        return cost;
    }
}
