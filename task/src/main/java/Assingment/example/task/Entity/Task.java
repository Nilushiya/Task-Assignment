package Assingment.example.task.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
//@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;
    private String title;
    private String description;
    @Column(columnDefinition = "varchar(20) not null default 'not_completed'")
    private String status;
    private LocalDateTime created_at;
    private Integer priority;
    private Long user_id;


    public Task() {
        this.created_at = LocalDateTime.now();

    }
}
