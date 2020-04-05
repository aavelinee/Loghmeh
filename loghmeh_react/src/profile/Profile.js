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
							<button type="button" className="credit tab btn btn-primary z-depth-1">
								<a className="credit-link" href="#"> افزایش اعتبار </a>
							</button>
							<button type="button" className="order tab btn btn-primary z-depth-1">
								<a className="order-link" href="#">سفارش‌ها</a>
							</button>
						</div>
						{this.state.tab == "credit" ? <Credit /> : <Orders />}
					</div>
				</div>
            	<Footer />
			</Fragment>
		);
	}
}

export default Profile;