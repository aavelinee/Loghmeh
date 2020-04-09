import React, { Component} from 'react';
import './App.css';

import Sign from './sign/Sign';
import Home from './Home/Home'
import Menu from './menu/Menu';
import { render } from 'react-dom';
import Example from './common/Modal';
import Navbar from './common/Navbar';
import FoodPartyFood from './Home/foodparty/FoodPartyFood';

class App extends Component {
  render() {
    return (
      <div>
          {/* <Menu /> */}
          {/* <Sign isSignUp={false}/> */}
        <Example test={<FoodPartyFood logo={true}/>}/>
      </div>
    );
  }
}

export default App;