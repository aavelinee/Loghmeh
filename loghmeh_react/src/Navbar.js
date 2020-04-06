class InNavbar extends React.Component {
	constructor(props) {
		super(props);
		this.state = {account : props.account};
	}

	render() {
		return (
			<nav className="navbar">
				<div className="right-navbar">
					<div className="loghmeh-logo">
						<img src="images/LOGO.png" className="rounded-circle" alt="Loghmeh-Logo"></img>
					</div>
				</div>
				<div className="left-navbar">
					<a className="flaticon-smart-cart" href="#"></a>
					{this.state.account &&
						<a className="profile" href="#">حساب کاربری</a>
					}
					<a className="quit-link" href="#">خروج</a>
				</div>
			</nav>
		);
	}
}

class OutNavbar extends React.Component {
	render() {
		return (
			<nav className="navbar">
				<div className="right-navbar">
					<div className="loghmeh-logo">
						<img src="images/LOGO.png" className="rounded-circle" alt="Loghmeh-Logo"></img>
					</div>
				</div>
			</nav>
		);
	}
}