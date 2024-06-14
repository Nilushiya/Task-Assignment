package Assingment.example.task.Service;

import Assingment.example.task.Dto.TaskDto;
import Assingment.example.task.Entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    Task createTask(TaskDto task, Long userId);

    List<Task> getTasksByUserId(Long user_id);

    String deleteTask(Long id);

    String updateTask(TaskDto updatedTask, Long id);

    Task updateStatus(String status, Long id);
}
