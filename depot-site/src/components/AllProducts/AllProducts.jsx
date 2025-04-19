import React from 'react';
import { useEffect, useState } from 'react';
import './AllProducts.css';


const AllProducts = () => {
    const [products, setProducts] = useState([]);
    
    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await fetch('/products/all');
                const data = await response.json();
                setProducts(data);
                console.log('Fetched products:', data);
            } catch (error) {
                console.error('Error fetching products:', error);
            }
        };

        fetchProducts();
    }, []);

    return (
        <div className="all-products-container">
            <h1>All Products</h1>
            <div className="products-grid">
                {products.map((product, index) => (
                    <div key={index} className="product-box">
                        <div className="product-inner">
                            {/* Front Side */}
                            <div className="product-front">
                                <img src="/src/resources/6523595.jpg" alt={product.name} />
                                <h3>{product.name}</h3>
                                <p>Price: ${product.price.toFixed(2)}</p>
                            </div>
                            {/* Back Side */}
                            <div className="product-back">
                                <button className="add-to-cart-button">Add to Cart</button>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default AllProducts;