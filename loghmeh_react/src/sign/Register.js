import React, { Component } from 'react';
import SignIn from './SignIn/SignIn';
import SignUp from './SignUp/SignUp';

class Register extends Component {
	constructor(props) {
		super(props);
		this.state = {isSignUp : props.isSignUp};
	}

	RegisterPhoto() {
		return (
			<div className="jumbotron register">
				<div className="background-image"></div>
			</div>
		);
	}
	
	render () {
		if (this.state.isSignUp) {
		  return(
			<div className = "main-content">
			  <div className = "sign-up-box">
				<div className = "sign-up-header"><h2> ثبت نام </h2></div>
				<div className = "sign-up-form">
				<SignUp />
				</div>
			  </div>
			</div>
		  );
		}
		else {
		  return(
			<div className = "main-content">
			  <div className = "sign-in-box">
				<div className = "sign-in-header"><h2> ورود </h2></div>
				<div className = "sign-in-form">
				<SignIn />
				</div>
			  </div>
			</div>
		  );
		}
	}	
}

export default Register;
