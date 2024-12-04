package api;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Controller {

    private List<Expense> expenseList;

    public Controller() {
        this.expenseList = new ArrayList<>();
    }


    public void addExpense(Expense e) {

        // checks
        expenseList.add(e);
    }

    public void addExpense(String date, String category, double amount) {
        expenseList.add(new Expense(date, category, amount));
    }


    public double sumByCategory(String category) {
        double sum = 0;
        for (Expense expense : expenseList) {
            if (expense.getCategory().equalsIgnoreCase(category))
                sum += expense.getAmount();
        }
        return sum;
    }


    public void removeByCategory(String category) {
        Iterator<Expense> iterator = expenseList.iterator();
        while (iterator.hasNext()) {
            Expense next = iterator.next();
            if (next.getCategory().equalsIgnoreCase(category))
                iterator.remove();
        }
    }


    public List<Expense> getExpenseList() {
        return expenseList;
    }


    public void saveExpensesToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("expenses.txt", true))) {
            for (Expense expense : expenseList) {
                writer.write(expense.getDate() + "|" + expense.getCategory() + "|" + expense.getAmount());
                writer.newLine();
            }
        }
    }

    public void loadExpenses() throws IOException {
        expenseList.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("expenses.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("\\|");
                addExpense(split[0], split[1], Double.parseDouble(split[2]));
            }
        }
    }

    public void saveExpensesToBinary() {
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("expenses.obj"))) {
            writer.writeObject(expenseList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadExpensesFromBinary() {
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream("expenses.obj"))) {
            expenseList = (List<Expense>) reader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
