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

        this.prepareLoginButton = this.prepareLoginButton.bind(this);
        this.googleSDK = this.googleSDK.bind(this);
        this.signIn = this.signIn.bind(this);
        this.signOut = this.signOut.bind(this);



        this.state = {email : "", password : "", showModal : false, msg : ""}
    }

    componentDidMount() {
      this.googleSDK();
      console.log('sfsfd');
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


    prepareLoginButton = () => {
 
      console.log(this.refs.googleLoginBtn);
   
      this.auth2.attachClickHandler(this.refs.googleLoginBtn, {},
          (googleUser) => {
   
          let profile = googleUser.getBasicProfile();
          console.log('Token || ' + googleUser.getAuthResponse().id_token);
          console.log('ID: ' + profile.getId());
          console.log('Name: ' + profile.getName());
          console.log('Image URL: ' + profile.getImageUrl());
          console.log('Email: ' + profile.getEmail());
          //YOUR CODE HERE
          this.signIn(googleUser.getAuthResponse().id_token)
   
   
          }, (error) => {
              alert(JSON.stringify(error, undefined, 2));
          });
   
      }
  
      googleSDK = () => {
   
          window['googleSDKLoaded'] = () => {
            window['gapi'].load('auth2', () => {
              this.auth2 = window['gapi'].auth2.init({
                client_id: '964150934775-gp7lee33askr8laivsf0prbhgpb5lddu.apps.googleusercontent.com',
                cookiepolicy: 'single_host_origin',
                scope: 'profile email'
              });
              this.prepareLoginButton();
            });
          }
       
          (function(d, s, id){
            var js, fjs = d.getElementsByTagName(s)[0];
            if (d.getElementById(id)) {return;}
            js = d.createElement(s); js.id = id;
            js.src = "https://apis.google.com/js/platform.js?onload=googleSDKLoaded";
            fjs.parentNode.insertBefore(js, fjs);
          }(document, 'script', 'google-jssdk'));
       
      }

      signIn(id_token) {
        console.log("id token: ", id_token);
        event.preventDefault();
        axios.put('http://localhost:8080/Loghmeh_war_exploded/google_sign_in', null,
          {params: {
                    'token': id_token}}
        ).then( (response) => {
                console.log("respoonnnse amaaadd")
                console.log(response)
            .catch((error) => {
                if (error.response.status == 403) {
                    console.log("erorrrrrrr")
                } else {
                    console.log(error);
                }
              })    
        })
      }

      signOut() {
        var auth2 = this.auth2.signOut().then(function() {
          console.log("ouuut!")
        });
      }
  
    
  

  render() {
      return (
        <div className="signin-container container-sm border">
          <form className="signin-form form-inline">
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
                  <input type="submit" value="ورود" className="signin-btn pull-center" onClick={this.handleSignin}></input>
                  <button className="g-signin2" ref="googleLoginBtn">ورود با گوگل</button>
                  <button className="signup-link" onClick={this.handleSignup} style={{cursor: 'pointer'}}>ثبت‌نام نکرده‌اید؟</button>
              </div>
          </form>
        </div>
      );
    }
}

export default SignIn;