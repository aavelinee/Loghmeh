import React, { Component } from 'react';
import {Modal} from 'react-bootstrap';
import './FoodItem.css';
import PersianNumber from '../../../common/PersianNumber';
import FoodDetail from '../../../Home/food/FoodDetail';
import {getCart, addToCart, removeFromCart} from '../../../common/ApiCalls';

class FoodItem extends Component {
    constructor(props) {
        super(props);
        this.state = {food : props.food, isAvailable : props.isAvailable, 
        showModal: false, cart: null, foodCount: 0};
        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.handlePlusAddToCart = this.handlePlusAddToCart.bind(this);
        this.handleMinusRemoveFromCart = this.handleMinusRemoveFromCart.bind(this);
    }

    componentWillMount() {
        const data = getCart();
        this.setState({cart: data});
    }

    handleShow() {
        this.setState({showModal: true});
    }
    
    handleClose() {
        this.setState({showModal: false});
    }

    handlePlusAddToCart(foodName, isFoodParty) {
        console.log("sdfhsklgksdaj");
        console.log(this.state.food);
        if(this.state.cart == null) {
            this.setState({cart: addToCart(this.state.food.restaurantId, foodName, isFoodParty)});
        } else {
            this.setState({cart: addToCart(this.state.cart.restaurant.id, foodName, isFoodParty)});
        }
    }

    handleMinusRemoveFromCart(foodName, isFoodParty) {
        console.log("on click minus: ", foodName, this.state);
        removeFromCart(this.state.cart.restaurant.id, foodName, isFoodParty);
    }

    render() {
        var foodNum = 0;
        if (this.state.cart != null) {
            for (let order = 0; order < this.state.cart.orders.length; order++) {
                if (this.state.cart.orders[order].food.name == this.props.foodDetail.name) {
                    foodNum = parseInt(this.state.cart.orders[order].orderCount);
                }
            }
        }
            return(
                <form className="food">
                    <div className="food-item-modal">
                        <div className="food-image" onClick={this.handleShow}>
                            <img src={this.state.food.image} className="rounded" alt="Food" ></img>
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
                                    <button type="button" className="available-btn" onClick={() => this.props.onClickBuy(this.props.food.name)}>افزودن به سبد خرید</button>
                                    :
                                    <button type="button" className="notavailable-btn">ناموجود</button>
                                }
                            </div>
                        </div>
                    </div>
                    <Modal show={this.state.showModal} onHide={this.handleClose}>
                        <FoodDetail isFoodParty={false} foodDetail={this.state.food} foodCount={foodNum} onClickPlus={this.handlePlusAddToCart} onClickMinus={this.handleMinusRemoveFromCart}/>
                    </Modal>
                </form>
            );
        }
}

export default FoodItem;
