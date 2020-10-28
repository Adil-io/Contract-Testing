import React from 'react';
import './AllProducts.css'
import Paper from '@material-ui/core/Paper';

const AllProducts = ({products, showProduct})=> {
  //console.log(products)
  let productList = products.length ? 
    products.map(product => {
      let {id, pid, name} = product;

      return (
        <Paper key={id} className="product-item" onClick={()=> showProduct(pid)} >
          {name}
        </Paper>
      )
    }) :
    <div className="loader-div">
      <div class="lds-ring"><div></div><div></div><div></div><div></div></div>
      <h2>Data is Loading</h2>
    </div>
  
  return (
    <Paper className="product-list">
      {productList}
    </Paper>
  );
}

export default AllProducts;
