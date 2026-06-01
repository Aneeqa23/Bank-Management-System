
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//MAIN METHOD
public class Main {
    // global variable
    static int basemoney = 1000000000;
    static int ID = (int) (Math.random() * 9000) + 1000;
    static Scanner globalScanner = new Scanner(System.in); // Global Scanner

    // USER BUILT METHODS

    // to show main menu
    public static void showMainMenu() {
        int choice;

        System.out.println("===============================================");
        System.out.println("|     WELCOME TO BANK MANAGEMENT SYSTEM       |");
        System.out.println("===============================================");
        System.out.println("|                 MAIN MENU                   |");
        System.out.println("|---------------------------------------------|");
        System.out.println("|  1. Customer Portal                         |");
        System.out.println("|  2. Admin Login                             |");
        System.out.println("|  0. Exit                                    |");
        System.out.println("===============================================");
        System.out.print("Enter your choice: ");

        try {
            choice = globalScanner.nextInt();
            globalScanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    customerPanel();
                    break;
                case 2:
                    if (adminLogin()) {
                        System.out.println(" ");
                        adminPanel();
                    }
                    break;
                case 0:
                    System.out.println("\nSystem terminated. Thank you for using our Bank.");
                    globalScanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid selection. Please choose between 0 to 2.");
            }
        } catch (InputMismatchException e) {
            System.out.println("\nInput error. Please enter a valid number.");
            globalScanner.nextLine(); // Clear the invalid input
        }
        showMainMenu(); // RECURSION
    }

    // to show admin panel
    public static void adminPanel() {
        int choice;

        while (true) {
            System.out.println("=========================================================");
            System.out.println("|                  ADMIN OPERATIONS CONSOLE              |");
            System.out.println("=========================================================");
            System.out.println("|  1. Customer Records Management                        |");
            System.out.println("|  2. Loan Supervision                                   |");
            System.out.println("|  3. Credit Card Controls                               |");
            System.out.println("|  4. Complaint Resolution Desk                          |");
            System.out.println("|  5. Interest & Fee Manager                             |");
            System.out.println("|  6. Transaction History Review                         |");
            System.out.println("|  7. Account Type setups                                |");
            System.out.println("|  0. Logout / Return to Main Gateway                    |");
            System.out.println("=========================================================");
            System.out.print(" Select an operation: ");

            try {
                choice = Integer.parseInt(globalScanner.nextLine());

                switch (choice) {
                    case 1:
                        readFile("customers.txt");
                        System.out.println(" ");
                        break;
                    case 2:
                        approveLoan();
                        System.out.println(" ");
                        break;
                    case 3:
                        creditCardControls();
                        System.out.println(" ");
                        break;
                    case 4:
                        System.out.println("\nThe complaints are as follows:");
                        readFile("complaints.txt");
                        System.out.println(" ");
                        break;
                    case 5:
                        interestFeeManage();
                        System.out.println(" ");
                        break;
                    case 6:
                        transactionHistory();
                        System.out.println(" ");
                        break;
                    case 7:
                        showAccountTypeSetupMenu();
                        System.out.println(" ");
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("\n Invalid input. Please select between 0–7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n Input error. Only numbers are allowed.");
            }

            System.out.println();
            System.out.println();
        }
    }

    // admin login
    public static boolean adminLogin() {
        System.out.print("Enter Admin Password: ");
        String password = globalScanner.nextLine();

        if (password.equals("abcde")) {
            System.out.println("Access Granted.");
            return true;
        } else {
            System.out.println("Access Denied.");
            return false;
        }
    }

    // 2. to approve loan
    public static void approveLoan() {
        System.out.print("Enter Loan ID to review (digits only, e.g., 1234): ");
        String loanID = globalScanner.nextLine();

        // Check if loanID contains only digits
        if (!loanID.matches("\\d+")) {
            System.out.println("Invalid Loan ID. It must contain digits only.");
            return;
        }

        System.out.println("Loan ID: LN-" + loanID); // Adding LN- prefix

        String decision;
        while (true) {
            System.out.print("Approve this loan? (yes/no): ");
            decision = globalScanner.nextLine().toLowerCase();

            if (decision.equals("yes")) {
                System.out.println("Loan Approved.");
                break;
            } else if (decision.equals("no")) {
                System.out.println("Loan Rejected.");
                break;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    // 3. credit card controls method
    public static void creditCardControls() {
        while (true) {
            System.out.println("+====== CREDIT CARD CONTROLS ======+");
            System.out.println("|1. Issue New Credit Card          |");
            System.out.println("|2. Block a Credit Card            |");
            System.out.println("|3. Unblock a Credit Card          |");
            System.out.println("|0. Back to Admin Panel            |");
            System.out.println("+==================================+");
            System.out.print("Select an option: ");

            try {
                int choice = globalScanner.nextInt();
                globalScanner.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                        System.out.print("Enter Customer Account Number to Issue Card: ");
                        String issueAcc = globalScanner.nextLine();
                        if (!issueAcc.matches("\\d+")) {
                            System.out.println("Invalid account number. Digits only.");
                            break;
                        }
                        System.out.println("Credit Card issued for account: " + issueAcc);
                        break;

                    case 2:
                        System.out.print("Enter Credit Card Number to Block: ");
                        String blockCard = globalScanner.nextLine();
                        if (!blockCard.matches("\\d+")) {
                            System.out.println("Invalid card number. Digits only.");
                            break;
                        }
                        System.out.println("Card " + blockCard + " has been BLOCKED.");
                        break;

                    case 3:
                        System.out.print("Enter Credit Card Number to Unblock: ");
                        String unblockCard = globalScanner.nextLine();
                        if (!unblockCard.matches("\\d+")) {
                            System.out.println("Invalid card number. Digits only.");
                            break;
                        }
                        System.out.println("Card " + unblockCard + " has been UNBLOCKED.");
                        break;

                    case 0:
                        System.out.println("Returning to Admin Panel...");
                        return;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                globalScanner.nextLine(); // Clear invalid input
            }
            System.out.println();
        }
    }

    // 5. interest and fee management method
    public static void interestFeeManage() {
    String[][] interestLog = new String[3][5]; // 2D ARRAYS
    int index = 0;

        while (index < interestLog.length) {
            System.out.println("\n-------------------------------------------------");
            System.out.println("         INTEREST & SERVICE FEE MANAGER          ");
            System.out.println("-------------------------------------------------");

            try {
                System.out.print("Enter Account Number: ");
                String accountNumber = globalScanner.nextLine();
                if (!accountNumber.matches("\\d+")) {
                    System.out.println("Account number must contain digits only.");
                    continue;
                }

                System.out.print("Enter Principal Amount (PKR): ");
                double principal = globalScanner.nextDouble();
                if (principal <= 0) {
                    System.out.println("Principal amount must be positive.");
                    globalScanner.nextLine();
                    continue;
                }

                System.out.print("Annual Interest Rate (%): ");
                double ratePercent = globalScanner.nextDouble();
                if (ratePercent < 0) {
                    System.out.println("Interest rate cannot be negative.");
                    globalScanner.nextLine();
                    continue;
                }

                System.out.print("Time Period (Years): ");
                double time = globalScanner.nextDouble();
                if (time <= 0) {
                    System.out.println("Time period must be positive.");
                    globalScanner.nextLine();
                    continue;
                }

                System.out.print("Fixed Service Fee (PKR): ");
                double serviceFee = globalScanner.nextDouble();
                if (serviceFee < 0) {
                    System.out.println("Service fee cannot be negative.");
                    globalScanner.nextLine();
                    continue;
                }

                globalScanner.nextLine(); // clear buffer

                double rate = ratePercent / 100;
                double amount = principal * Math.exp(rate * time);
                double interest = amount - principal;
                double totalDue = amount + serviceFee;

                interestLog[index][0] = accountNumber;
                interestLog[index][1] = String.format("%.2f", interest);
                interestLog[index][2] = String.format("%.2f", amount);
                interestLog[index][3] = String.format("%.2f", serviceFee);
                interestLog[index][4] = String.format("%.2f", totalDue);

                index++;

                if (index == interestLog.length) break;

                while (true) {
                    System.out.print("\nAdd another record? (yes/no): ");
                    String ans = globalScanner.nextLine().toLowerCase();

                    if (ans.equals("yes")) {
                        break;
                    } else if (ans.equals("no")) {
                        index = interestLog.length; // Force exit
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter only 'yes' or 'no'.");
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Use numbers only.");
                globalScanner.nextLine(); // Clear buffer
            }
        }

        System.out.println("\n---------------- INTEREST LOG ----------------");
        for (int i = 0; i < index; i++) {
            System.out.printf("Account: %s | Interest: PKR %s | Amount: PKR %s | Fee: PKR %s | Total: PKR %s\n",
                    interestLog[i][0], interestLog[i][1], interestLog[i][2], interestLog[i][3], interestLog[i][4]);
        }
        System.out.println("---------------------------------------------");
    }

    // 6. transaction history method
    public static void transactionHistory() {
        try {
            Scanner fileScanner = new Scanner(new File("transaction_log.txt"));
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Transaction log file not found. No transactions recorded yet.");
        }
    }

    // 7. to show account type setup menu
    public static void showAccountTypeSetupMenu() {
        int choice;

        do {
            System.out.println("---------------------------------------------------------");
            System.out.println("|                 ACCOUNT TYPE SETUP                    |");
            System.out.println("---------------------------------------------------------");
            System.out.println("|  1. View Account Type List                            |");
            System.out.println("|  2. View Account Type Details                         |");
            System.out.println("|  0. Back to Admin Menu                                |");
            System.out.println("---------------------------------------------------------");
            System.out.print(" Select an option: ");

            try {
                choice = globalScanner.nextInt();
                globalScanner.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                        System.out.println("Available Account Types:");
                        System.out.println("- Savings Account");
                        System.out.println("- Personal Account");
                        System.out.println("- Corporate Salary Account");
                        break;
                    case 2:
                        System.out.println("Account Type Details:");
                        System.out.println("- Savings       | Min Balance: $1000 | Interest: 5%");
                        System.out.println("- Personal      | Min Balance: $800  | Interest: 2.0%");
                        System.out.println("- Salary        | No Min Balance     | Interest: 1.0%");
                        break;
                    case 0:
                        System.out.println("Returning to Admin Menu...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                globalScanner.nextLine(); // Clear invalid input
                choice = -1; // Continue loop
            }

            System.out.println();
        } while (choice != 0);
    }

    // method to read file
    public static void readFile(String filename) {
        File file = new File(filename);

        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                System.out.println(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }

        System.out.println(" ");
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    // CUSTOMER PANEL
    public static void customerPanel() {
        int choice;

        do {
            System.out.println("+==============WELCOME TO CUSTOMER PANEL================+");
            System.out.println("|------------------ACCOUNT STATUS-----------------------|");
            System.out.println("|1.Log into existing account                            |");
            System.out.println("|2.Create a new account                                 |");
            System.out.println("|0.Exit                                                 |");
            System.out.println("+=======================================================+");
            System.out.print("Enter your choice: ");

            try {
                choice = globalScanner.nextInt();
                globalScanner.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                        customerCheck();
                        break;
                    case 2:
                        newacc();
                        break;
                    case 0:
                        System.out.println("Returning to main menu...");
                        break;
                    default:
                        System.out.println("\nInvalid selection. Please choose between 0 to 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInput error. Please enter a valid number.");
                globalScanner.nextLine(); // Clear invalid input
                choice = -1; // Continue loop
            }
        } while (choice != 0);
    }

    // PASSWORD CHECKING:
    // Method to check password correction
    public static boolean isCredentialsValid(String enteredId, String enteredPin, String filePath) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String storedId = parts[0].trim();
                    String storedPin = parts[3].trim();

                    if (storedId.equals(enteredId) && storedPin.equals(enteredPin)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing reader: " + e.getMessage());
            }
        }

        return false;
    }

    // Method to handle existing account login
    public static void customerCheck() {
        try {
            System.out.print("Enter your Account ID: ");
            String id = globalScanner.nextLine().trim();

            System.out.print("Enter your PIN: ");
            String pin = globalScanner.nextLine().trim();

            if (isCredentialsValid(id, pin, "customers.txt")) {
                System.out.println("Login successful!");
                accountType(); // Call your next operation (e.g., show services)
            } else {
                System.out.println("Invalid ID or PIN. Access denied.");
            }
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
        }
    }

    // method for displaying and invoking account types
    public static void accountType() {
        int choice;
        try {
            do {
                System.out.println("+=====================ACCOUNT TYPES=======================+");
                System.out.println("|1. Savings Account                                       |");
                System.out.println("|2. Personal Account                                      |");
                System.out.println("|3. Corporate Salary Account                              |");
                System.out.println("|4. Exit (Return to Customer Panel)                       |");
                System.out.println("+=========================================================+");

                System.out.print("Enter your choice: ");
                choice = globalScanner.nextInt();
                globalScanner.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                        savingsacc();
                        break;
                    case 2:
                        personalacc();
                        break;
                    case 3:
                        corporatesalaryacc(ID);
                        break;
                    case 4:
                        System.out.println("Returning to Customer Panel...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1-4.");
                }
            } while (choice != 4);
        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter a number (1-4).");
            globalScanner.nextLine(); // Clear invalid input
        }
    }

    // Method for Savings account
    public static void savingsacc() {
        int choice;

        do {
            System.out.println("+==========SAVINGS ACCOUNT===========+");
            System.out.println("|1. Interest on Money                 |");
            System.out.println("|2. Withdraw Money                    |");
            System.out.println("|3. Deposit Money                     |");
            System.out.println("|4. Transfer Money                    |");
            System.out.println("|5. Apply for loan                    |");
            System.out.println("|6. Transaction History               |");
            System.out.println("|7. File complaint                    |");
            System.out.println("|8.Bill Payment                       |");
            System.out.println("|9. Zakat Calculation                 |");
            System.out.println("|0. Exit                              |");
            System.out.println("+=====================================+");
            System.out.print("Enter your choice: ");

            try {
                choice = globalScanner.nextInt();
                globalScanner.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                        System.out.print("Enter time period for which you want to get interest (years): ");
                        int time = globalScanner.nextInt();
                        globalScanner.nextLine();
                        if (time <= 0)
                            throw new IllegalArgumentException("Time period must be positive");
                        calculateInterest(time);
                        break;
                    case 2:
                        withdraw();
                        break;
                    case 3:
                        deposit();
                        break;
                    case 4:
                        transfer();
                        break;
                    case 5:
                        applyLoan();
                        break;
                    case 6:
                        transactionHistory();
                        break;
                    case 7:
                        writeComplaints();
                        break;
                    case 8:
                        billPayment();
                        break;
                    case 9:
                        zakat();
                        break;
                    case 0:
                        System.out.println("Exiting savings account menu");
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                globalScanner.nextLine();
                choice = -1;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                choice = -1;
            }
        } while (choice != 0);
    }

    // method for personal account
    public static void personalacc() {
        int choice;

        do {
            System.out.println("+==========PERSONAL ACCOUNT===========+");
            System.out.println("|1. Check Balance                     |");
            System.out.println("|2. Withdraw Money                    |");
            System.out.println("|3. Deposit Money                     |");
            System.out.println("|4. Transfer Money                    |");
            System.out.println("|5. Apply for loan                    |");
            System.out.println("|6. Transaction History               |");
            System.out.println("|7. Bill Payment                      |");
            System.out.println("|8. File complaint                    |");
            System.out.println("|9. Zakat Calculation                 |");
            System.out.println("|0. Exit                              |");
            System.out.println("+=====================================+");
            System.out.print("Enter your choice: ");

            try {
                choice = globalScanner.nextInt();
                globalScanner.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        withdraw();
                        break;
                    case 3:
                        deposit();
                        break;
                    case 4:
                        transfer();
                        break;
                    case 5:
                        applyLoan();
                        break;
                    case 6:
                        transactionHistory();
                        break;
                    case 7:
                        billPayment();
                        break;
                    case 8:
                        writeComplaints();
                        break;
                    case 9:
                        zakat();
                        break;
                    case 0:
                        System.out.println("Exiting personal account menu");
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                globalScanner.nextLine();
                choice = -1;
            }
        } while (choice != 0);
    }

    // method for corporate salary account
    public static void corporatesalaryacc(int id) {
        int choice;

        do {
            System.out.println("+===========CORPORATE SALARY ACCOUNT===========+");
            System.out.println("|1. Check Balance                              |");
            System.out.println("|2. Withdraw Money                             |");
            System.out.println("|3. Zakat Calculation                          |");
            System.out.println("|4. Transfer Money                             |");
            System.out.println("|5. Apply for loan                             |");
            System.out.println("|6. Transaction History                        |");
            System.out.println("|7. File complaint                             |");
            System.out.println("|0. Exit                                       |");
            System.out.println("+==============================================+");
            System.out.print("Enter your choice: ");

            try {
                choice = globalScanner.nextInt();
                globalScanner.nextLine(); // Clear buffer
                id = (int) (Math.random() * 9000) + 1000;

                switch (choice) {
                    case 1:
                        checkBalance();
                        System.out.println("Enter years for which you want to calculate interest: ");
                        int time = globalScanner.nextInt();
                        globalScanner.nextLine();
                        calculateInterest(time);
                        break;
                    case 2:
                        withdraw();
                        break;
                    case 3:
                        zakat();
                        break;
                    case 4:
                        transfer();
                        break;
                    case 5:
                        applyLoan();
                        break;
                    case 6:
                        transactionHistory();
                        break;
                    case 7:
                        writeComplaints();
                        break;
                    case 0:
                        System.out.println("Exiting corporate salary account menu");
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                globalScanner.nextLine();
                choice = -1;
            }
        } while (choice != 0);
    }

    // 1. Check balance method
    public static void checkBalance() {
        System.out.println("Your current balance is: PKR " + basemoney);
    }

    // 2. method for withdrawing money
    public static void withdraw() {
        try {
            System.out.print("Enter amount to withdraw: ");
            double amount = globalScanner.nextDouble();
            globalScanner.nextLine(); // Clear buffer

            if (amount <= 0) {
                throw new IllegalArgumentException("Amount must be positive");
            }

            if (amount > basemoney) {
                throw new IllegalArgumentException("Insufficient funds");
            }

            basemoney -= amount;
            System.out.println("Amount " + amount + " withdrawn successfully. Your remaining balance is: " + basemoney);

            logTransaction(ID, "Withdrawal", amount);
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            globalScanner.nextLine(); // Clear invalid input
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 3. Method to deposit money
    public static void deposit() {
        try {
            System.out.print("Enter amount to deposit: ");
            double amount = globalScanner.nextDouble();
            globalScanner.nextLine(); // Clear buffer

            if (amount <= 0) {
                throw new IllegalArgumentException("Amount must be positive");
            }

            basemoney += amount;
            System.out.println("Amount " + amount + " deposited successfully. Your bank balance is: " + basemoney);

            logTransaction(ID, "Deposit", amount);
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            globalScanner.nextLine(); // Clear invalid input
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 4. method to transfer money
    public static void transfer() {
        boolean success = false;

        while (!success) {
            try {
                System.out.print("Enter recipient name: ");
                String recipient = globalScanner.nextLine().trim();

                if (!recipient.matches("[a-zA-Z ]+")) {
                    System.out.println("Invalid recipient name. Only letters and spaces allowed.");
                    continue;
                }

                System.out.print("Enter bank name: ");
                String bankname = globalScanner.nextLine().trim();

                if (!bankname.matches("[a-zA-Z ]+")) {
                    System.out.println("Invalid bank name. Only letters and spaces allowed.");
                    continue;
                }

                System.out.print("Enter amount to transfer: ");
                String amtInput = globalScanner.nextLine().trim();

                if (!amtInput.matches("\\d+(\\.\\d{1,2})?")) {
                    System.out.println("Invalid amount. Enter numbers only.");
                    continue;
                }

                double amount = Double.parseDouble(amtInput);

                if (amount <= 0) {
                    System.out.println("Amount must be greater than 0.");
                    continue;
                }

                if (amount > basemoney) {
                    System.out.println("Insufficient funds.");
                    continue;
                }

                // All validations passed
                basemoney -= amount;
                System.out.println("Transferred Rs. " + amount + " to " + recipient + " at " + bankname + ".");
                System.out.println("Remaining balance: Rs. " + basemoney);
                logTransaction(12345, "Transfer to " + recipient + " at " + bankname, amount);
                success = true;

            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    // 5. method for applying loan
    public static void applyLoan() {
        String[] loanTypes = {
                "Business Expansion Loan",
                "Mortgage Loan",
                "Credit Builder Loan",
                "Personal Loan"
        };

        int choice;
        do {
            System.out.println("+================== Apply for a Loan ===================+");
            System.out.println("|1. Business Expansion Loan      ($5,000 - $50,000)     |");
            System.out.println("|2. Mortgage Loan                ($20,000 - $500,000)   |");
            System.out.println("|3. Credit Builder Loan          ($500 - $2,000)        |");
            System.out.println("|4. Personal Loan                ($1,000 - $15,000)     |");
            System.out.println("|0. Exit                                                |");
            System.out.println("+=======================================================+");

            System.out.print("Enter your choice (0-4): ");

            try {
                choice = globalScanner.nextInt();
                globalScanner.nextLine(); // Clear buffer

                if (choice == 0) {
                    return; // exit method
                }

                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid option. Please select 0-4.");
                    continue;
                }

                System.out.print("Enter desired loan amount in USD: ");
                double loan = globalScanner.nextDouble();
                globalScanner.nextLine(); // Clear buffer

                boolean valid = false;
                switch (choice) {
                    case 1:
                        if (loan >= 5000 && loan <= 50000)
                            valid = true;
                        break;
                    case 2:
                        if (loan >= 20000 && loan <= 500000)
                            valid = true;
                        break;
                    case 3:
                        if (loan >= 500 && loan <= 2000)
                            valid = true;
                        break;
                    case 4:
                        if (loan >= 1000 && loan <= 15000)
                            valid = true;
                        break;
                }

                if (!valid) {
                    System.out.println("Amount not in valid range for selected loan type.");
                    continue;
                }

                System.out.println("Loan application submitted for " + loanTypes[choice - 1] + " with amount $" + loan);
                break;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                globalScanner.nextLine(); // Clear invalid input
                choice = -1; // Continue loop
            }
        } while (choice != 0);
    }

    // 6. file complaints
    public static void writeComplaints() {
        System.out.print("We apologize for the inconvenience. Please enter your complaint: ");
        String complaints = globalScanner.nextLine();

        try {
            FileWriter fileWriter = new FileWriter("complaints.txt", true);
            fileWriter.write(complaints + "\n");
            fileWriter.write("------------------------------------------------------------\n");
            fileWriter.close();
            System.out.println("Complaint filed successfully.");
        } catch (IOException e) {
            System.out.println("Error filing complaint: " + e.getMessage());
        }
    }

    // 7. bill payments
    public static void billPayment() {
        boolean paid = false;

        while (!paid) {
            try {
                System.out.print("Enter bill type (Electricity, Gas, Internet): ");
                String billType = globalScanner.nextLine().trim();

                if (!(billType.equalsIgnoreCase("Electricity") ||
                        billType.equalsIgnoreCase("Gas") ||
                        billType.equalsIgnoreCase("Internet"))) {
                    throw new IllegalArgumentException("Only Electricity, Gas, or Internet bills allowed.");
                }

                System.out.print("Enter bill amount: ");
                String input = globalScanner.nextLine().trim();

                if (input.isEmpty()) {
                    throw new IllegalArgumentException("Amount cannot be empty.");
                }

                double amount = Double.parseDouble(input);

                if (amount <= 0) {
                    throw new IllegalArgumentException("Amount must be positive.");
                }

                if (amount > basemoney) {
                    throw new IllegalArgumentException("Not enough funds.");
                }

                basemoney -= amount;
                System.out.println("Paid " + billType + " bill of Rs. " + amount);
                System.out.println("Remaining: Rs. " + basemoney);

                logTransaction(ID, "Bill Payment - " + billType, amount);
                paid = true;

            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number for amount.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // 8. INTEREST
    public static void calculateInterest(int time) {
        double rate = 5.0; // 5% annual interest rate
        double interest = (basemoney * rate * time) / 100;
        System.out.println(
                "Interest on: " + basemoney + " PKR, for " + time + " years at " + rate + "% = " + interest + " PKR");
        System.out.println("Interest generated per month is: " + (interest / 12));
    }

    // 9. ZAKAT
    public static void zakat() {
        double nisab = 153090; // based on 612.36g of silver × 250 PKR per gram

        if (basemoney >= nisab) {
            double zakat = basemoney * 0.025;
            System.out.println("Zakat on: " + basemoney + " PKR = " + zakat + " PKR");
        } else {
            System.out.println("Your current balance (" + basemoney + " PKR) is below the Nisab threshold.");
            System.out.println("Zakat is not obligatory on you right now.");
        }
    }

    // Method to create new account
    public static void newacc() {
        boolean valid = false;

        System.out.println("+=====================NEW ACCOUNT CREATION=======================+");
        System.out.println("Have a look at the services being provided by our Bank: ");
        customerServices();

        while (!valid) {
            try {
                System.out.println("Enter your details to create a new account.");
                System.out.print("Enter your ID: ");
                String id = globalScanner.nextLine().trim();
                if (!id.matches("\\d+"))
                    throw new IllegalArgumentException("ID must be a number.");

                System.out.print("Enter your name: ");
                String name = globalScanner.nextLine().trim();
                if (name.isEmpty())
                    throw new IllegalArgumentException("Name cannot be empty.");

                System.out.print("Enter your email: ");
                String email = globalScanner.nextLine().trim();
                if (!email.contains("@"))
                    throw new IllegalArgumentException("Email must contain '@'.");

                System.out.print("Enter 4-digit PIN: ");
                String pin = globalScanner.nextLine().trim();
                if (!pin.matches("\\d{4}"))
                    throw new IllegalArgumentException("PIN must be exactly 4 digits.");

                System.out.println("Choose Account Type:");
                System.out.println("1. Corporate Salary Account");
                System.out.println("2. Personal Account");
                System.out.println("3. Savings Account");
                int choice = globalScanner.nextInt();
                globalScanner.nextLine(); // Clear buffer

                String accountType;
                switch (choice) {
                    case 1:
                        accountType = "Corporate Salary Account";
                        break;
                    case 2:
                        accountType = "Personal Account";
                        break;
                    case 3:
                        accountType = "Savings Account";
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid account type selected.");
                }

                if (accountExists(id, name, email, pin, accountType, "customers.txt")) {
                    System.out.println("Account already exists with the provided details.");
                } else {
                    addNewCustomer("customers.txt", Integer.parseInt(id), name, email, pin, accountType);
                    valid = true;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please try again.");
                globalScanner.nextLine(); // Clear invalid input
            }
        }
    }

    // method to update file with new customer account information
    public static void addNewCustomer(String filePath, int id, String name, String email, String pin,
            String accountType) {
        String customerLine = id + "," + name + "," + email + "," + pin + "," + accountType;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.write(customerLine);
            writer.newLine();
            writer.close();
            System.out.println("New customer added successfully. Your information:\n" + customerLine);
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }

    // method to get next customer id
    public static int getNextCustomerId(String filePath) {
        int maxId = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length > 0) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        if (id > maxId)
                            maxId = id;
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file for ID: " + e.getMessage());
        }
        return maxId + 1;
    }

    // method to check if account already exists
    public static boolean accountExists(String id, String name, String email, String pin, String accountType,
            String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 5); // Ensure 5 parts

                if (parts.length == 5) {
                    String fileId = parts[0].trim();
                    String fileName = parts[1].trim();
                    String fileEmail = parts[2].trim();
                    String filePin = parts[3].trim().replace("\"", "");
                    String fileAccountType = parts[4].trim();

                    if (fileId.equals(id) &&
                            filePin.equals(pin) &&
                            fileName.equalsIgnoreCase(name) &&
                            fileEmail.equalsIgnoreCase(email) &&
                            fileAccountType.equalsIgnoreCase(accountType)) {

                        System.out.println("Account exists and is verified.");
                        reader.close();
                        return true;
                    }
                }
            }
            reader.close();

            System.out.println("Account does not exist.");
            return false;

        } catch (IOException e) {
            System.out.println("Error reading customer file: " + e.getMessage());
            return false;
        }
    }

    // Method informing customers about various services provided by Bank
    public static void customerServices() {
        int choice;

        do {

            System.out.println("=========================================================");
            System.out.println("|               SERVICES PROVISION                      |");
            System.out.println("=========================================================");
            System.out.println("|  1. Deposit Money                                     |");
            System.out.println("|  2. Withdraw Money                                    |");
            System.out.println("|  3. Transfer Money                                    |");
            System.out.println("|  4. Loan Services                                     |");
            System.out.println("|  5. Bill Payment                                      |");
            System.out.println("|  6. View History                                      |");
            System.out.println("|  7. File Complaint                                    |");
            System.out.println("|  8. Check Balance                                     |");
            System.out.println("|  0. Exit                                              |");
            System.out.println("=========================================================");
            System.out.print("Select a service you wish to gain knowledge about: ");

            try {
                choice = globalScanner.nextInt();
                globalScanner.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                        System.out.println(
                                "DEPOSIT: Our Banking System allows you to deposit money to your account conveniently and without much hassle.");
                        break;
                    case 2:
                        System.out.println(
                                "WITHDRAW: Easily take out money from your account using an ATM, cheque, or over-the-counter. You can access your funds anytime you need them.");
                        break;
                    case 3:
                        System.out.println(
                                "TRANSFER: Send money quickly to another account locally or internationally.It's fast, secure, and available through mobile or online banking.");
                        break;
                    case 4:
                        System.out.println(
                                "LOAN SERVICES: Apply for personal, home, or business loans with flexible repayment options.Get financial support when you need it most.");
                        break;
                    case 5:
                        System.out.println(
                                "BILL PAYMENT: Pay utility, internet, and other bills directly from your bank account.No queues or delays—just easy, instant payments.");
                        break;
                    case 6:
                        System.out.println(
                                "VIEW HISTORY: Track all your past deposits, withdrawals, and payments anytime.Stay informed and in control of your finances.");
                        break;
                    case 7:
                        System.out.println(
                                "FILE COMPLAINT: Have an issue? Submit a complaint easily through our helpdesk or app.We’re here to resolve problems quickly and professionally.");
                        break;
                    case 8:
                        System.out.println(
                                "CHECK BALANCE: Quickly view your current account balance anytime via mobile, ATM, or online banking.Stay updated on your finances with just a few taps.");
                        break;
                    case 0:
                        System.out.println("Choose our bank for best and reliable outcomes...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                globalScanner.nextLine(); // Clear invalid input
                choice = -1; // Continue loop
            }
        } while (choice != 0);
    }

    // miscellaneous methods
    // 1.
    public static void logTransaction(int accountNumber, String type, double amount) {
        try {
            FileWriter writer = new FileWriter("transaction_log.txt", true); // true = append

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = now.format(formatter);

            String logEntry = timestamp + " | Account: " + accountNumber + " | " + type + " | $" + amount + "\n";

            writer.write(logEntry);
            writer.close();

            System.out.println("Transaction logged successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }

    // misc
    // 2. Method for creating a file that has stored information about the customers
    public static void customerFile(String filePath) {
        // 1D array to store customer data

        String[] customers = {
                "1,Muhammad Umer,muhammad1@example.com,1234,Corporate Salary Account",
                "2,Asif Jan,asghar2@example.com,5678,Personal Account",
                "3,Samina Asif,samina3@example.com,9012,Savings Account",
                "4,Irfan Junejo,irfan4@example.com,3456,Corporate Salary Account",
                "5,Mikaeel Khan,mikaeel5@example.com,7890,Personal Account",
                "6,Haris Iqbal,haris6@example.com,1122,Savings Account",
                "7,Nadeem Ahmed,nadeem7@example.com,3344,Corporate Salary Account",
                "8,Sara Roshi,sarah8@example.com,5566,Personal Account",
                "9,Junaid Imran,junaid9@example.com,7788,Savings Account",
                "10,Ameena Ali,ameena10@example.com,9900,Corporate Salary Account",
                "11,Sidra Mehak,sidrah11@example.com,1357,Personal Account",
                "12,Aneeqa Nadeem,aneeqa12@example.com,2468,Savings Account",
                "13,Hareem Tahir,hareem13@example.com,3691,Corporate Salary Account",
                "14,Momina Bukhari,momina14@example.com,4802,Personal Account",
                "15,Aleena Qamar,aleena15@example.com,5913,Savings Account",
                "16,Maria Ghafoor,maria16@example.com,6024,Corporate Salary Account",
                "17,Hassaan Ali,hassaan17@example.com,7135,Personal Account",
                "18,Saad Haider,saad18@example.com,8246,Savings Account",
                "19,Abdullah Muslim,muslim19@example.com,9357,Corporate Salary Account",
                "20,Harron Saqib,haroon20@example.com,0468,Personal Account"
        };

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                for (String customer : customers) {
                    writer.write(customer);
                    writer.newLine();
                }
                writer.close();
                System.out.println("Customer file created successfully.");
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // MAIN method
    public static void main(String[] args) {
        showMainMenu();
    }
}
