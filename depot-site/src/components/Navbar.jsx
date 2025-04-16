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
        <h1 className="navbar__logo"><Link to="/">/dev/depot</Link></h1>
      </div>

      <div className="navbar__center">
        <Search />
      </div>

      <ul className="navbar__menu">
        <li><Link to="/checkout">Checkout</Link></li>
        <li className="cart-container">
          <Link to="/checkout">
            <FaShoppingCart className="cart-icon" />
            {cartItemCount > 0 && <span className="cart-badge">{cartItemCount}</span>}
          </Link>
        </li>
        <li><Link to="/signin">Sign In</Link></li>
        <li>
          <button className="button" onClick={toggleMode}>Toggle Mode</button>
        </li>
      </ul>
    </nav>
  );
}

export default Navbar;
