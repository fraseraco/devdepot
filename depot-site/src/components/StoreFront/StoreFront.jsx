import React from 'react';
import './StoreFront.css';
import StoreItem from './StoreItem/StoreItem';
import { useEffect, useState } from 'react';

const StoreFront = () => {
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
        <div className='storefront-container'>
            <StoreItem/>
            <StoreItem/>
            
        </div>
    );
};

export default StoreFront;