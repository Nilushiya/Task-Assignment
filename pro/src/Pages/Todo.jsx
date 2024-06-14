import axios from 'axios';
import React, { useEffect, useState } from 'react'
import TaskForm from '../Components/TaskForm';
import TaskList from '../Components/TaskList';
import '../Components/Style/todo.scss'
import { decodeToken } from '../Context/UserContext';
const Todo = () => {
    const [tasks, setTasks] = useState([]);
    const[userId , setUserId] = useState('');
    const[username , setName] = useState('');

useEffect(() => {
    const decode = decodeToken();
    if(decode){
     const user = decode.userId;
     const userName = decode.name;
     setName(userName)
     setUserId(user);
    }
    else{
     const user = null;
     const userName = "there"
     setName(userName)
     setUserId(user);
    }
  },[])

  
  useEffect(() => {
    if (userId) {
      loadTasks();
    }
  }, [userId]);

  const loadTasks = async () => {
    console.log("userId:",userId);
    try {
      const response = await axios.get(`http://localhost:9090/api/v1/task/get/${userId}`);
      console.log("add:",response.data)
      setTasks(response.data);
    } catch (error) {
      console.error('Error loading tasks:', error);
    }
  };

  const addTask = async (newTask) => {
    console.log("new:",newTask);
    console.log("userId:",userId);
    try {
      const response = await axios.post(`http://localhost:9090/api/v1/task/add/${userId}`,newTask);
      console.log("add:",response.data)
      setTasks([...tasks, response.data]);
    } catch (error) {
      console.error('Error adding task:', error);
      // Handle error, show alert, etc.
    }
  };

  const updateTask = async (taskId, updatedFields) => {
    try {
      await axios.put(`http://localhost:9090/api/v1/task/update/${taskId}`, updatedFields, {
        headers: { 'Content-Type': 'application/json' }
      });
      loadTasks();
    } catch (error) {
      console.error('Error updating task:', error);
      // Handle error, show alert, etc.
    }
  };

  const deleteTask = async (taskId) => {
    try {
      await axios.delete(`http://localhost:9090/api/v1/task/delete/${taskId}`);
      setTasks(tasks.filter(task => task.task_id !== taskId));
    } catch (error) {
      console.error('Error deleting task:', error);
      // Handle error, show alert, etc.
    }
  };
  return (
    <div className='todo'>
      <h1>{username}</h1>
      <h2>This is your Task Management</h2>
         
        <TaskForm addTask={addTask} />
        <TaskList tasks={tasks} updateTask={updateTask} deleteTask={deleteTask} loadTasks = {loadTasks}/>
    </div>
  )
}

export default Todo