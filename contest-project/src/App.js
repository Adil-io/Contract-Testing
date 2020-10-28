import React, { Component } from 'react'
import axios from 'axios';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';

import './App.css';
import AllProducts from './components/AllProducts/AllProducts';
import Product from './components/Product/Product';

class App extends Component {
  state = {
    products: [],
    selectedProduct: null,
    error: null
  }

  componentDidMount() {
    axios.get("http://localhost:8080/allProducts")
      .then(resolve => {
        //console.log(resolve.data)
        this.setState({
          products: resolve.data
        })
      })
      .catch(err => {
        this.setState({
          error: err
        })
      })
  }

  fetchProduct = (pid)=> {
    axios.get(`http://localhost:8080/product?pid=${pid}`)
      .then(resolve => {
        this.setState({
          selectedProduct: resolve.data
        })
      })
      .catch(err => console.log(err))
  }

  showProduct = (pid)=> {
    //let product = this.state.products.filter(product => product.id === id)[0];
    this.fetchProduct(pid);
  }

  render() {
    return (
      <div className="root">
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <Paper className="heading">Product Listing Page</Paper>
          </Grid>
          <Grid item xs={4}>
            <AllProducts products={this.state.products} showProduct={this.showProduct} />
          </Grid>
          <Grid item xs={8}>
            <Paper className="selected-product">
              {
                this.state.selectedProduct ? 
                <Product product={this.state.selectedProduct} /> 
                :
                <div className="nothing-selected">
                  <h3>Nothing is Selected</h3>
                </div>
              }
            </Paper>
          </Grid>
        </Grid>
      </div>
    )
  }
}

export default App;
