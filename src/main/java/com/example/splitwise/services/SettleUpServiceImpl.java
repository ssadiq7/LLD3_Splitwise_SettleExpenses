package com.example.splitwise.services;

import com.example.splitwise.exceptions.InvalidGroupException;
import com.example.splitwise.exceptions.InvalidUserException;
import com.example.splitwise.models.Expense;
import com.example.splitwise.models.GroupExpense;
import com.example.splitwise.models.Transaction;
import com.example.splitwise.models.User;
import com.example.splitwise.repositories.ExpenseRepository;
import com.example.splitwise.repositories.GroupExpenseRepository;
import com.example.splitwise.repositories.GroupRepository;
import com.example.splitwise.repositories.UserRepository;
import com.example.splitwise.strategies.SettleUpStrategy;
import com.example.splitwise.utils.ExpenseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SettleUpServiceImpl implements SettleUpService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupExpenseRepository groupExpenseRepository;
    private final SettleUpStrategy settleUpStrategy;
    private final ExpenseRepository expenseRepository;

    @Autowired
    public SettleUpServiceImpl(UserRepository userRepository, GroupRepository groupRepository, GroupExpenseRepository groupExpenseRepository, SettleUpStrategy settleUpStrategy, ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.groupExpenseRepository = groupExpenseRepository;
        this.settleUpStrategy = settleUpStrategy;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Transaction> settleGroup(long groupId) throws InvalidGroupException {

        groupRepository.findById(groupId).orElseThrow(() -> new InvalidGroupException("Group not found"));

        List<GroupExpense> groupExpenses = groupExpenseRepository.findAllByGroupId(groupId);

        List<Expense> expenses = groupExpenses.stream().map(GroupExpense::getExpense).toList();

        Map<User, Double> aggregatedExpenses = ExpenseUtils.aggregateExpenses(expenses);

        return settleUpStrategy.settleUp(aggregatedExpenses);

    }

    @Override
    public List<Transaction> settleUser(long userId) throws InvalidUserException {

        userRepository.findById(userId).orElseThrow(() -> new InvalidUserException("User not found"));
        List<Expense> expenses = expenseRepository.findNonGroupExpensesForUser(userId);
        Map<User, Double> aggregateExpenses = ExpenseUtils.aggregateExpenses(expenses);
        return settleUpStrategy.settleUp(aggregateExpenses);

    }

}
