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
<<<<<<< HEAD
        {/* <BaseCrousel> <Cart /> <Cart /> <Cart /> <Cart /> </BaseCrousel> */}
        {/* <Sign isSignUp={true}/> */}
=======
>>>>>>> 6951f3dc02f29977547c28ec9497459aa3e62a44
      </div>
    );
  }
}

export default App;