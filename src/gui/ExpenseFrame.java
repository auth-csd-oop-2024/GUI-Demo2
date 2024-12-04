package gui;

import api.Controller;
import api.Expense;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ExpenseFrame {

    private JFrame frame;
    private JTextField dateField, categoryField, amountField;
    JButton addButton, loadButton;

    private JTable table;
    private DefaultTableModel tableModel;

    Controller controller;


    public ExpenseFrame() {
        frame = new JFrame("Personal Finance Tracker");
        frame.setSize(500, 400);
        frame.setLayout(new FlowLayout());

        JLabel dateLabel = new JLabel("Date (yyyy-mm-dd):");
        frame.add(dateLabel);
        dateField = new JTextField();
        dateField.setPreferredSize(new Dimension(100, 20));
        frame.add(dateField);

        JLabel categoryLabel = new JLabel("Category:");
        frame.add(categoryLabel);
        categoryField = new JTextField();
        categoryField.setPreferredSize(new Dimension(100, 20));
        frame.add(categoryField);

        JLabel amountLabel = new JLabel("Amount:");
        frame.add(amountLabel);
        amountField = new JTextField();
        amountField.setPreferredSize(new Dimension(100, 20));
        frame.add(amountField);

        addButton = new JButton("Add Expense");
        frame.add(addButton);

        loadButton = new JButton("Load Expenses");
        frame.add(loadButton);

        tableModel = new DefaultTableModel(new String[]{"Date", "Category", "Amount"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 180, 440, 150);
        frame.add(scrollPane);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                String category = categoryField.getText();
                double amount = Double.parseDouble(amountField.getText());

                controller.addExpense(date, category, amount);
                tableModel.addRow(new Object[]{date, category, amount});


            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.loadExpenses();
                    for (Expense expense : controller.getExpenseList()) {
                        tableModel.addRow(new Object[]{expense.getDate(), expense.getCategory(), expense.getAmount()});
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });


        controller = new Controller();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                int choice = JOptionPane
                        .showConfirmDialog(frame, "Are you sure?", "Confirm EXIT", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    try {
                        controller.saveExpensesToFile();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();
                    System.exit(0);
                }

            }
        });


        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new ExpenseFrame();
    }
}
