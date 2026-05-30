import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static BudgetManager budgetManager = new BudgetManager();
    static ReportGenerator reportGenerator = new ReportGenerator(budgetManager);

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("      BUDGET & EXPENSE TRACKER");
        System.out.println("   Developed by Vijay Bridgelalsingh");
        System.out.println("==========================================");

        int choice = -1;
        while (choice != 0) {
            showMainMenu();
            choice = getIntInput();
            switch (choice) {
                case 1 -> addIncome();
                case 2 -> addExpense();
                case 3 -> budgetManager.viewAllTransactions();
                case 4 -> viewByCategory();
                case 5 -> budgetManager.viewBalance();
                case 6 -> searchTransaction();
                case 7 -> deleteTransaction();
                case 8 -> setBudgetLimit();
                case 9 -> reportsMenu();
                case 0 -> System.out.println("\nExiting. Goodbye!");
                default -> System.out.println("\n✘ Invalid option. Try again.");
            }
        }
    }

    static void showMainMenu() {
        System.out.println("\n==========================================");
        System.out.println("              MAIN MENU");
        System.out.println("==========================================");
        System.out.println("  1. Add Income");
        System.out.println("  2. Add Expense");
        System.out.println("  3. View All Transactions");
        System.out.println("  4. View Expenses by Category");
        System.out.println("  5. View Balance");
        System.out.println("  6. Search Transaction");
        System.out.println("  7. Delete Transaction");
        System.out.println("  8. Set Budget Limit");
        System.out.println("  9. Reports");
        System.out.println("  0. Exit");
        System.out.println("==========================================");
        System.out.print("Enter choice: ");
    }

    static void addIncome() {
        System.out.print("Enter description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter amount: $");
        double amount = getDoubleInput();
        System.out.print("Enter date (DD/MM/YYYY): ");
        String date = scanner.nextLine();
        budgetManager.addIncome(desc, amount, date);
    }

    static void addExpense() {
        System.out.print("Enter description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter amount: $");
        double amount = getDoubleInput();
        System.out.println("Select category:");
        Category[] cats = Category.values();
        for (int i = 0; i < cats.length; i++) {
            System.out.println("  " + (i + 1) + ". " + cats[i]);
        }
        System.out.print("Enter choice: ");
        int catChoice = getIntInput() - 1;
        Category category = (catChoice >= 0 && catChoice < cats.length)
                ? cats[catChoice] : Category.OTHER;
        System.out.print("Enter date (DD/MM/YYYY): ");
        String date = scanner.nextLine();
        budgetManager.addExpense(desc, amount, category, date);
    }

    static void viewByCategory() {
        System.out.println("Select category:");
        Category[] cats = Category.values();
        for (int i = 0; i < cats.length; i++) {
            System.out.println("  " + (i + 1) + ". " + cats[i]);
        }
        System.out.print("Enter choice: ");
        int catChoice = getIntInput() - 1;
        Category category = (catChoice >= 0 && catChoice < cats.length)
                ? cats[catChoice] : Category.OTHER;
        budgetManager.viewByCategory(category);
    }

    static void searchTransaction() {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine();
        budgetManager.searchByDescription(keyword);
    }

    static void deleteTransaction() {
        System.out.print("Enter Transaction ID to delete: ");
        int id = getIntInput();
        budgetManager.deleteTransaction(id);
    }

    static void setBudgetLimit() {
        System.out.print("Enter monthly budget limit: $");
        double limit = getDoubleInput();
        budgetManager.setBudgetLimit(limit);
    }

    static void reportsMenu() {
        System.out.println("\n===== REPORTS =====");
        System.out.println("  1. Financial Summary");
        System.out.println("  2. Spending by Category");
        System.out.println("  3. Highest Spending Category");
        System.out.print("Enter choice: ");
        int choice = getIntInput();
        switch (choice) {
            case 1 -> reportGenerator.generateSummary();
            case 2 -> reportGenerator.generateCategoryBreakdown();
            case 3 -> reportGenerator.highestSpendingCategory();
            default -> System.out.println("\n✘ Invalid option.");
        }
    }

    static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("✘ Invalid input. Enter a number: ");
            }
        }
    }

    static double getDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("✘ Invalid input. Enter a valid amount: ");
            }
        }
    }
}