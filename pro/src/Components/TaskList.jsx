import React, { useEffect } from 'react'
import './Style/todo.scss'
const TaskList = ({ tasks, updateTask, deleteTask ,loadTasks}) => {

    useEffect(() => {
        loadTasks();
      }, [loadTasks]);

    const handleUpdate = (taskId, newTitle, newDescription) => {
        updateTask(taskId, { title: newTitle, description: newDescription });
      };
    
      const handleDelete = (taskId) => {
        deleteTask(taskId);
      };
  return (
    <div className="task-list">
    {tasks.length === 0 ? (
      <p>No tasks available</p>
    ) : (
      <table>
        <thead>
          <tr>
            <th>Title</th>
            <th>Description</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {tasks.map(task => (
            <tr key={task.task_id}>
              <td><input type="text" defaultValue={task.title} onChange={(e) => handleUpdate(task.task_id, e.target.value, task.description)} /></td>
              <td><input type="text" defaultValue={task.description} onChange={(e) => handleUpdate(task.task_id, task.title, e.target.value)} /></td>
              <td>{task.status}</td>
              <td>
                <button onClick={() => handleDelete(task.task_id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    )}
  </div>
  )
}

export default TaskList