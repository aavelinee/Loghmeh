import React, { Component} from 'react';
import './App.css';

import Sign from './sign/Sign';
import Home from './Home/Home'
import Menu from './menu/Menu';
import { render } from 'react-dom';
import Navbar from './common/Navbar';
import FoodPartyFood from './Home/foodparty/FoodPartyFood';

class App extends Component {
  render() {
    return (
      <div>
          {/* <Menu /> */}
          {/* <Sign isSignUp={false}/> */}
        {/* <Example test={<FoodPartyFood logo={true}/>}/> */}
        {/* <Home /> */}
        <Menu name={"منوی غذا"} />
      </div>
    );
  }
}

export default App;