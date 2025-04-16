import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css'
import HeroSection from './components/HeroSection/HeroSection';
import Navbar from './components/Navbar';
import StoreFront from './components/StoreFront/StoreFront';
import CheckOut from './components/Checkout/CheckOut';
import SignIn from './components/SignIn/SignIn';
import './styles/theme.css';

function App() {
  return (
    <Router>
      <Navbar />
      <div className='body'>
      <Routes>
          <Route path="/" element={<HeroSection />} />
          <Route path="/store" element={<StoreFront />} />
          <Route path="/checkout" element={<CheckOut/>} />
          <Route path="/signin" element={<SignIn />} />
        </Routes>
      </div>
       
    </Router>
  );

  
}

export default App
