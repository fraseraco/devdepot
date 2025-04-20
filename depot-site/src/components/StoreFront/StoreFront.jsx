import React from 'react';
import './StoreFront.css';
import StoreItem from './StoreItem/StoreItem';
import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

const StoreFront = () => {
    const [products, setProducts] = useState([]);
    const [videoCards, setFilteredProducts] = useState([]);
    const [laptops, setLaptops] = useState([]);
    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await fetch('/products/all');
                const data = await response.json();
                setProducts(data);
                console.log('Fetched products:', data);

                const videoCards = data.filter(product => product.category === 'video-card');
                console.log('Filtered video cards:', videoCards);
                setFilteredProducts(videoCards);

                const laptops = data.filter(product => product.category === 'gaming-laptop');
                console.log('Filtered laptops:', laptops);
                setLaptops(laptops);
            } catch (error) {
                console.error('Error fetching products:', error);
            }
        };

        fetchProducts();
    }, []);

    return (
        <div className='storefront-container'>
            <h1>Shop GPUs</h1>
            <StoreItem products = {videoCards}/>
            <h1>Shop Laptops</h1>
            <StoreItem products = {laptops}/>
            <div className="button-container">
                
                <Link to="/products/all" className="show-all-button">
                    Show All Products
                </Link>
            </div>
        </div>
    );
};

export default StoreFront;