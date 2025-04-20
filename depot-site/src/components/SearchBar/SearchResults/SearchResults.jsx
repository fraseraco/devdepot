import React from 'react';
import { useLocation } from 'react-router-dom'; // Import useLocation to access passed state
import './SearchResults.css';

const SearchResults = () => {
    const location = useLocation();
    const results = location.state?.results || []; // Get the filtered results from state

    return (
        <div className="search-results-container" style={{ textAlign: 'center' }}>
            <t className='search-head'>Search Results</t>
            <div className="results-grid">
                {results.length > 0 ? (
                    results.map((product, index) => (
                        <div key={index} className="result-box">
                            <div className="flip-card">
                                <div className="flip-card-inner">
                                    {/* Front Side */}
                                    <div className="flip-card-front">
                                        <img src={`/src/resources/${product.sku}.jpg`} alt={product.name} />
                                        <h3>{product.name}</h3>
                                        <p>Price: ${product.price.toFixed(2)}</p>
                                    </div>
                                    {/* Back Side */}
                                    <div className="flip-card-back">
                                        <button className="add-to-cart-button">Add to Cart</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    ))
                ) : (
                    <t className='search-head'>No results found.</t>
                )}
            </div>
        </div>
    );
};

export default SearchResults;