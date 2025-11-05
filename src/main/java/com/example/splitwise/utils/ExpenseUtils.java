package com.example.splitwise.utils;

import com.example.splitwise.models.Expense;
import com.example.splitwise.models.ExpenseType;
import com.example.splitwise.models.ExpenseUser;
import com.example.splitwise.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseUtils {

    public static Map<User, Double> aggregateExpenses(List<Expense> expenses) {
        // This will receive list of expenses and aggregate the expenses based on users and the expense type
        // There will be only so many keys as many users are involved in the expenses
        Map<User, Double> aggregatedExpenses = new HashMap<>();

        for(Expense expense : expenses) {

            for(ExpenseUser expenseUser : expense.getExpenseUsers()) {
                User user = expenseUser.getUser();
                double amountForThisExpense = 0d;
                if(expenseUser.getExpenseType().equals(ExpenseType.PAID)) { // The users who paid for this expense will have positive
                    amountForThisExpense = expenseUser.getAmount() * 1;
                }
                else {
                    amountForThisExpense =  expenseUser.getAmount() * -1; // The users who owe for this expense will be marked negative
                }
                aggregatedExpenses.put(user, aggregatedExpenses.getOrDefault(user, 0d) + amountForThisExpense);
            }

        }

        return aggregatedExpenses;

    }
}
