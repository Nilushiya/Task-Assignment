import React, { useEffect, useState } from 'react'
import './Style/todo.scss'
const TaskList = ({ tasks, updateTask, deleteTask , loadTasks}) => {
  console.log("Task : ", tasks);
    const [updatedTasks, setUpdatedTasks] = useState({});


  const handleUpdate = (taskId, newTitle, newDescription, newPriority ,newStatus) => {
    const updatedTask = { ...updatedTasks };
    updatedTask[taskId] = { title: newTitle, description: newDescription, priority: newPriority , status: newStatus};
    setUpdatedTasks(updatedTask);
  };

  const saveUpdatedTasks = async () => {
    const tasksToUpdate = Object.keys(updatedTasks).map(taskId => ({
      taskId,
      ...updatedTasks[taskId]
    }));

    for (const task of tasksToUpdate) {
      await updateTask(task.taskId, task);
    }

    loadTasks();
    setUpdatedTasks({});
  };


  const handleDelete = (taskId) => {
    deleteTask(taskId);
  };
  return (
    <div className="task-list">
     {tasks && tasks.length > 0 ? ( 
        <table>
          <thead>
            <tr>
              <th>Title</th>
              <th>Description</th>
              <th>Priority</th>
              <th>Status</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {tasks && tasks.length > 0 && tasks.map((task) => (
              <tr key={task.task_id}>
                <td><input type="text" defaultValue={task.title} onChange={(e) => handleUpdate(task.task_id, e.target.value, task.description, task.priority)} /></td>
                <td><input type="text" defaultValue={task.description} onChange={(e) => handleUpdate(task.task_id, task.title, e.target.value, task.priority)} /></td>
                <td><input type="text" defaultValue={task.priority} onChange={(e) => handleUpdate(task.task_id, task.title, task.description, e.target.value)} /></td>
                <td>
                  <select
                    defaultValue={task.status}
                    onChange={(e) => handleUpdate(task.task_id, task.title, task.description, task.priority, e.target.value)}
                  >
                    <option value="not_completed">Not Completed</option>
                    <option value="completed">Completed</option>
                  </select>
                </td>
                <td>
                  <button onClick={() => saveUpdatedTasks()}>Update</button>
                  <button onClick={() => handleDelete(task.task_id)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No tasks available</p>
      )}


  </div>
  )
}

export default TaskList