import React from 'react';
import './HeroSection.css';
import StoreFront from '../StoreFront/StoreFront';
const HeroSection = () => {
    return (
        <div className="hero-container">
            <img src='src\assets\herographic.png'></img>
        <StoreFront />
        </div>

    );
};

export default HeroSection;