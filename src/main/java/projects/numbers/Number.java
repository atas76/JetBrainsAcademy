package projects.numbers;

public class Number {

    private int number;
    private boolean even;
    private boolean odd;
    private boolean buzz;
    private boolean duck;

    public Number(int number) {

        this.number = number;

        even = number % 2 == 0;
        odd = !even;

        boolean divisibleBySeven = (number % 7 == 0);
        boolean lastDigitSeven = number % 10 == 7;
        this.buzz = divisibleBySeven || lastDigitSeven;
        this.duck = isDuck();
    }

    public void print() {
        System.out.println("Properties of " + number);
        System.out.println("even: " + even);
        System.out.println("odd: " + odd);
        System.out.println("buzz: " + buzz);
        System.out.println("duck: " + duck);
    }

    private boolean isDuck() {

        int quotient = number;

        while (quotient > 0) {
            int modulo = quotient % 10;
            if (modulo == 0) return true;
            quotient /= 10;
        }

        return false;
    }
}
