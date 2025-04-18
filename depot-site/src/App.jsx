import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css'
import HeroSection from './components/HeroSection/HeroSection';
import Navbar from './components/Navbar';
import StoreFront from './components/StoreFront/StoreFront';
import CheckOut from './components/Checkout/CheckOut';
import SignIn from './components/SignIn/SignIn';
import AllProducts from './components/AllProducts/AllProducts';
import SearchResults from './components/SearchBar/SearchResults/SearchResults';
import TerminalPage from './components/Terminals/TerminalPage';

function App() {
  return (
    <div className='app-container'>
    <Router>
      <Navbar />
      <div className='router-container'>
      <Routes>
          <Route path="/" element={<HeroSection />} />
          <Route path="/store" element={<StoreFront />} />
          <Route path="/checkout" element={<CheckOut/>} />
          <Route path="/signin" element={<SignIn />} />
          <Route path="/products/all" element={<AllProducts />} />
          <Route path="/search" element={<SearchResults />} />
          <Route path="/terminal" element={<TerminalPage />} />
          
        </Routes>
      </div>
       
    </Router>
    </div>
  );

  
}

export default App
