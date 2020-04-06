import React, { Component, Fragment } from 'react';
import Navbar from '../common/Navbar';
import UserInfo from './UserInfo';
import Credit from './Credit';
import Orders from './Orders';
import Footer from '../common/Footer';
import './Profile.css'


class Profile extends Component {
	constructor(props) {
		super(props);
		this.state = {tab : props.tab};
	}

	render() {
		return(
			<Fragment>
				<Navbar logo={true} cart={true} account={false} quit={true}/>
				<UserInfo />
				<div className="main-content">
					<div className="tab-box">
						<div className="btn-group btn-group-lg">
							<button type="button" id = "credit" className="tab btn btn-primary z-depth-1" onClick={this.changeTab.bind(this, "credit")}>
								<a id="credit-link" href="#"> افزایش اعتبار </a>
							</button>
							<button type="button" id = "orders" className="tab btn btn-primary z-depth-1" onClick={this.changeTab.bind(this, "orders")}>
								<a id="orders-link" href="#">سفارش‌ها</a>
							</button>
						</div>
						{this.state.tab == "credit" ? <Credit /> : <Orders />}
					</div>
				</div>
            	<Footer />
			</Fragment>
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

}

export default Profile;