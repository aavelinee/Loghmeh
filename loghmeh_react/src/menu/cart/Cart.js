import React, { Component } from 'react';
import './Cart.css';
import CartItem from './cartItem/CartItem';

class Cart extends Component {
    render() {
        return(
            <div className="cart container border">
                <div className="row shopping-cart-row">
                    <p id="shopping-cart"><b>سبد خرید</b></p>
                </div>
                <div className="row cart-items-row">
                    <div className="cart-items container">
                        <CartItem name={"پیتزا اعلا"} number={2} price={78000} />
                        <CartItem name={"پیتزا نیمه‌اعلا"} number={1} price={29000} />
                    </div>
                </div>
                <div className="row cart-total-row">
                    <p className="cart-total-price">جمع کل: <b>۱۰۷۰۰۰ تومان</b></p>
                </div>
                <div className="row cart-button-row">
                    <button type="submit" className="cart-buy-btn btn btn-primary mb-2">تأیید نهایی</button>
                </div>
            </div>
        );
    }
}

export default Cart;
