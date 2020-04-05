import React, { Component } from 'react';
import './Navbar.css';
import '../images/icons/flaticon.css'
import LOGO from '../images/LOGO.png';

class Navbar extends Component {
	constructor(props) {
		super(props);
		this.state = {logo : props.logo, account : props.account, cart : props.cart, quit : props.quit};
	}

	render() {
		return (
			<nav className="navbar">
				<div className="right-navbar">
                    {this.state.logo &&
                        <div className="loghmeh-logo">
                            <img src={LOGO} className="rounded-circle" alt="Loghmeh-Logo"></img>
                        </div>
                    }
				</div>
				<div className="left-navbar">
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
			</nav>
		);
	}
}

export default Navbar; // Don’t forget to use export default!
