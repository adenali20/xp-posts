package com.xp.xpposts.controller;

import com.xp.xpposts.model.Expense;
import com.xp.xpposts.model.ExpenseDto;
import com.xp.xpposts.repository.ExpenseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/expensesrv")
public class ExpenseController {

    @Autowired
    ExpenseRepository expenseRepository;
    @GetMapping("/expenses")
    public List<Expense> getExpenses(Authentication authentication){
        List<Expense> expenses =  expenseRepository.findAllByUsername(authentication.getName());
        Collections.reverse(expenses);

        return expenses;
    }

    @PostMapping("/expenses/add")
    public List<Expense> addExpense(@RequestBody ExpenseDto expenseDto, Authentication authentication){
        log.info("logged in User : {} ",authentication.getName());
        Expense expense = new Expense();
        expense.setUsername(authentication.getName());
        expense.setAmount(expenseDto.getAmount());
        expense.setDescription(expenseDto.getDescription());
        expenseRepository.save(expense);
        List<Expense> expenses =  expenseRepository.findAllByUsername(authentication.getName());
        Collections.reverse(expenses);
        return expenses;
    }
}
