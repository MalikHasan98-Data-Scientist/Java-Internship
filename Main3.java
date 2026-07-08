import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String pin;

    public BankAccount(String accountNumber, String name, double initialBalance, String pin) {
        this.accountNumber = accountNumber;
        this.accountHolderName = name;
        this.balance = initialBalance;
        this.pin = pin;
    }
    public boolean validatePIN(String inputPIN) {
        return this.pin.equals(inputPIN);
    }
    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount! Must be greater than zero.");
            return false;
        }
        balance += amount;
        System.out.println("✓ Deposit successful! Amount: $" + String.format("%.2f", amount));
        return true;
    }
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("✗ Invalid amount!");
            return false;
        }
        if (amount > balance) {
            System.out.println("✗ Insufficient funds! Balance: $" + String.format("%.2f", balance));
            return false;
        }
        balance -= amount;
        System.out.println("✓ Withdrawal successful! Amount: $" + String.format("%.2f", amount));
        return true;
    }
    public void checkBalance() {
        System.out.println("\n========== ACCOUNT INFO ==========");
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: $" + String.format("%.2f", balance));
        System.out.println("====================================\n");
    }
    public String getAccountHolderName() {
        return accountHolderName;
    }
}
class ATM {
    private BankAccount account;
    private Scanner scanner;
    private boolean isAuthenticated;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
        this.isAuthenticated = false;
    }
    public boolean authenticateUser() {
        System.out.println("\n========== ATM LOGIN ==========");
        System.out.print("Enter your PIN: ");
        String inputPIN = scanner.nextLine();

        if (account.validatePIN(inputPIN)) {
            System.out.println("✓ Login successful! Welcome, " + account.getAccountHolderName());
            isAuthenticated = true;
            return true;
        } else {
            System.out.println("✗ Incorrect PIN! Access denied.");
            isAuthenticated = false;
            return false;
        }
    }
    public void displayMenu() {
        System.out.println("\n========== ATM MENU ==========");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
        System.out.println("===============================");
        System.out.print("Choose an option (1-4): ");
    }
    public void run() {
        System.out.println("========================================");
        System.out.println("      WELCOME TO BANK ATM SYSTEM");
        System.out.println("========================================");

        if (!authenticateUser()) {
            System.out.println("Too many failed attempts. Exiting...");
            return;
        }
        boolean exit = false;
        while (!exit) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        account.checkBalance();
                        break;
                    case 2:
                        System.out.print("\nEnter amount to deposit: $");
                        double depositAmount = Double.parseDouble(scanner.nextLine());
                        account.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("\nEnter amount to withdraw: $");
                        double withdrawAmount = Double.parseDouble(scanner.nextLine());
                        account.withdraw(withdrawAmount);
                        break;
                    case 4:
                        System.out.println("\nThank you for using the ATM!");
                        System.out.println("Please take your card. Have a nice day!");
                        exit = true;
                        break;
                    default:
                        System.out.println("✗ Invalid option! Please choose between 1-4.");
                }
                if (!exit) {
                    System.out.print("\nPress Enter to continue...");
                    scanner.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a valid number.");
            }
        }
        scanner.close();
    }
}
public class Main {
    public static void main(String[] args) {
        // Create a sample bank account
        BankAccount myAccount = new BankAccount(
                "1234-5678-9012",   // Account number
                "John Doe",          // Account holder name
                2500.00,             // Initial balance
                "1234"               // PIN code
        );
        // Create ATM instance
        ATM atm = new ATM(myAccount);

        // Run the ATM system
        atm.run();
    }
}