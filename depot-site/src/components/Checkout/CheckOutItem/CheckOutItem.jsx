import React from 'react';
import './CheckOutItem.css';

const CheckOutItem = ({ item, onRemove }) => {
    
    return (
        
        <div className="checkout-item">
            
            <img src="https://cdn-icons-png.flaticon.com/512/618/618310.png" alt="placeholder" />
            <div>
                <h3>Gaming PC</h3>
                <h3>49.99</h3>
            </div>
        </div>

    );
};

export default CheckOutItem;