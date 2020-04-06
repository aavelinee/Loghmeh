import SignInForm from './SignIn/SignIn';
import SignUpForm from './SignUp/SignUp';
import { Component } from 'react';

class Register extends Component {
	RegisterPhoto() {
		return (
			<div className="jumbotron">
				<div className="background-image"></div>
			</div>
		);
	}
	
	Register (props) {
		const isSignUp = props.isSignUp;
		if (isSignUp) {
		  return(
			<div className = "main-content">
			  <div className = "sign-up-box">
				<div className = "sign-up-header"><h2> ثبت نام </h2></div>
				<div className = "sign-up-form">
				<SignUpForm />
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
				<SignInForm />
				</div>
			  </div>
			</div>
		  );
		}
	}	
}

export default Register;
