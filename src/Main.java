import api.Controller;
import api.Expense;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Expense e1  = new Expense("2024-1-1", "Doctor", 100);
        Expense e2  = new Expense("2024-1-2", "Doctor", 200);
        Expense e3  = new Expense("2024-1-3", "Shopping", 300);
        Expense e4  = new Expense("2024-1-4", "Groceries", 400);

        Controller c = new Controller();
        c.addExpense(e1);
        c.addExpense(e2);
        c.addExpense(e3);
        c.addExpense(e4);

        c.saveExpensesToFile();

        c.loadExpenses();

        System.out.println(c.getExpenseList().size());

        // binary
        c.saveExpensesToBinary();
        c.loadExpensesFromBinary();
        System.out.println(c.getExpenseList().size());


    }
}