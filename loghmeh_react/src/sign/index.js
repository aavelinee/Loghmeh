import React, { Component } from 'react';
// import logo from './logo.svg';
import './App.css';
import './Register.css';

// import Register from './Register.js'
// import RegisterPhoto from './Register.js'

class Sign extends Component {
  render() {
    return (
      <div className="App">
        {/* <div className="App-header"> */}
          <RegisterPhoto />
          <Register isSignUp={true} />
        {/* </div> */}
      </div>
    );
  }
}

export default App;
