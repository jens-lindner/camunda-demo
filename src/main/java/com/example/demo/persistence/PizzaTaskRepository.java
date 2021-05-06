package com.example.demo.persistence;

import com.example.demo.persistence.model.PizzaTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaTaskRepository extends JpaRepository<PizzaTask, String> {
}
