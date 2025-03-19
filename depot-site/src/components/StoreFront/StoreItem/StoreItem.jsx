import React from 'react';
import './StoreItem.css';
import Slider from 'react-slick';
import Tile from '/src/components/Tile/Tile.jsx';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

const StoreItem = () => {
    const settings = {
        dots: true,
        infinite: false,
        speed: 50,
        slidesToShow: 4,
        slidesToScroll: 1,
        
    };

    return (
        <div className="item-container">
            <Slider className="test" {...settings}>
                <Tile />
                <Tile />
                <Tile />
                <Tile />
                <Tile />
            </Slider>
        </div>
    );
};



export default StoreItem;