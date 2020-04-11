import React, { Component} from 'react';
<<<<<<< HEAD
import Home from './Home/Home';
=======
import Home from './Home/Home'
import './App.css';
import FoodDetail from './Home/food/FoodDetail';
>>>>>>> 0c8732029a3a50a4ea4345b1362538508b165797
import Cart from './menu/cart/Cart';
import BaseCrousel from './Home/foodparty/Carousel';
import './App.css';

class App extends Component {
  render() {
    return (
      <div>
        <Home />
        {/* <BaseCrousel> <Cart /> <Cart /> <Cart /> <Cart /> </BaseCrousel> */}
      </div>
    );
  }
}

export default App;
