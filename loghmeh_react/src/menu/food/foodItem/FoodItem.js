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
        this.handlePlus = this.handlePlus.bind(this);
        this.handleMinus = this.handleMinus.bind(this);
        this.getCart = this.getCart.bind(this);
        this.onClickAddToCart = this.onClickAddToCart.bind(this);
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

    handlePlus(foodName, isFoodParty) {
        console.log("on click plus: ", this.state);
        this.setState({foodCount: this.state.foodCount + 1});
    }

    handleMinus() {
        console.log("on click minus: ", this.state);
        if(this.state.foodCount == 0){
            return;
        }
        this.setState({foodCount: this.state.foodCount - 1});
    }

    getCart() {
        console.log("getCart is called");
    	axios.get("http://localhost:8081/Loghmeh_war_exploded/cart/" + 1)
		.then(res => {
            const data = res.data;
			this.setState({ 
				cart : data
			});
        }).catch(error => {console.log(error);})
    }

    onClickAddToCart(restaurantId, foodName, isFoodParty) {
        console.log("order moreeeeeeeeee");
        event.preventDefault();
        axios.put('http://localhost:8081/Loghmeh_war_exploded/put_cart', null,
            {params: {
                'userId': 1, 
                'restaurantId': restaurantId, 
                'foodName' : foodName, 
                'isFoodParty' : isFoodParty,
                'foodCount': this.state.foodCount
            }}
        ).then( (response) => {this.getCart();})
        .catch((error) => {
            // if (error.response.status === 403) {
                // return <Example test={<p>شما مجاز به سفارش غذا از رستوران‌های متفاوت نیستید.</p>}/>

            // } else {
                console.log(error);
            // }
            })
    }

    render() {
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
                        <FoodDetail isFoodParty={false} foodDetail={this.state.food} foodCount={this.state.foodCount} onClickPlus={this.handlePlus} onClickMinus={this.handleMinus} />
                    </Modal>
                </form>
            );
        }
}

export default FoodItem;
