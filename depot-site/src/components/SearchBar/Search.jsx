import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate for navigation
import './Search.css';

const Search = () => {
    const [query, setQuery] = useState('');
    const [products, setProducts] = useState([]);
    const navigate = useNavigate(); // Initialize navigation

    // Fetch products from the API
    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await fetch('/products/all');
                const data = await response.json();
                setProducts(data); // Store all products
            } catch (error) {
                console.error('Error fetching products:', error);
            }
        };

        fetchProducts();
    }, []);

    // Handle search button click
    const handleSearch = () => {
        const filtered = products.filter((product) =>
            product.name.toLowerCase().includes(query.toLowerCase())
        );
        navigate('/search', { state: { results: filtered } }); // Navigate to SearchResults with filtered data
    };

    return (
        <div className="search-bar">
            <input
                type="text"
                placeholder="Search..."
                className="search-input"
                value={query}
                onChange={(e) => setQuery(e.target.value)} // Update query state on input change
            />
            <button className="search-button" onClick={handleSearch}>
                Search
            </button>
        </div>
    );
};

export default Search;