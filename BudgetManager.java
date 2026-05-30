import java.util.ArrayList;
    public class BudgetManager {
        private ArrayList<Transaction> transactions;
        private int nextId;
        private double budgetLimit;

        public BudgetManager() {
            transactions = new ArrayList<>();
            nextId = 1;
            budgetLimit = 0;
        }

        public void setBudgetLimit(double limit) {
            this.budgetLimit = limit;
            System.out.println(" Budget limit set to: $" + String.format("%.2f", limit));
        }

        public void addIncome(String description, double amount, String date) {
            Transaction t = new Transaction(nextId++, description, amount,
                    TransactionType.INCOME, date);
            transactions.add(t);
            System.out.println(" Income added successfully.");
        }

        public void addExpense(String description, double amount, Category category, String date) {
            Transaction t = new Transaction(nextId++, description, amount,
                    TransactionType.EXPENSE, category, date);
            transactions.add(t);
            System.out.println("Expense added successfully.");

            if (getTotalExpenses() > getTotalIncome()) {
                System.out.println("WARNING: Your expenses have exceeded your income!");
            }

            if (budgetLimit > 0 && getTotalExpenses() >= budgetLimit * 0.9) {
                System.out.println("WARNING: You are approaching your budget limit!");
            }
        }

        public void viewAllTransactions() {
            if (transactions.isEmpty()) {
                System.out.println("\nNo transactions found.");
                return;
            }
            System.out.println("\n===== ALL TRANSACTIONS =====");
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }

        public void viewByCategory(Category category) {
            boolean found = false;
            System.out.println("\n===== EXPENSES: " + category + " =====");
            for (Transaction t : transactions) {
                if (t.getType() == TransactionType.EXPENSE && t.getCategory() == category) {
                    System.out.println(t);
                    found = true;
                }
            }
            if (!found) System.out.println("No expenses found for category: " + category);
        }

        public void viewBalance() {
            double income = getTotalIncome();
            double expenses = getTotalExpenses();
            double balance = income - expenses;
            double percentSpent = income > 0 ? (expenses / income) * 100 : 0;

            System.out.println("\n===== CURRENT BALANCE =====");
            System.out.println("Total Income   : $" + String.format("%.2f", income));
            System.out.println("Total Expenses : $" + String.format("%.2f", expenses));
            System.out.println("Balance        : $" + String.format("%.2f", balance));
            System.out.printf("You have spent %.1f%% of your income%n", percentSpent);
            if (budgetLimit > 0) {
                System.out.println("Budget Limit   : $" + String.format("%.2f", budgetLimit));
            }
            System.out.println("===========================");
        }

        public void deleteTransaction(int id) {
            Transaction found = null;
            for (Transaction t : transactions) {
                if (t.getId() == id) { found = t; break; }
            }
            if (found != null) {
                transactions.remove(found);
                System.out.println("\n✔ Transaction deleted successfully.");
            } else {
                System.out.println("\n✘ Transaction with ID " + id + " not found.");
            }
        }

        public void searchByDescription(String keyword) {
            boolean found = false;
            System.out.println("\n===== SEARCH RESULTS =====");
            for (Transaction t : transactions) {
                if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    System.out.println(t);
                    found = true;
                }
            }
            if (!found) System.out.println("No transactions found for: " + keyword);
        }

        public double getTotalIncome() {
            double total = 0;
            for (Transaction t : transactions)
                if (t.getType() == TransactionType.INCOME) total += t.getAmount();
            return total;
        }

        public double getTotalExpenses() {
            double total = 0;
            for (Transaction t : transactions)
                if (t.getType() == TransactionType.EXPENSE) total += t.getAmount();
            return total;
        }

        public ArrayList<Transaction> getTransactions() { return transactions; }
    }
