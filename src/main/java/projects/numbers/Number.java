package projects.numbers;

public class Number {

    private long number;
    private boolean even;
    private boolean odd;
    private boolean buzz;
    private boolean duck;
    private boolean palindromic;

    public Number(long number) {

        this.number = number;

        even = number % 2 == 0;
        odd = !even;

        boolean divisibleBySeven = (number % 7 == 0);
        boolean lastDigitSeven = number % 10 == 7;
        this.buzz = divisibleBySeven || lastDigitSeven;
        this.duck = isDuck();
        this.palindromic = isPalindromic();
    }

    public void print() {
        System.out.println("Properties of " + number);
        System.out.println("even: " + even);
        System.out.println("odd: " + odd);
        System.out.println("buzz: " + buzz);
        System.out.println("duck: " + duck);
        System.out.println("palindromic: " + palindromic);
    }

    private boolean isDuck() {

        long quotient = number;

        while (quotient > 0) {
            long modulo = quotient % 10;
            if (modulo == 0) return true;
            quotient /= 10;
        }

        return false;
    }

    private boolean isPalindromic() {
        long quotient = number;
        long reverseNumber = 0;
        while (quotient > 0) {
            long modulo = quotient % 10;
            reverseNumber = reverseNumber * 10 + modulo;
            quotient /= 10;
        }
        return reverseNumber == this.number;
    }
}
