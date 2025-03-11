import React from 'react';
import './StoreFront.css';
import StoreItem from './StoreItem/StoreItem';

const StoreFront = () => {
    return (
        <div className='storefront-container'>
            <StoreItem/>
            <StoreItem/>
            <StoreItem/>
        </div>
    );
};

export default StoreFront;