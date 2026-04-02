package com.bharath.task.service;

import com.bharath.task.entity.Task;
import com.bharath.task.exception.ResourceNotFoundException;
import com.bharath.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Pagination
    public Page<Task> getAllTasksPages(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return taskRepository.findAll(pageable);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task Not Found"));
    }

    public String createTask(Task task) {
         taskRepository.save(task);
         return "Task Created";
    }

    public String updateTask(Long id, Task taskDetails) {
        Task task = getTaskById(id);

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        taskRepository.save(task);
        return "Task Updated";
    }

    public String deleteTask(Long id){
        Task task = getTaskById(id);
        taskRepository.delete(task);
        return "Task Deleted";
    }


}
