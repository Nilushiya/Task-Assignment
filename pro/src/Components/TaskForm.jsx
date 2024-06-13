import React, { useState } from 'react'
import './Style/todo.scss'
const TaskForm = ({ addTask }) => {
    const [taskTitle, setTaskTitle] = useState('');
    const [taskDescription, setTaskDescription] = useState('');
    const[taskPriority , setTaskePriority] = useState('');
    const handleSubmit = (e) => {
      e.preventDefault();
      addTask({ title: taskTitle, description: taskDescription ,priority: taskPriority});
      setTaskTitle('');
      setTaskDescription('');
      setTaskePriority('');
    };
  return (
    <form className="task-form" onSubmit={handleSubmit}>
    <input
      type="text"
      placeholder="Title"
      value={taskTitle}
      onChange={(e) => setTaskTitle(e.target.value)}
      required
    />
    <input
      type="text"
      placeholder="Description"
      value={taskDescription}
      onChange={(e) => setTaskDescription(e.target.value)}
      required
    />
    <input
      type="number"
      placeholder="Priority"
      value={taskPriority}
      onChange={(e) => setTaskePriority(e.target.value)}
      required
    />
    <button type="submit">Add Task</button>
  </form>
  )
}

export default TaskForm