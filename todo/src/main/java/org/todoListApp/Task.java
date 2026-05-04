package org.todoListApp;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String task;
    private boolean isComplete;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Task() {}
}
