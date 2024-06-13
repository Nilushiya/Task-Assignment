import React, { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEnvelope, faLock, faUser } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';
import { checkRegister, decodeToken } from '../Context/UserContext.jsx';
import '../Components/Style/Register.scss'; // Import the SCSS file

const Register = () => {
  const [formdata , setFormdata] = useState({
    customerName:'',
    email : '',
    password : '',
    confirmPassword:'',
    userType: 'USER'
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (formdata.password === formdata.confirmPassword) {
        const response = await checkRegister(formdata); 
        const token = response.data; 

        localStorage.setItem('token', token);
        const decoded = decodeToken();
        const userType = decoded.userType;

        // Redirect or handle success state
        window.location.href = '/todo';
      } else {
        alert('Confirm password does not match');
      }
    } catch(err) {
      alert('Error: ' + err);
    }

    // Clear form data after submission
    setFormdata({
      customerName: '',
      email: '',
      password: '',
      confirmPassword: '',
      userType: 'USER' // Reset userType if needed
    });
  };

  return (
    <div className='register'>
      <div className="registerbox">
        <div className="heder">
          <h1>Register</h1>
        </div>
        <div className="registerbody">
          <form action="" onSubmit={handleSubmit}>
            <div className="name">
              <FontAwesomeIcon icon={faUser} className='envelope' size='xl'/> 
              <input 
                type='text'
                value={formdata.customerName}
                name='customerName'
                placeholder='Enter your name'
                onChange={(e) => setFormdata({...formdata, customerName:e.target.value})}
                required
              />
            </div>
            <div className="mail">
              <FontAwesomeIcon icon={faEnvelope} className='envelope' size='xl'/> 
              <input 
                type='email'
                value={formdata.email}
                name='email'
                placeholder='Enter your email'
                onChange={(e) => setFormdata({...formdata, email:e.target.value})}
                required
              />
            </div>
            <div className="pass">
              <FontAwesomeIcon icon={faLock} className='lock' size='xl'/> 
              <input 
                type='password'
                value={formdata.password}
                name='password'
                placeholder='Enter your password'
                onChange={(e) => setFormdata({...formdata, password:e.target.value})}
                required
              />
            </div>
            <div className="conpass">
              <FontAwesomeIcon icon={faLock} className='lock' size='xl'/> 
              <input 
                type='password'
                value={formdata.confirmPassword}
                name='confirmPassword'
                placeholder='Enter your confirmPassword'
                onChange={(e) => setFormdata({...formdata, confirmPassword:e.target.value})}
                required
              />
            </div>
            <button type='submit'>Submit</button>
            <p style={{color:"white"}}>Already have an account?<Link to='/' className='log'>Login</Link></p>
          </form> 
        </div>
      </div>
    </div>
  );
};

export default Register;
