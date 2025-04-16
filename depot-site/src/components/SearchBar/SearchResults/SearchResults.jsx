import React from 'react';
import { useLocation } from 'react-router-dom'; // Import useLocation to access passed state
import './SearchResults.css';

const SearchResults = () => {
    const location = useLocation();
    const results = location.state?.results || []; // Get the filtered results from state

    return (
        <div className="search-results-container">
            <h1>Search Results</h1>
            <div className="results-grid">
                {results.length > 0 ? (
                    results.map((product, index) => (
                        <div key={index} className="result-box">
                            <h3>{product.name}</h3>
                            <p>Price: ${product.price.toFixed(2)}</p>
                        </div>
                    ))
                ) : (
                    <h1 className='no-results'>No results found.</h1>
                )}
            </div>
        </div>
    );
};

export default SearchResults;