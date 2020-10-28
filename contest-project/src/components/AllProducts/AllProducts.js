import React, {useState} from 'react';
import './AllProducts.css'

import { Paper, List, ListItem, ListItemText, withStyles, Divider } from "@material-ui/core";

const StyledListItem = withStyles({
  root: {
    margin: "15px",
    "&.Mui-selected": {
      backgroundColor: "#e84545",
      color: "white",
      width: "23vw",
      paddingLeft: "50px",
      transform: "translateX(25px) scale(1.3)"
    },
    "&:hover": {
      backgroundColor: "#ED7373",
      color: "#2b2e4a",
      width: "23vw",
      paddingLeft: "50px",
      transform: "translateX(25px) scale(1.3)"
    }
  },
})(ListItem);

const AllProducts = ({products, showProduct})=> {
  
  const [selectedIndex, setSelectedIndex] = useState(-1);

  const handleListItemClick = (event, index, pid) => {
    setSelectedIndex(index);
    showProduct(pid);
  };

  let productList = products.length ? 
    products.map((product, index) => {
      let {pid, name} = product;

      return (
        <>
          <StyledListItem
            key={index}
            button
            selected={selectedIndex === index}
            onClick={(event) => handleListItemClick(event, index, pid)}
            className="product-item"
          >
            <ListItemText primary={name} />
          </StyledListItem>
          <Divider />
        </>
      )
    }) :
    <div className="loader-div">
      <div class="lds-ring"><div></div><div></div><div></div><div></div></div>
      <h2>Data is Loading</h2>
    </div>
  
  return (
    <Paper className="product-list">
      <List component="nav" aria-label="main mailbox folders">
        {productList}
      </List>
    </Paper>
  );
}

export default AllProducts;
