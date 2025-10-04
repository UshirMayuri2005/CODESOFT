import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("Welcome to the ATM Machine");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (account.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful. New balance: " + account.getBalance());
                    } else {
                        System.out.println("Withdrawal failed. Insufficient balance or invalid amount.");
                    }
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    if (account.deposit(depositAmount)) {
                        System.out.println("Deposit successful. New balance: " + account.getBalance());
                    } else {
                        System.out.println("Deposit failed. Invalid amount.");
                    }
                    break;
                case 3:
                    System.out.println("Current balance: " + account.getBalance());
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.00); // Initial balance
        ATM atm = new ATM(userAccount);
        atm.run();
    }
}