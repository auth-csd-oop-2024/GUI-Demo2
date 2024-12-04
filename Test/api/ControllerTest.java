package api;

import org.junit.Test;

import static org.junit.Assert.*;

public class ControllerTest {

    @Test
    public void addExpense1() {
        Expense e1  = new Expense("2024-1-1", "Doctor", 100);
        Expense e2  = new Expense("2024-1-2", "Doctor", 200);
        Expense e3  = new Expense("2024-1-3", "Shopping", 300);
        Expense e4  = new Expense("2024-1-4", "Groceries", 400);

        Controller c = new Controller();
        c.addExpense(e1);
        c.addExpense(e2);
        c.addExpense(e3);
        c.addExpense(e4);

        assertEquals(4, c.getExpenseList().size());
    }

    @Test
    public void addExpense2() {
        Expense e1  = new Expense("2024-1-1", "Doctor", 100);
        Expense e2  = new Expense("2024-1-2", "Doctor", 200);
        Expense e3  = new Expense("2024-1-3", "Shopping", 300);
        Expense e4  =  new Expense("2024-1-3", "Shopping", 300);

        Controller c = new Controller();
        c.addExpense(e1);
        c.addExpense(e2);
        c.addExpense(e3);
        c.addExpense(e4);

        assertEquals(3, c.getExpenseList().size());
    }

    @Test
    public void renoveExpense() {
        Expense e1  = new Expense("2024-1-1", "Doctor", 100);
        Expense e2  = new Expense("2024-1-2", "Doctor", 200);
        Expense e3  = new Expense("2024-1-3", "Shopping", 300);
        Expense e4  =  new Expense("2024-1-3", "Shopping", 300);

        Controller c = new Controller();
        c.addExpense(e1);
        c.addExpense(e2);
        c.addExpense(e3);
        c.addExpense(e4);

        c.removeByCategory("Doctor");
        assertEquals(2, c.getExpenseList().size());
    }
}