package Assingment.example.task.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String user_name;
    private String user_type;
    private String email;
    private String password;
    private LocalDateTime created_at;
}
