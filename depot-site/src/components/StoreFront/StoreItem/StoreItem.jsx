import React from 'react';
import './StoreItem.css';
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

const StoreItem = ({ products }) => {
    var settings = {
      dots: true,
      infinite: false,
      speed: 500,
      slidesToShow: 5,
      slidesToScroll: 4,
      initialSlide: 0,
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesToShow: 3,
            slidesToScroll: 3,
            infinite: true,
            dots: true,
          },
        },
        {
          breakpoint: 600,
          settings: {
            slidesToShow: 2,
            slidesToScroll: 2,
            initialSlide: 2,
          },
        },
        {
          breakpoint: 480,
          settings: {
            slidesToShow: 1,
            slidesToScroll: 1,
          },
        },
      ],
    };

    // Add spacing between slides
    const sliderStyle = {
      '.slick-slide': {
        margin: '0 10px', // Adjust the margin as needed
      },
    };

    return (
        <div className="store-item">
            <div className="slider-container">
                <Slider {...settings}>
                    {products.map((product, index) => (
                        <div key={index} className="slider-div">
                            <img
                                src={`/src/resources/${product.sku}.jpg`}
                                alt={product.name}
                            />
                            <h4>{product.name}</h4>
                            <p>Price: ${product.price.toFixed(2)}</p>
                            <button className="add-to-cart-button">
                                Add to Cart
                            </button>
                        </div>
                    ))}
                </Slider>
            </div>
        </div>
    );
};

export default StoreItem;