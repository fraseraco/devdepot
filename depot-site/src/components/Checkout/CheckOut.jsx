import React, { useEffect, useState } from 'react';
import CheckOutItem from './CheckOutItem/CheckOutItem';
import './CheckOut.css';

const CheckOut = () => {
    const [products, setProducts] = useState([]);
    const [shippingAddress, setShippingAddress] = useState('');
    const [paymentMethod, setPaymentMethod] = useState('');
    const [discountCode, setDiscountCode] = useState('');

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const token = localStorage.getItem('authToken');

                const response = await fetch('/carts/me', {
                    method: 'GET',
                    headers: {
                        'Accept': '*/*',
                        'Authorization': `Bearer ${token}`,
                    },
                });

                const data = await response.json();
                setProducts(data.cartItems || []);
            } catch (error) {
                console.error('Error fetching products:', error);
            }
        };

        fetchProducts();
    }, []);

    const handleCheckout = async () => {
        try {
            const token = localStorage.getItem('authToken');

            const response = await fetch('/checkout', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': '*/*',
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify({
                    shippingAddress: shippingAddress,
                    paymentMethod: paymentMethod,
                    discountCode: discountCode,
                }),
            });

            if (response.ok) {
                const data = await response.json();
                alert('Checkout successful! Thank you for your order.');
            } else {
                alert('Checkout failed. Please try again.');
            }
        } catch (error) {
            console.error('Error during checkout:', error);
        }
    };

    return (
        <div className="checkout">
            <div className="checkout-items">
                {products.map((cartItem, index) => (
                    <CheckOutItem
                        key={index}
                        name={cartItem.product.name}
                        price={cartItem.product.price}
                        quantity={cartItem.quantity}
                        sku={cartItem.product.id}
                    />
                ))}
            </div>
            <div className="checkout-summary">
                <h2>Order Summary</h2>
                <div className="summary-item-container">
                    <div className="checkout-summary-item">
                        <h3>Subtotal</h3>
                        <h3>${products.reduce((sum, cartItem) => sum + cartItem.product.price * cartItem.quantity, 0).toFixed(2)}</h3>
                    </div>
                    <div className="checkout-summary-item">
                        <h3>Shipping</h3>
                        <h3>$19.99</h3>
                    </div>
                    <div className="checkout-summary-item">
                        <h3>Total</h3>
                        <h3>${(products.reduce((sum, cartItem) => sum + cartItem.product.price * cartItem.quantity, 0) + 19.99).toFixed(2)}</h3>
                    </div>
                </div>
                <div className="checkout-inputs">
                    <label>
                        Shipping Address:
                        <input
                            type="text"
                            placeholder="Enter your shipping address"
                            value={shippingAddress}
                            onChange={(e) => setShippingAddress(e.target.value)}
                        />
                    </label>
                    <label>
                        Payment Method:
                        <input
                            type="text"
                            placeholder="Enter your payment method"
                            value={paymentMethod}
                            onChange={(e) => setPaymentMethod(e.target.value)}
                        />
                    </label>
                    <label>
                        Discount Code:
                        <input
                            type="text"
                            placeholder="Enter discount code"
                            value={discountCode}
                            onChange={(e) => setDiscountCode(e.target.value)}
                        />
                    </label>
                </div>
                <button className="checkout-button" onClick={handleCheckout}>
                    Checkout
                </button>
            </div>
        </div>
    );
};

export default CheckOut;