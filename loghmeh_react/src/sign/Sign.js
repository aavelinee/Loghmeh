import React, { Component } from 'react';

import Register from './Register';
import RegisterPhoto from './Register';

class Sign extends Component {
  render() {
    return (
      <div className="sign">
        <RegisterPhoto />
        <Register />
      </div>
    );
  }
}

export default Sign;
