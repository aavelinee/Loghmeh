import React, { Component } from 'react';
import './UserInfo.css'
import PersianNumber from '../common/PersianNumber';


class UserInfo extends Component {
	constructor(props) {
		super(props);
		this.state = {first_name : "", last_name : "", phone_number : "", email : "", credit : 0};
	}

	render() {
		return (
			<div className="jumbotron">
				<div className="container-fluid">
				<div className="row">
					<div className="right col-sm-4">
						<i className="flaticon-account"></i>
						<p className="name">احسان خامس‌پناه</p>
					</div>
					<div className="middle col-4">
					</div>
					<div className="left col-sm-4">
						<div className="all-data">
						<div className="data">
							<i className="flaticon-phone"></i>
							<p className="phone-data" data-href="#">۰۹۱۲۳۴۵۶۷۸۹</p>
						</div>
						<div className="data">
							<i className="flaticon-mail"></i>
							<p className="mail-data" data-href="#">ekhamespanah@yahoo.com</p>
						</div>
						<div className="data">
							<i className="flaticon-card"></i>
							<p className="card-data" data-href="#">۱۰۰۰۰۰ تومان</p>
						</div>
						</div>
					</div>
				</div>
			</div>
			</div>
		);
	}

	componentDidMount() {
		fetch("http://localhost:8080/08_React_war_exploded/getCustomerInfo/" + 1)
			.then(resp => resp.json())
			.then(data => this.setState({
													first_name : data.firstName,
													last_name : data.lastName,
													phone_number : data.phoneNumber,
													email : data.email,
													credit : data.credit
													}));
	}
}

export default UserInfo;