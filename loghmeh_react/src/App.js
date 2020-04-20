import React, { Component} from 'react';
import Home from './Home/Home';
import Cart from './menu/cart/Cart';
import BaseCrousel from './Home/foodparty/Carousel';
import Sign from './sign/Sign'
import './App.css';


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
        {/* <BaseCrousel> <Cart /> <Cart /> <Cart /> <Cart /> </BaseCrousel> */}
        {/* <Sign isSignUp={true}/> */}
      </div>
    );
  }
}

export default App;