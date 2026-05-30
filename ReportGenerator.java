import java.util.ArrayList;

public class ReportGenerator {

    private BudgetManager budgetManager;

    public ReportGenerator(BudgetManager budgetManager) {
        this.budgetManager = budgetManager;
    }

    public void generateSummary() {
        double income = budgetManager.getTotalIncome();
        double expenses = budgetManager.getTotalExpenses();
        double balance = income - expenses;
        double percentSpent = income > 0 ? (expenses / income) * 100 : 0;

        System.out.println("\n===== FINANCIAL SUMMARY =====");
        System.out.println("Total Income    : $" + String.format("%.2f", income));
        System.out.println("Total Expenses  : $" + String.format("%.2f", expenses));
        System.out.println("Remaining       : $" + String.format("%.2f", balance));
        System.out.printf("You have spent %.1f%% of your income%n", percentSpent);
        System.out.println("=============================");
    }

    public void generateCategoryBreakdown() {
        System.out.println("\n===== SPENDING BY CATEGORY =====");
        for (Category cat : Category.values()) {
            double total = 0;
            for (Transaction t : budgetManager.getTransactions()) {
                if (t.getType() == TransactionType.EXPENSE && t.getCategory() == cat) {
                    total += t.getAmount();
                }
            }
            if (total > 0) {
                System.out.println(cat + " : $" + String.format("%.2f", total));
            }
        }
        System.out.println("================================");
    }

    public void highestSpendingCategory() {
        Category highest = null;
        double max = 0;

        for (Category cat : Category.values()) {
            double total = 0;
            for (Transaction t : budgetManager.getTransactions()) {
                if (t.getType() == TransactionType.EXPENSE && t.getCategory() == cat) {
                    total += t.getAmount();
                }
            }
            if (total > max) { max = total; highest = cat; }
        }

        System.out.println("\n===== HIGHEST SPENDING CATEGORY =====");
        if (highest != null) {
            System.out.println(highest + " : $" + String.format("%.2f", max));
        } else {
            System.out.println("No expenses recorded yet.");
        }
        System.out.println("=====================================");
    }
}