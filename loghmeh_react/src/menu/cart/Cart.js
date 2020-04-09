import React, { Component } from 'react';
import './Cart.css';
import CartItem from './cartItem/CartItem';

class Cart extends Component {
    render() {
        return(
            <div className="cart container border">
                <p className="shopping-cart"><b>سبد خرید</b></p>
                <div className="cart-items container">
                    <CartItem name={"پیتزا اعلا"} number={2} price={78000} />
                    <CartItem name={"پیتزا نیمه‌اعلا"} number={1} price={29000} />
                </div>
                <p className="total-price">جمع کل: <b>۱۰۷۰۰۰ تومان</b></p>
                <button type="submit" className="submit-btn btn btn-primary mb-2">تایید نهایی</button>
            </div>
        );
    }
}

export default Cart;
