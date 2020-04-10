import React, { Component } from 'react';
import axios from 'axios';
import Navbar from '../common/Navbar';
import UserInfo from './UserInfo';
import Credit from './credit/Credit';
import Orders from './orders/Orders';
import Footer from '../common/Footer';
import './Profile.css'


class Profile extends Component {
	constructor(props) {
		super(props);
		this.state = {tab : props.tab};
		this.userInfoElement = React.createRef();
		this.handleCreditIncrease = this.handleCreditIncrease.bind(this);
	}

	render() {
		return(
			<div className="profile">
				<Navbar logo={true} cart={true} account={false} quit={true}/>
				<UserInfo ref={this.userInfoElement} />
				<div className="profile-main-content">
					<div className="tab-box">
						<div className="btn-group btn-group-lg">
							<button type="button" id="credit" className="tab btn btn-primary z-depth-1" onClick={this.changeTab.bind(this, "credit")}>
								<a id="credit-link" href="#"> افزایش اعتبار </a>
							</button>
							<button type="button" id="orders" className="tab btn btn-primary z-depth-1" onClick={this.changeTab.bind(this, "orders")}>
								<a id="orders-link" href="#" >سفارش‌ها</a>
							</button>
						</div>
						{this.state.tab == "credit" ? <Credit onClick={this.handleCreditIncrease}/> : <Orders />}
					</div>
				</div>
            	<Footer />
			</div>
		);
	}

	changeStyle(newTab, prevTab) {
		console.log(newTab, prevTab, "hello");
		document.getElementById(newTab).style.background="#FF6B6B";
		document.getElementById(newTab+"-link").style.color="white";
		document.getElementById(prevTab).style.background="white";
		document.getElementById(prevTab+"-link").style.color="black";
	}

	changeTab(newTab) {
		this.setState((prevState, props) => ({tab : newTab}));
		let prevTab;
		if(newTab == "credit")
			prevTab = "orders";
		else if(newTab == "orders")
			prevTab = "credit";
		this.changeStyle(newTab, prevTab);
	}

	handleCreditIncrease(credit) {
		event.preventDefault();
		axios.put('http://localhost:8081/08_React_war_exploded/credit', null, 
			{params: {'userId': 1, 'creditIncrease': credit}}
		).then( (response) => {this.userInfoElement.current.updateUserInfo();})
		.catch((error) => {console.log(error);});
	} 

}

export default Profile;