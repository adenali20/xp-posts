package com.xp.xpposts.repository;

import com.xp.xpposts.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    Expense save(Expense entity);

    List<Expense> findAll();

    List<Expense> findAllByUsername(String username);
}
