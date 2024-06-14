package Assingment.example.task.Service;

import Assingment.example.task.Dto.TaskDto;
import Assingment.example.task.Entity.Task;
import Assingment.example.task.Repository.TaskRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImp implements TaskService{
    @Autowired TaskRepo taskRepo;
    @Override
    public Task createTask(TaskDto taskDto, Long userId) {
        Task task = new Task();
        BeanUtils.copyProperties(taskDto, task);
        task.setUser_id(userId);
        task.setCreated_at(LocalDateTime.now());
        if (task.getStatus() == null) {
            task.setStatus("not_completed");
        }
        Task savedFaculty = taskRepo.save(task);
        return savedFaculty;
    }

    @Override
    public List<Task> getTasksByUserId(Long user_id) {
        List<Task> tasks = taskRepo.findTaskByUserid(user_id);
        if (tasks.isEmpty()) {
          return tasks;
        }
        else{
            return tasks;

        }
    }

    @Override
    public String deleteTask(Long task_id) {
          Optional<Task> tasks= taskRepo.findById(task_id);
        if (tasks.isEmpty()) {
            return ("No tasks found for task with ID: " + task_id);
        }
//        return tasks;
        else{
            taskRepo.deleteById(task_id);
            return tasks.toString();
        }
    }

    @Override
    public String updateTask(TaskDto updatedTask, Long task_id) {
        Optional<Task> tasks= taskRepo.findById(task_id);
        if (tasks.isEmpty()) {
            return ("No tasks found for task with ID: " + task_id);
        }
        else{
            Task existingTask = tasks.get();

            // Update only the non-null fields from updatedTask
            if (updatedTask.getTitle() != null) {
                existingTask.setTitle(updatedTask.getTitle());
            }
            if (updatedTask.getDescription() != null) {
                existingTask.setDescription(updatedTask.getDescription());
            }
            if (updatedTask.getPriority() != null) {
                existingTask.setPriority(updatedTask.getPriority());
            }
            if (updatedTask.getStatus() != null) {
                existingTask.setStatus(updatedTask.getStatus());
            }


            taskRepo.save(existingTask);
            return ("Update Successfully");
        }
    }

    @Override
    public Task updateStatus(String status, Long id) {
        return null;
    }
}
