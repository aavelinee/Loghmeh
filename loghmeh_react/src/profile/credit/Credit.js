import React, { Component } from 'react';
import './Credit.css'

class Credit extends Component {
	
	constructor(props) {
		super(props);
		this.handleCreditInput = this.handleCreditInput.bind(this);
		this.state = {credit : 0};
	}

	handleCreditInput() {
		this.setState(prevState => ({credit : event.target.value}));
	}

	render() {
		return(
			<div className="credit-container container-sm border">
				<form className="credit-form form-inline" onSubmit={() => this.props.onClick(this.state.credit)}>
					<label className="sr-only" htmlFor="inlineFormInputName2">Credit</label>
					<input type="number" readonly className="credit-input form-control mb-2 mr-sm-2" id="inlineFormInputName2"
						   placeholder="میزان افزایش اعتبار" onChange={this.handleCreditInput}></input>
					<button type="submit" className="credit-btn">افزایش</button>
				</form>
			</div>
		);
	}
}

export default Credit;