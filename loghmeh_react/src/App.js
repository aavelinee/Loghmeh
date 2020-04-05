import React, { Component } from 'react';
import './App.css';
import Navbar from './common/Navbar';
import Footer from './common/Footer';
import UserInfo from './profile/UserInfo';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Navbar logo={true} account={false} cart={true} quit={true} />
        <UserInfo />
        <Footer />
      </div>
    );
  }
}

export default App;
