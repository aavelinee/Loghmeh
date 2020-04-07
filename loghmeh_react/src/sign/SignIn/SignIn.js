import React, { Component } from 'react';
import './SignIn.css'

class SignIn extends Component {
  render() {
      return (
        <div className="container-sm-sign-in border">
          <div className="form-inline">
              <input 
              type="email" 
              name="email" 
              className="sign-in-form form-control mb-2 mr-sm-2" 
              placeholder="ایمیل">
              </input>
              <input 
              type="password" 
              name="password" 
              className="sign-in-form form-control mb-2 mr-sm-2" 
              placeholder="رمز عبور">
              </input>
          </div>
          <div className="sub">
            <input type="submit" value="ورود" className="btn btn-primary pull-center"></input>
          </div>
        </div>
      );
    }
}

export default SignIn;