import React from 'react';
import './Product.css';

const Product = ({product})=> {
    let {name, price, imgUrl} = product;
    return (
        <div className="product">
            <img src={imgUrl} alt="Not Available" />
            <h1>Name: {name}</h1>
            <h2>Price: &#8377;{price}</h2>
        </div>
    )
}

export default Product;