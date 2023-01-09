package com.example.apitodo.Repository;

import com.example.apitodo.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<Task, Long> {
}
