package cs.langs.java.basics.strings.textblocks;

public class ClosingDelimeter {

    public static void main(String [] args) {
        String nameAnimal = "Pencil";
        String typeAnimal = "Cat";

        String firstAnimal = """
    name: %s
    type: %s
    """.formatted(nameAnimal, typeAnimal);

        String secondAnimal = String.format("name: %s\ntype: %s", nameAnimal, typeAnimal);

        System.out.println("First animal");
        System.out.print(firstAnimal);
        System.out.println("-Next");
        System.out.println("Second animal");
        System.out.print(secondAnimal);
        System.out.println("-Next");
    }
}
