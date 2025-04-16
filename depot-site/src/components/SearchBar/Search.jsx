import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate for navigation
import './Search.css';

const Search = () => {
    const [query, setQuery] = useState('');
    const [products, setProducts] = useState([]);
    const navigate = useNavigate(); 

   
    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await fetch('/products/all');
                const data = await response.json();
                setProducts(data); 
            } catch (error) {
                console.error('Error fetching products:', error);
            }
        };

        fetchProducts();
    }, []);


    const handleSearch = () => {
        const filtered = products.filter((product) =>
            product.name.toLowerCase().includes(query.toLowerCase())
        );
        navigate('/search', { state: { results: filtered } }); 
    };

    return (
        <div className="search-bar">
            <input
                type="text"
                placeholder="Search..."
                className="search-input"
                value={query}
                onChange={(e) => setQuery(e.target.value)} 
            />
            <button className="search-button" onClick={handleSearch}>
                <i className="fas fa-search"></i> {/* Font Awesome icon */}
            </button>
        </div>
    );
};

export default Search;