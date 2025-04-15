import React from 'react';
import './StoreItem.css';
import Slider from 'react-slick';
import Tile from '/src/components/Tile/Tile.jsx';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

const StoreItem = ({products}) => {
    var settings = {
        dots: true,
        infinite: false,
        speed: 500,
        slidesToShow: 6,
        slidesToScroll: 4,
        initialSlide: 0,
        responsive: [
          {
            breakpoint: 1024,
            settings: {
              slidesToShow: 3,
              slidesToScroll: 3,
              infinite: true,
              dots: true
            }
          },
          {
            breakpoint: 600,
            settings: {
              slidesToShow: 2,
              slidesToScroll: 2,
              initialSlide: 2
            }
          },
          {
            breakpoint: 480,
            settings: {
              slidesToShow: 1,
              slidesToScroll: 1
            }
          }
        ]
      };
      return (
        <div className="store-item">
            <div className="slider-container">
                <Slider {...settings}>
                    {products.map((product, index) => (
                        <div key={index} className="slider-div">
                            <img src= '/src/resources/6523595.jpg'/>
                            <h3>{product.name}</h3>
                            <p>Price: ${product.price.toFixed(2)}</p>
                        </div>
                    ))}
                </Slider>
            </div>
        </div>
    );
    }
    
    
    
    
    
    


export default StoreItem;