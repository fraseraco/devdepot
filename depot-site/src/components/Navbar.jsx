import React from 'react';
import '../styles/Navbar.css';
import Search from './SearchBar/Search.jsx';
import { Link } from 'react-router-dom';
function Navbar() {
return (
    <nav className="navbar">
        <div className="navbar__logo">
            <h1><Link to="/">/dev/depot</Link></h1>
        </div>
        <ul className="navbar__menu">
            <li><Search /></li>
            
            <li>
                    <Link to="/signin" className="signin-icon">
                        <div className="icon-text-container">
                            <i className="fas fa-sign-in-alt"></i> 
                            <span>Sign In</span>
                        </div>
                    </Link>
                </li>
            
            <li>
                    <Link to="/checkout" className="cart-icon">
                        <i className="fas fa-shopping-cart"></i> 
                    </Link>
                </li>
            
        </ul>
    </nav>
);
}

export default Navbar;