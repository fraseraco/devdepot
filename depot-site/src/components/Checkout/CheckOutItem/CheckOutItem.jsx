import React from 'react';
import './CheckOutItem.css';

const CheckOutItem = ({ name, price, quantity }) => {
    return (
        <div className="checkout-item">
            <h3>{name}</h3>
            <p>Price: ${price.toFixed(2)}</p>
            <p>Quantity: {quantity}</p>
        </div>
    );
};

export default CheckOutItem;