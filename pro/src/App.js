
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Login from './Pages/Login';
import Register from './Pages/Register';
import Todo from './Pages/Todo';

function App() {
  return (
    <div className="App">
        <BrowserRouter>
          <Routes>
            <Route path="/register" element={<Register />} />
            <Route path="/" element={<Login />} />
            <Route path="/todo" element={<Todo />} />
          </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
