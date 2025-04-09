import React, { useEffect, useState } from 'react';
import CheckOutItem from './CheckOutItem/CheckOutItem';
import './CheckOut.css';

const CheckOut = () => {
    const [products, setProducts] = useState([]);

    // Fetch product data from the API
    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await fetch('/products/all'); // Replace with your API URL
                const data = await response.json();
                setProducts(data); // Assuming the API returns an array of products
            } catch (error) {
                console.error('Error fetching products:', error);
            }
        };

        fetchProducts();
    }, []);

    return (
        <div className="checkout">
            <div>
                {/* Map over the products and pass data to CheckOutItem */}
                {products.map((product, index) => (
                    <CheckOutItem
                        key={index}
                        name={product.name}
                        price={product.price}
                        quantity={product.quantity}
                    />
                ))}
            </div>
            <div className='checkout-summary'>
                <h2>Order Summary</h2>
                <div className='summary-item-container'>
                    <div className='checkout-summary-item'>
                        <h3>Subtotal</h3>
                        <h3>${products.reduce((sum, product) => sum + product.price * product.quantity, 0).toFixed(2)}</h3>
                    </div>
                    <div className='checkout-summary-item'>
                        <h3>Shipping</h3>
                        <h3>$19.99</h3>
                    </div>
                    <div className='checkout-summary-item'>
                        <h3>Total</h3>
                        <h3>${(products.reduce((sum, product) => sum + product.price * product.quantity, 0) + 19.99).toFixed(2)}</h3>
                    </div>
                </div>
                <button className='checkout-button'>Checkout</button>   
            </div>
        </div>
    );
};

export default CheckOut;