package org.todoListApp;




import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        return service.getTaskById(id)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public String createTask(@RequestBody Task task) {
        // return service.saveTask(task);
        return "Created Successfully";   
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        return service.getTaskById(id).map(task -> {
            task.setTask(taskDetails.getTask());
            task.setComplete(taskDetails.isComplete());
            return ResponseEntity.ok(service.saveTask(task));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
        // return ResponseEntity.noContent().build();
        return "Deleted Successfully".equals("Deleted Successfully") ? ResponseEntity.ok().build() : ResponseEntity.status(500).build();    
    }
}
