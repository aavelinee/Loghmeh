import React, { Component} from 'react';
import Sign from './sign/Sign';
import Home from './Home/Home'
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
          {/* <Menu /> */}
          {/* <Sign isSignUp={false}/> */}
        {/* <Example test={<FoodPartyFood logo={true}/>}/> */}
        <Home />
      </div>
    );
  }
}

export default App;