import React from 'react';
import '../styles/Navbar.css';
import Search from './SearchBar/Search.jsx';
import { Link } from 'react-router-dom';
import { FaShoppingCart } from 'react-icons/fa';

function Navbar() {
  const cartItemCount = 3;

  const toggleMode = () => {
    const current = document.body.classList.contains('dark') ? 'dark' : 'light';
    document.body.classList.remove(current);
    document.body.classList.add(current === 'dark' ? 'light' : 'dark');
  };

  return (
    <nav className="navbar">
  <div className="navbar__left">
    <h1><Link to="/">/dev/depot</Link></h1>
  </div>

  <div className="navbar__center">
    <Search />
  </div>

  <div className="navbar__right">
    <Link to="/checkout" className="neumorphic-btn">Checkout</Link>
    <div className="cart-container">
      <Link to="/checkout">
        <FaShoppingCart className="cart-icon" />
        {cartItemCount > 0 && (
          <span className="cart-badge">{cartItemCount}</span>
        )}
      </Link>
    </div>
    <Link to="/signin" className="neumorphic-btn">Sign In</Link>
    <button className="neumorphic-btn" onClick={toggleMode}>Toggle Mode</button>
  </div>
</nav>

  );
}

export default Navbar;
