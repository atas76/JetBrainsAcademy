package projects.machine;

public enum Ingredient {
    COFFEE("coffee beans", "g", 15),
    WATER("water", "ml", 200),
    MILK("milk", "ml", 50);

    private String label;
    private String unit;
    private int cupQuantity;

    Ingredient(String label, String unit, int cupQuantity) {
        this.label = label;
        this.unit = unit;
        this.cupQuantity = cupQuantity;
    }

    public String getLabel() {
        return this.label;
    }

    public String getUnit() {
        return this.unit;
    }

    public int getCupQuantity() {
        return this.cupQuantity;
    }
}
