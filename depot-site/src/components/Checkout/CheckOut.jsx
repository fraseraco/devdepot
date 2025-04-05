import React from 'react';
import Tile from '../Tile/Tile';
import CheckOutItem from './CheckOutItem/CheckOutItem';
import './CheckOut.css';
const CheckOut = () => {
    return (
        <div className="checkout">
            <div>
                <CheckOutItem />
                <CheckOutItem />
                <CheckOutItem />
                <CheckOutItem />
            </div>
            <div className='checkout-summary'>
                <h2>Order Summary</h2>
                <div className='summary-item-container'>
                    <div className='checkout-summary-item'>
                        <h3>Subtotal</h3>
                        <h3>$199.99</h3>
                    </div>
                    <div className='checkout-summary-item'>
                        <h3>Shipping</h3>
                        <h3>$19.99</h3>
                    </div>
                    <div className='checkout-summary-item'>
                        <h3>Total</h3>
                        <h3>$219.98</h3>
                    </div>
                </div>
                <button className='checkout-button'>Checkout</button>   
            </div>
            
        </div>
    );
};

export default CheckOut;