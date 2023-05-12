package cs.langs.java.codeorg.oop.other.enums;

public class Account {

    private String code;
    private Long balance;

    public String getCode() {
        return code;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public static boolean changeBalance(Account account, Operation operation, Long sum) {
        switch(operation) {
            case DEPOSIT -> {
                account.setBalance(account.balance + sum);
                return true;
            }
            case WITHDRAW -> {
                if (sum > account.balance) {
                    System.out.println("Not enough money to withdraw.");
                    return false;
                }
                account.setBalance(account.balance - sum);
                return true;
            }
        }
        return false;
    }
}
