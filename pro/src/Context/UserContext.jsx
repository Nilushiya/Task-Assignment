import axios from 'axios'
import {jwtDecode} from 'jwt-decode';
// import { useNavigate } from 'react-router-dom';
import React, { createContext, useContext, useEffect, useState } from 'react';

const BASE_URL = 'http://localhost:9090/api/v1';


// Register
export const checkRegister = async(formData) => {

    try {
      const response = await axios.post(`${BASE_URL}/signup`, formData);
      return response
    } 
    catch (error) {
      console.error("Email or Password not match", error);
    }
  }
  // Login
export const checklogin = async(formData) => {
    try {
      const response = await axios.post(`${BASE_URL}/login`, formData);
      return response
    } 
    catch (error) {
      console.error("Email or Password not match", error);
    }
  }
  
  
    // Token
  export const decodeToken = () => {
    try{
      const token = localStorage.getItem('token');
      const decodeToken = jwtDecode(token);
      return decodeToken;
    }
    catch(err){
      return null;
    }
    
    }

    
  
  
  
    const UserContext = createContext();
  
  export const UserProvider = ({ children }) => {
    const [userRole, setUserRole] = useState(null);
    const [loading, setLoading] = useState(true);
  
    useEffect(() => {
      const token = localStorage.getItem('token');
      if (token) {
        const decodedToken = jwtDecode(token);
        setUserRole(decodedToken.role);
      }
      setLoading(false);
    }, []);
  
    return (
      <UserContext.Provider value={{ userRole, setUserRole, loading }}>
        {children}
      </UserContext.Provider>
    );
  };
  
  export const useUserContext = () => useContext(UserContext);