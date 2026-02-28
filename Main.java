class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

class WithdrawalLimitExceededException extends Exception {
    public WithdrawalLimitExceededException(String message) {
        super(message);
    }
}

class AccountInactiveException extends Exception {
    public AccountInactiveException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class BankAccount {

    private double balance;
    private boolean isActive;

    public BankAccount(double balance, boolean isActive) {
        this.balance = balance;
        this.isActive = isActive;
    }

    public void withdraw(double amount)
            throws InvalidAmountException,
            WithdrawalLimitExceededException,
            AccountInactiveException,
            InsufficientBalanceException {

        validateAmount(amount);
        validateAccountStatus();
        validateBalance(amount);

        balance -= amount;
        System.out.println("Withdrawal successful. Remaining balance: ₹" + balance);
    }

    private void validateAmount(double amount)
            throws InvalidAmountException, WithdrawalLimitExceededException {

        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero.");
        }

        if (amount > 50000) {
            throw new WithdrawalLimitExceededException("Withdrawal limit of ₹50,000 exceeded.");
        }
    }

    private void validateAccountStatus()
            throws AccountInactiveException {

        if (!isActive) {
            throw new AccountInactiveException("Account is inactive.");
        }
    }

    private void validateBalance(double amount)
            throws InsufficientBalanceException {

        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance.");
        }
    }
}

public class BankingRiskManager {

    public static void main(String[] args) {

        BankAccount account = new BankAccount(30000, true);

        try {
            account.withdraw(60000);
        }
        catch (InvalidAmountException |
               WithdrawalLimitExceededException |
               AccountInactiveException |
               InsufficientBalanceException e) {

            System.out.println("Transaction failed: " + e.getMessage());
        }
        finally {
            System.out.println("Transaction processing completed.");
        }
    }
}
