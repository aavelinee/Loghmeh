import React, { Component, Fragment } from 'react';
import axios from 'axios';
import CartItem from './cartItem/CartItem';
import Example from '../../common/Modal'
import './Cart.css';

class Cart extends Component {
    constructor(props) {
        super(props);
        this.state = {cart : null, rerender : false};
        this.getCart = this.getCart.bind(this);
        this.addToCart = this.addToCart.bind(this);
        this.handlePlusAddToCart = this.handlePlusAddToCart.bind(this);
        this.handleFinalize = this.handleFinalize.bind(this);
    }

    componentDidMount() {
        this.getCart();
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

    addToCart(restaurantId, foodName, isFoodParty) {
        console.log("order moreeeeeeeeee");
        event.preventDefault();
		axios.put('http://localhost:8081/Loghmeh_war_exploded/put_cart', null,
			{params: {'userId': 1, 'restaurantId': restaurantId, 'foodName' : foodName, 'isFoodParty' : isFoodParty}}
		).then( (response) => {this.getCart();})
        .catch((error) => {
            if (error.response.status === 403) {
              return <Example test={<p>شما مجاز به سفارش غذا از رستوران‌های متفاوت نیستید.</p>}/>

            } else {
                console.log(error);
            }
          })    
    }

    handlePlusAddToCart(foodName, isFoodParty) {
        this.addToCart(this.props.restaurantId, foodName, isFoodParty);
    }

    handleFinalize() {
        console.log("finaliiiiiize");
        event.preventDefault();
		axios.put('http://localhost:8081/Loghmeh_war_exploded/finalize', null,
			{params: {'userId': 1}}
		).then( (response) => {this.getCart()})
        .catch((error) => {
            // if (error.response.status === 403) {
            //   return <Example test={<p>شما مجاز به سفارش غذا از رستوران‌های متفاوت نیستید.</p>}/>

            // } else {
                console.log("eroooooooor", error);
            // }
          })   
    }


    render() {
        console.log("cart", this.state.cart);
        var cartItems;
        if(this.state.cart){
            console.log("null nis");
            cartItems = this.state.cart.orders.map((order) => 
            <CartItem name={order.food.name} number={order.orderCount} price={order.food.price} onClickPlus={this.handlePlusAddToCart} key={order.food.name}/>
            );
        }
        console.log("cart item var: ", cartItems);

        return(
            <div className="cart container border">
                <div className="row shopping-cart-row">
                    <p id="shopping-cart"><b>سبد خرید</b></p>
                </div>
                { this.state.cart ? 
                    <Fragment>
                    <div className="row cart-items-row">
                        <div className="cart-items container">
                            {cartItems}
                        </div>
                    </div>
                    <div className="row cart-total-row">
                        <p className="cart-total-price">جمع کل: <b>۱۰۷۰۰۰ تومان</b></p>
                    </div>
                    <div className="row cart-button-row">
                        <button type="button" className="cart-buy-btn" onClick={this.handleFinalize}>تأیید نهایی</button>
                    </div>   
                    </Fragment>         
                : <p id="empty-cart">سبد خرید شما خالی است.</p>
                       
                }
            </div>
        );
    }
}

export default Cart;
