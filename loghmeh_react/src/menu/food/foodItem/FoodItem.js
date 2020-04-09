import React, { Component } from 'react';
import {Modal} from 'react-bootstrap';
import './FoodItem.css';
import FoodPic from '../../../images/foodpic.jpeg';
import PersianNumber from '../../../common/PersianNumber';
import FoodDetail from '../../../Home/food/FoodDetail';

class FoodItem extends Component {
    constructor(props) {
        super(props);
        this.state = {food : props.food, isAvailable : props.isAvailable, showModal: false};
        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);
    }

    handleShow() {
        this.setState({showModal: true});
    }

    handleClose() {
        this.setState({showModal: false});
    }

    render() {
            return(
                <form className="food">
                    <div className="food-image">
                        <img src={this.state.food.image} className="rounded" alt="Food"></img>
                    </div>
                    <div className="fooditem-info container">
                        <div className="row fooditem-namestar">
                            <div className="fooditem-name col-md-9">
                                <b>{this.state.food.name}</b>
                            </div>
                            <div className="fooditem-star col-md-3">
                                <p id="fooditem-rating" data-href="#"><PersianNumber number={this.state.food.popularity} /></p>
                                <span className="fa fa-star checked"></span>
                            </div>
                        </div>
                        <div className="row fooditem-price">
                            <p id="fooditem-price" data-href="#"><PersianNumber number={this.state.food.price} /> تومان</p>
                        </div>
                        <div className="row fooditem-buy-btn">
                            {this.props.isAvailable ? 
                                <div>
                                <input type="submit" value="افزودن به سبد خرید" className="available-btn btn" onClick={this.handleShow}></input>
                                <Modal show={this.state.showModal} onHide={this.handleClose}>
                                    <FoodDetail isPartyFood={false} />
                                </Modal>
                                </div>
                                :
                                <input type="submit" value="ناموجود" className="notavailable-btn btn"></input>

                            }
                        </div>
                    </div>
                </form>
            );
        }
}

export default FoodItem;
