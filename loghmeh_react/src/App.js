import React, { Component } from 'react';
import './App.css';

import Sign from './sign/Sign';
import Home from './Home/Home'

class App extends Component {
  render() {
    return (
      <div className="App">
        <Home />
        {/* <Sign isSignUp={false}/> */}
      </div>
    );
  }
}

export default App;