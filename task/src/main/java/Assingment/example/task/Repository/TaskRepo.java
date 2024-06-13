package Assingment.example.task.Repository;

import Assingment.example.task.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Task , Long> {

    @Query("select t from Task t where t.user_id = :userId")
    List<Task> findTaskByUserid(Long userId);
}
