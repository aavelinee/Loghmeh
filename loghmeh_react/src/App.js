import React, { Component} from 'react';
import Home from './Home/Home';
import Cart from './menu/cart/Cart';
import BaseCrousel from './Home/foodparty/Carousel';
import Sign from './sign/Sign'
import './App.css';

class App extends Component {
  render() {
    return (
      <div>
        <Home />
        {/* <BaseCrousel> <Cart /> <Cart /> <Cart /> <Cart /> </BaseCrousel> */}
        {/* <Sign isSignUp={true}/> */}
      </div>
    );
  }
}

export default App;
