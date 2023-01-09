package com.example.apitodo.Controllers;

import com.example.apitodo.Models.Task;
import com.example.apitodo.Repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {
    @Autowired
    private ToDoRepository toDoRepository;

    // Index
    @GetMapping("/")
    public String index() {
        return "Bienvenido a To-Do APPI!";
    }

    // Obtener todas
    @GetMapping("/api/tasks")
    public List<Task> listTasks() {
        return toDoRepository.findAll();
    }

    // Obtener por id
    @GetMapping("api/tasks/show/{id}")
    public ResponseEntity<Task> showTask(@PathVariable Long id) {
        if (!toDoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Task response = toDoRepository.findById(id).get();

        return ResponseEntity.ok(response);
    }

    // Agregar
    @PostMapping("api/tasks/add")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task response = toDoRepository.save(task);

        return ResponseEntity.ok(response);
    }

    // Editar
    @PutMapping("/api/tasks/edit/{id}")
    public ResponseEntity<Task> editTask(@PathVariable Long id, @RequestBody Task task) {
        if (!toDoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

//        Task response = toDoRepository.save(task);
        Task taskToEdit = toDoRepository.findById(id).get();
        taskToEdit.setTitle(task.getTitle());
        taskToEdit.setDescription(task.getDescription());
         toDoRepository.save(taskToEdit);

        return ResponseEntity.ok(taskToEdit);
    }

    // Eliminar
    @DeleteMapping("api/tasks/delete/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        if (!toDoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        toDoRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
