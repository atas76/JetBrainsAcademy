package projects.machine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IngredientCalculator {
    public static Map<Ingredient, Integer> calculateIngredients(int cups) {
        Map<Ingredient, Integer> result = new HashMap<>();
        Arrays.stream(Ingredient.values()).forEach(ingredient ->
                result.put(ingredient, cups * ingredient.getCupQuantity()));
        return result;
    }
}
