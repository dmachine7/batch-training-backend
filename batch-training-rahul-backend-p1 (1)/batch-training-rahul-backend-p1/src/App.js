import React from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom';
import Login from './Components/Login';
import Dashboard  from './Components/Dashboard';
import ForgotPassword from './Components/ForgotPassword';
import Home from './Components/Home';

function App() {
  return (
    <div className='main'>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/forgotpw" element={<ForgotPassword />} />
      </Routes>
    </div>
  );
}

export default App;
