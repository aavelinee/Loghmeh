import React, { Component} from 'react';
import {Modal, Button} from 'react-bootstrap';

import './App.css';

import Sign from './sign/Sign';
import Home from './Home/Home'
import Menu from './menu/Menu';
import { render } from 'react-dom';
import ModalClass from './common/Modal';
import Navbar from './common/Navbar';
import FoodPartyFood from './Home/foodparty/FoodPartyFood';
import OrderBill from './profile/orders/OrderBill';
import FoodDetail from './Home/food/FoodDetail';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {show: false};
    this.handleShow = this.handleShow.bind(this);
    // this.handleClose = this.handleClose.bind(this);
  }

  handleShow() {
    this.setState({show: true});
  }

  render() {
    console.log(this.state.show); 
    return (
      <div>
        <Home />
      </div>
    );
  }
}

export default App;