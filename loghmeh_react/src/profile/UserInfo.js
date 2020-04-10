import React, { Component } from 'react';
import axios from 'axios';
import './UserInfo.css'
import PersianNumber from '../common/PersianNumber';



class UserInfo extends Component {
	constructor(props) {
		super(props);
		this.state = {first_name : "", last_name : "", phone_number : "", email : "", credit : 0};
		this.updateUserInfo = this.updateUserInfo.bind(this);
	}

	render() {
		return (
			<div className="profile-jumbotron jumbotron">
				<div className="container-fluid">
				<div className="row">
					<div className="right col-sm-4">
						<i className="flaticon-account"></i>
						<p className="name">{this.state.first_name + " " + this.state.last_name}</p>
					</div>
					<div className="middle col-4">
					</div>
					<div className="left col-sm-4">
						<div className="all-data">
						<div className="data">
							<i className="flaticon-phone"></i>
							<p className="phone-data" data-href="#">{this.state.phone_number}</p>
						</div>
						<div className="data">
							<i className="flaticon-mail"></i>
							<p className="mail-data" data-href="#">{this.state.email}</p>
						</div>
						<div className="data">
							<i className="flaticon-card"></i>
							<p className="card-data" data-href="#"><PersianNumber number={this.state.credit} /> تومان</p>
						</div>
						</div>
					</div>
				</div>
			</div>
			</div>
		);
	}

	componentDidMount() {
		this.updateUserInfo();
	}

	updateUserInfo() {
		console.log("updating user info")
		axios.get("http://localhost:8081/Loghmeh_war_exploded/customer/" + 1)
		.then(res => {
			const data = res.data;
			console.log(this.state.credit, data.credit);
			this.setState({ 
					first_name : data.firstName,
					last_name : data.lastName,
					phone_number : data.phoneNumber,
					email : data.email,
					credit : data.credit
			 });
		})
	}
}

export default UserInfo;
