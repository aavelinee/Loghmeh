import React, { Component } from 'react';
import './App.css';
import Navbar from './common/Navbar';
import Footer from './common/Footer';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Navbar logo={true} account={true} cart={true} quit={true} />
        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p>
        <Footer />
      </div>
    );
  }
}

export default App;
