import React from 'react';
import './Product.css';

const Product = ({product})=> {
    let {name, price} = product;
    return (
        <div className="product">
            <h1>Name: {name}</h1>
            <h2>Price: ${price}</h2>
        </div>
    )
}

export default Product;