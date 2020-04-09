import React, { Component } from 'react';
import './CartItem.css';
import PersianNumber from '../../../common/PersianNumber';

class CartItem extends Component {
    constructor(props) {
        super(props);
        this.state = {name: props.name, number: props.number, price: props.price};
    }
    render() {
        return(
            <div className="cart-item row">
                <div className="food-name-in-cart container right-col">{this.state.name}</div>
                <div className="ord-detail container">
                    <div className="first-line">
                        <div className="plus col-sm-1">
                            <a className="flaticon-plus" href="#"></a>
                        </div>
                        <div className="ord-num col-sm-1">
                        <p className="food-number" data-href="#"><PersianNumber number={this.state.number} /></p>
                        </div>
                        <div className="minus col-sm-1">
                            <a className="flaticon-minus" href="#"></a> 
                        </div>
                    </div>
                    <div className="second-line">
                        <p className="food-price" data-href="#"><PersianNumber number={this.state.price} /> تومان</p>
                    </div>
                </div>
            </div>
        );
    }
}

export default CartItem;
