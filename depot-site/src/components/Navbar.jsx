import React from 'react';
import '../styles/Navbar.css';
import Search from './SearchBar/Search.jsx';
import { Link } from 'react-router-dom';

function Navbar() {
  function toggleMode() {
    const current = document.body.classList.contains('dark') ? 'dark' : 'light';
    document.body.classList.remove(current);
    document.body.classList.add(current === 'dark' ? 'light' : 'dark');
  }

  return (
    <nav className="navbar">
      <div className="navbar__logo">
        <h1><Link to="/">/dev/depot</Link></h1>
      </div>
      <ul className="navbar__menu">
        <li><Search /></li>
        <li><Link to="/checkout">Checkout</Link></li>
        <li><Link to="/signin">Sign In</Link></li>
        <li>
          <button className="button" onClick={toggleMode}>Toggle Mode</button>
        </li>
      </ul>
    </nav>
  );
}

export default Navbar;
