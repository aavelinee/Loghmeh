import React, { Component } from 'react';
import LOGO from '../images/LOGO.png';
import './Navbar.css';
import '../images/icons/flaticon.css'

class Navbar extends Component {
	constructor(props) {
		super(props);
		this.state = {logo : props.logo, account : props.account, cart : props.cart, quit : props.quit};
	}

	render() {
		return (
			<nav className="navbar">
				<div className="container-fluid">
					<div className="row">
						<div className="right-navbar col-md-8">
							{this.state.logo &&
								<div className="loghmeh-logo">
									<img src={LOGO} alt="Loghmeh-Logo"></img>
								</div>
							}
						</div>
						<div className="left-navbar col-md-4">
							{this.state.cart &&
								<a className="flaticon-smart-cart"></a>
							}
							{this.state.account &&
								<a className="profile">حساب کاربری</a>
							}
							{this.state.quit &&
								<a className="quit-link">خروج</a>
							}
						</div>
					</div>
				</div>
			</nav>
		);
	}
}

export default Navbar; // Don’t forget to use export default!
