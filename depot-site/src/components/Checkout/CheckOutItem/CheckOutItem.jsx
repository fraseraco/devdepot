import React from 'react';
import './CheckOutItem.css';

const CheckOutItem = ({ name, price, quantity, sku}) => {
    return (
        <div className="checkout-item">
            <img src={`/src/resources/${sku}.jpg`}/>
            <div className="details-box">
            <h3>{name}</h3>
            <p>Price: ${price.toFixed(2)}</p>
            </div>
        </div>
    );
};

export default CheckOutItem;