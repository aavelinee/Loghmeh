import React, { Component} from 'react';
import Sign from './sign/Sign';
import Home from './Home/Home'
import './App.css';


class App extends Component {
  render() {
    return (
      <div>
          {/* <Menu /> */}
          {/* <Sign isSignUp={true}/> */}
        {/* <Example test={<FoodPartyFood logo={true}/>}/> */}
        <Home />
      </div>
    );
  }
}

export default App;