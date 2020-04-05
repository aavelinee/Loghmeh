import React, { Component } from 'react';
import './Credit.css'

class Credit extends Component {
	render() {
		return(
			<div className="container-sm border">
				<form className="form-inline">
					<label className="sr-only" htmlFor="inlineFormInputName2">Credit</label>
					<input type="text" className="credit-input form-control mb-2 mr-sm-2" id="inlineFormInputName2"
						   placeholder="میزان افزایش اعتبار"></input>
					<button type="submit" className="credit-btn btn btn-primary mb-2">افزایش</button>
				</form>
			</div>
		);
	}
}

export default Credit;