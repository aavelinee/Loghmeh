import React, { Component} from 'react';
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
  render() {
    return (
      <div>
        <ModalClass show={true} comp={<OrderBill />} />
      </div>
    );
  }
}

export default App;