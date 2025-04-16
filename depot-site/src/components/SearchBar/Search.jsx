import React from 'react';
import './Search.css';

const Search = () => {
  return (
    <div className="Card">
      <div className="CardInner">
        <label htmlFor="search">Search</label>
        <div className="container">
          <div className="Icon">
            🔍
          </div>
          <div className="InputContainer">
            <input
              id="search"
              type="text"
              placeholder="Type to search..."
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Search;
