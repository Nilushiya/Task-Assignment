package Assingment.example.task.Controller;

import Assingment.example.task.Dto.TaskDto;
import Assingment.example.task.Entity.Task;
import Assingment.example.task.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("api/v1/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/add/{user_id}")
    public ResponseEntity<Task> addTask(@RequestBody TaskDto taskDto, @PathVariable Long user_id) {
        return ResponseEntity.status(201).body(taskService.createTask(taskDto,user_id));
    }
      @GetMapping("/get/{user_id}")
      public ResponseEntity<String> getTasksByUserId(@PathVariable Long user_id) {
          String tasks = taskService.getTasksByUserId(user_id);
          if (tasks.isEmpty()) {
              ResponseEntity<String> body = ResponseEntity.status(HttpStatus.NO_CONTENT)
                      .body("No tasks found for user with ID: " + user_id);
              return body;
          } else {
              return ResponseEntity.ok(tasks.toString());
          }
      }

    @DeleteMapping("/delete/{task_id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long task_id) {
        String tasks =  taskService.deleteTask(task_id);
        if (tasks.isEmpty()) {
            ResponseEntity<String> body = ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No tasks found for task with ID: " + task_id);
            return body;
        } else {
            return ResponseEntity.ok(tasks.toString());
        }
    }

        @PutMapping("/update/{task_id}")
    public ResponseEntity<String> updateTask(@RequestBody TaskDto taskDto, @PathVariable Long task_id) {
            String tasks =  taskService.updateTask(taskDto , task_id);
//            if (tasks.isEmpty()) {
//                ResponseEntity<String> body = ResponseEntity.status(HttpStatus.NO_CONTENT)
//                        .body("No tasks found for task with ID: " + task_id);
//                return body;
//            } else {
//                return ResponseEntity.ok(tasks.toString());
//            }
            return ResponseEntity.ok(tasks);
    }

    @PutMapping("/update-status/{task_id}")
    public ResponseEntity<Task> updateStatus(@RequestBody String status, @PathVariable Long task_id) {
        return ResponseEntity.ok(taskService.updateStatus(status, task_id));
    }

}




