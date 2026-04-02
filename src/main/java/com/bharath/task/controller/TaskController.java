package com.bharath.task.controller;

import com.bharath.task.dto.TaskDTO;
import com.bharath.task.entity.Task;
import com.bharath.task.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    public final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
//        String responseBody = String.valueOf(taskService.getAllTasks());
//        return new ResponseEntity<>(responseBody, HttpStatus.OK);
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
//        String responseBody = taskService.getTaskById(id).toString();
//        return new ResponseEntity<>(responseBody, HttpStatus.OK);
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());

        String responseBody = taskService.createTask(task);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());

        String responseBody = taskService.updateTask(id, task);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        String responseBody = taskService.deleteTask(id);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
