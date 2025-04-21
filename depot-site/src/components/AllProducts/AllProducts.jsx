import React, { useEffect, useState } from 'react';
import './AllProducts.css';

const AllProducts = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await fetch('/products');
                const data = await response.json();
                setProducts(data);
                console.log('Fetched products:', data);
            } catch (error) {
                console.error('Error fetching products:', error);
            }
        };

        fetchProducts();
    }, []);

    const handleAddToCart = async (productId) => {
        try {
            const token = localStorage.getItem('authToken');

            if (!token) {
                alert('You must be logged in to add items to the cart.');
                return;
            }

            const response = await fetch('/carts/items', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': '*/*',
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify({
                    productId: productId,
                    quantity: 1,
                }),
            });

            if (response.ok) {
                const data = await response.json();
                console.log('Item added to cart:', data);
                alert('Item added to cart successfully!');
            } else {
                console.error('Failed to add item to cart:', response.status);
                alert('Failed to add item to cart. Please try again.');
            }
        } catch (error) {
            console.error('Error adding item to cart:', error);
        }
    };

    return (
        <div className="all-products-container">
            <h1>All Products</h1>
            <div className="products-grid">
                {products.map((product, index) => (
                    <div key={index} className="product-box">
                        <div className="product-inner">
                            <div className="product-front">
                                <img src={`/src/resources/${product.sku}.jpg`} alt={product.name} />
                                <h3>{product.name}</h3>
                                <p>Price: ${product.price.toFixed(2)}</p>
                            </div>
                            <div className="product-back">
                                <button
                                    className="add-to-cart-button"
                                    onClick={() => handleAddToCart(product.id)} 
                                >
                                    Add to Cart
                                </button>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default AllProducts;