import React, { Component } from 'react';
import Register from './Register';
import './Register.css'
import LOGO from '../images/LOGO.png';

class Sign extends React.Component {
  RegisterPhoto() {
		return (
			<div className="jumbotron register">
				<div className="background-image"></div>
			</div>
		);
  }
  
  render() {
    const isSignUp = this.props.isSignUp
    console.log(isSignUp);
    return (
      <div className="sign">
        <div className="jumbotron">
        {this.RegisterPhoto}
          <div className="loghmeh-logo">
            <img src={LOGO} alt="Loghmeh-Logo"></img>
          </div>
          <div className="motto">
            <p>اولین و بزرگترین وب‌سایت سفارش آنلاین غذا در دانشگاه تهران</p>
          </div>
        </div>
        <Register isSignUp={isSignUp} />
      </div>
    );
  }
}

export default Sign;
