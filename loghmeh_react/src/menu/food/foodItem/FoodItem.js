import React, { Component } from 'react';
import {Modal} from 'react-bootstrap';
import axios from 'axios';
import './FoodItem.css';
import PersianNumber from '../../../common/PersianNumber';
import FoodDetail from '../../../Home/food/FoodDetail';

class FoodItem extends Component {
    constructor(props) {
        super(props);
        this.state = {food : props.food, isAvailable : props.isAvailable, 
        showModal: false, cart: null, foodCount: 0};
        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.getCart = this.getCart.bind(this);
        this.handlePlusAddToCart = this.handlePlusAddToCart.bind(this);
        this.handleMinusRemoveFromCart = this.handleMinusRemoveFromCart.bind(this);
        this.addToCart = this.addToCart.bind(this);

    }

    componentWillMount() {
        this.getCart();
    }

    handleShow() {
        this.setState({showModal: true});
    }
    
    handleClose() {
        this.setState({showModal: false});
    }


    getCart() {
        console.log("getCart is called");
    	axios.get("http://localhost:8081/08_React_war_exploded/cart/" + 1)
		.then(res => {
            const data = res.data;
			this.setState({ 
				cart : data
			});
        }).catch(error => {console.log(error);})
    }

    addToCart(restaurantId, foodName, isFoodParty) {
        console.log("order moreeeeeeeeee");
        event.preventDefault();
		axios.put('http://localhost:8081/08_React_war_exploded/put_cart', null,
			{params: {'userId': 1, 'restaurantId': restaurantId, 'foodName' : foodName, 'isFoodParty' : isFoodParty}}
		).then( (response) => {this.getCart();})
        .catch((error) => {
            // if (error.response.status === 403) {
            //     console.log("different restaurant");
            // //   return <Example test={<p>شما مجاز به سفارش غذا از رستوران‌های متفاوت نیستید.</p>}/>

            // } else {
                console.log(error);
            // }
          })    
    }


    handlePlusAddToCart(foodName, isFoodParty) {
        this.addToCart(this.state.food.restaurantId, foodName, isFoodParty);

    }

    handleMinusRemoveFromCart(foodName, isFoodParty) {
        console.log("on click minus: ", foodName, this.state);
        // removeFromCart(this.state.cart.restaurant.id, foodName, isFoodParty);
    }

    render() {
        var foodNum = 0;
        if (this.state.cart) {
            console.log("cart null niiisssttt in food items");
            for (let order = 0; order < this.state.cart.orders.length; order++) {
                console.log(this.state.cart.orders[order].food.name);
                console.log(this.props.food);
                if (this.state.cart.orders[order].food.name == this.props.food.name) {
                    foodNum = parseInt(this.state.cart.orders[order].orderCount);
                }
            }
            console.log("foodNum: ", foodNum);
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
                                    <p id="fooditem-rating"><PersianNumber number={this.state.food.popularity} /></p>
                                    <span className="fa fa-star checked"></span>
                                </div>
                            </div>
                            <div className="row fooditem-price">
                                <p id="fooditem-price"><PersianNumber number={this.state.food.price} /> تومان</p>
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

                    {this.state.cart ? 
                        <FoodDetail isFoodParty={false} foodDetail={this.state.food} foodCount={foodNum} onClickPlus={this.handlePlusAddToCart} onClickMinus={this.handleMinusRemoveFromCart}/>
                    :
                        <FoodDetail isFoodParty={false} foodDetail={this.state.food} foodCount={0} onClickPlus={this.handlePlusAddToCart} onClickMinus={this.handleMinusRemoveFromCart}/>
                    }
                    </Modal>
                </form>
            );
        }
}

export default FoodItem;
