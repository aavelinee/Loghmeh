import React, { Component } from 'react';
import './SignIn.css'
import ReactDOM from "react-dom";
import Sign from "../Sign";
import axios from "axios";

class SignIn extends Component {
    constructor(props) {
        super(props);
        this.handleSignin = this.handleSignin.bind(this);
        this.handleSignup = this.handleSignup.bind(this);
        this.handleEmailInput = this.handleEmailInput.bind(this);
        this.handlePasswordInput = this.handlePasswordInput.bind(this);
        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);

        this.state = {email : "", password : "", showModal : false, msg : ""}
    }

    handleSignup() {
        ReactDOM.render(
            <Sign isSignUp={true}/>,
            document.getElementById('root')
        );
    }

    handleSignin() {
        event.preventDefault();

        var format = /\S+@\S+\.\S+/;
        if(!format.test(this.state.email)){//email
            console.log("email");
            this.setState({msg:"آدرس ایمیل وارد شده معتبر نیست."});
            this.handleShow();
            return;
        }
        console.log("sending login req");
        axios.put('http://localhost:8080/Loghmeh_war_exploded/sign_in', null,
            {params: {'email' : this.state.email, 'password' : this.state.password}}
        ).then( (response) => {
            console.log(response.headers["authorization"].split(" ")[1]);
            localStorage.setItem("jwt_token", response.headers["authorization"].split(" ")[1])
            this.renderHome();
        })
        .catch((error) => {
            if (error.response.status == 400){
                this.setState({msg:"ایمیل یا رمزعبور اشتباه است."});
                this.handleShow();
            }
        });

    }


    handleEmailInput() {
        this.setState(prevState => ({email : event.target.value}));
    }

    handlePasswordInput() {
        this.setState(prevState => ({password : event.target.value}));
    }

    handleShow() {
        this.setState({showModal: true});
    }

    handleClose() {
        this.setState({showModal: false});
    }

  render() {
      return (
        <div className="signin-container container-sm border">
          <form className="signin-form form-inline" onSubmit={this.handleSignin}>
              <input 
              type="email" 
              name="email" 
              className="sign-in-form form-control mb-2 mr-sm-2" 
              placeholder="ایمیل"
              onChange={this.handleEmailInput}>
              </input>
              <input 
              type="password" 
              name="password" 
              className="sign-in-form form-control mb-2 mr-sm-2" 
              placeholder="رمز عبور"
              onChange={this.handlePasswordInput}>
              </input>
              <div className="sub">
                  <input type="submit" value="ورود" className="signin-btn pull-center"></input>
                  <button className="signup-link" onClick={this.handleSignup} style={{cursor: 'pointer'}}>ثبت‌نام نکرده‌اید؟</button>
              </div>
          </form>
        </div>
      );
    }
}

export default SignIn;