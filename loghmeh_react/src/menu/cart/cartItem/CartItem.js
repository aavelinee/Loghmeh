import React, { Component } from 'react';
import './CartItem.css';
import PersianNumber from '../../../common/PersianNumber';

class CartItem extends Component {
    constructor(props) {
        super(props);
        this.state = {name: props.name, number: props.number, price: props.price};
    }
    render() {
        console.log("rendering cart item", this.state.number);
        return(
            <div className="cart-item-container">
                <div className="cart-item-info row">
                    <div className="cart-food-name col-md-9">
                        <p id="cart-food-name">{this.state.name}</p>
                    </div>
                    <div className="cart-ord-add-cart col-md-3">
                        <div className="addcart-box">
                            <div className="cart-plus">
                                <a className="flaticon-plus" href="#"></a>
                            </div>
                            <div className="cart-ord-num">
                            <p className="food-number" data-href="#"><PersianNumber number={this.state.number} /></p>
                            </div>
                            <div className="cart-minus">
                                <a className="flaticon-minus" href="#"></a> 
                            </div>
                        </div>
                    </div>
                </div>
                <div className="cart-item-price row">
                    <p id="cart-item-price" data-href="#"><PersianNumber number={this.state.price} /> تومان</p>
                </div>
            </div>
        );
    }
}

export default CartItem;
