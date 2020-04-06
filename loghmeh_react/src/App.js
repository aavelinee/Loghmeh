import React, { Component } from 'react';
import './App.css';
import Navbar from './common/Navbar';
import Footer from './common/Footer';
// import Sign from './sign/Sign';
import Profile from './profile/Profile';

class App extends Component {
  render() {
    return (
      // <div className="App">
      //   <Navbar logo={true} account={true} cart={true} quit={true} />
      //   <p className="App-intro">
      //     To get started, edit <code>src/App.js</code> and save to reload.
      //   </p>
      //   <Sign />
      //   <Footer />
      // </div>
      <div className="App">
        <Profile tab={"credit"} />,
      </div>
    );
  }
}

export default App;