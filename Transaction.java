public class Transaction {
    private int id;
    private String description;
    private double amount;
    private TransactionType type;
    private Category category;
    private String date;

    public Transaction(int id, String description, double amount,
                       TransactionType type, Category category, String date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = date;
    }

    public Transaction(int id, String description, double amount,
                       TransactionType type, String date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.category = Category.OTHER;
        this.date = date;
    }

    public int getId() { return id; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public TransactionType getType() { return type; }
    public Category getCategory() { return category; }
    public String getDate() { return date; }

    public void setDescription(String description) { this.description = description; }
    public void setAmount(double amount) { this.amount = amount; }

    @Override
    public String toString() {
        return "-------------------------------\n" +
                "ID          : " + id + "\n" +
                "Description : " + description + "\n" +
                "Amount      : $" + String.format("%.2f", amount) + "\n" +
                "Type        : " + type + "\n" +
                "Category    : " + category + "\n" +
                "Date        : " + date + "\n" +
                "-------------------------------";
    }
}
