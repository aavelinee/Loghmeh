import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import {Modal} from 'react-bootstrap';
import LOGO from '../images/LOGO.png';
import Profile from '../profile/Profile';
import './Navbar.css';
import '../images/icons/flaticon.css';
import Orders from '../profile/orders/Orders';
import Cart from '../menu/cart/Cart';

class Navbar extends Component {
	constructor(props) {
		super(props);
		this.state = {logo : props.logo, account : props.account, cart : props.cart, quit : props.quit, showModal: false};
		this.renderProfile = this.renderProfile.bind(this);
		this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);
	}

	renderProfile() {
		ReactDOM.render(
			<Profile tab={"credit"} />,
			document.getElementById('root')
		);
	}

	handleShow() {
        this.setState({showModal: true});
    }
    
    handleClose() {
        this.setState({showModal: false});
    }

	render() {
		return (
			<nav className="navbar">
				<div className="container-fluid nav-container">
					<div className="row">
						<div className="right-navbar col-md-9">
							{this.state.logo &&
								<div className="loghmeh-logo">
									<img src={LOGO} alt="Loghmeh-Logo"></img>
								</div>
							}
						</div>
						<div className="left-navbar col-md-3">
							{this.state.cart &&
								<a className="flaticon-smart-cart" onClick={this.handleShow}></a>
							}
							{this.state.account &&
								<a className="profile" onClick={this.renderProfile}>حساب کاربری</a>
							}
							{this.state.quit &&
								<a className="quit-link">خروج</a>
							}
						</div>
					</div>
				</div>
				<Modal show={this.state.showModal} onHide={this.handleClose}>
                    <Cart />
                </Modal>
			</nav>
		);
	}
}

export default Navbar; // Don’t forget to use export default!
