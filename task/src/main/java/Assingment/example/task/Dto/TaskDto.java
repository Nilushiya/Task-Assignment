package Assingment.example.task.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long task_id;
    private String title;
    private String description;
    private Integer priority;
    private String status;
    private LocalDateTime created_at;
    private Long user_id;
}
