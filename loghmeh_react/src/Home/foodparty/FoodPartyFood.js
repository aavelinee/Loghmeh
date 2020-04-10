import React, { Component } from 'react';
import {Modal} from 'react-bootstrap';
import FOODIMG from '../../images/foodpic.jpeg';
import './FoodPartyFood.css';
import ModalClass from '../../common/Modal';
import FoodDetail from '../food/FoodDetail';

class FoodPartyFood extends Component {
    constructor(props) {
        super(props);
        this.state = {showModal: false};
        this.handleClose = this.handleClose.bind(this);
        this.handleShow = this.handleShow.bind(this);

    }

    handleShow() {
        this.setState({showModal: true});
    }

    handleClose() {
        this.setState({showModal: false});
    }

    render() {
        return (
            <div className="container foodparty-food">
                <div className="food-party-food-border" onClick={this.handleShow}>
                    <div className="row foodparty-food-row foodparty-foodinfo">
                        <div className="right-foodparty-foodinfo col-md-6">
                            <img  id="foodparty-foodimg" src={FOODIMG} className="rounded" alt="Food Picture"></img>
                        </div>
                        <div className="left-foodparty-foodinfo col-md-6">
                            <div className="row foodparty-foodname">
                                <p id="foodparty-foodname">پیتزا</p>
                            </div>
                            <div className="food-star-icon foodRate">

                            </div>
                        </div>
                    </div>
                    <div className="row foodparty-food-row foodparty-price">
                        <div className="right-foodparty-price col-md-6">
                            <p id="prevPrice">۳۹۰۰۰</p>
                        </div>
                        <div className="left-foodparty-price col-md-6">
                            <p id="curPrice">۲۹۰۰۰</p>
                        </div>
                    </div>
                    <div className="row foodparty-food-row foodparty-buy">
                        <div className="right-foodparty-countLeft col-md-6">
                            <p id="countLeft">موجودی: <b className="foodparty-count">۳</b></p>
                        </div>
                        <div className="left-foodparty-buy col-md-6">
                            <button type="button" className="foodparty-buy-btn" >خرید</button>
                        </div>
                    </div>
                    <div className="row foodparty-food-row foodparty-restname">
                        <div className="foodparty-restaurantname">
                            <p id="foodparty-restaurantname">رستوران خامس</p>
                        </div>
                    </div>
                </div>
                {/* <Modal show={this.state.showModal} onHide={this.handleClose}>
                    <FoodDetail isFoodParty={true} />
                </Modal> */}
            </div>
        );
    }
}

export default FoodPartyFood;
