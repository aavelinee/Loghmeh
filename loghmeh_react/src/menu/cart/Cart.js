import React, { Component, Fragment } from 'react';
import axios from 'axios';
import CartItem from './cartItem/CartItem';
import {Modal} from 'react-bootstrap';
import './Cart.css';
import PersianNumber from '../../common/PersianNumber';

class Cart extends Component {
    constructor(props) {
        super(props);
        this.state = {cart : null, showModal: false, msg:""};
        this.getCart = this.getCart.bind(this);
        this.addToCart = this.addToCart.bind(this);
        this.removeFromCart = this.removeFromCart.bind(this);
        this.handlePlusAddToCart = this.handlePlusAddToCart.bind(this);
        this.handleMinusRemoveFromCart = this.handleMinusRemoveFromCart.bind(this);
        this.handleFinalize = this.handleFinalize.bind(this);

        this.handleShow = this.handleShow.bind(this);
		this.handleClose = this.handleClose.bind(this);


    }

    componentDidMount() {
        this.getCart();
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


    addToCart(restaurantId, foodName, isFoodParty, foodCount) {
        console.log("order moreeeeeeeeee in cart");
        console.log(foodCount)
        event.preventDefault();
		axios.put('http://localhost:8081/08_React_war_exploded/put_cart', null,
			{params: {'userId': 1, 'restaurantId': restaurantId, 'foodName' : foodName, 'isFoodParty' : isFoodParty, 'foodCount' : foodCount}}
		).then( (response) => {this.getCart();})
        .catch((error) => {
            if (error.response.status == 403) {
                this.setState({msg:"شما مجاز به سفارش غذا از رستوران‌های متفاوت نیستید."});
                this.handleShow();
            } else {
                console.log(error);
            }
          })    
    }

    removeFromCart(restaurantId, foodName, isFoodParty) {
        console.log("order lessssssss");
        // event.preventDefault();
		axios.delete('http://localhost:8081/08_React_war_exploded/del_cart', 
			{params: {'userId': 1, 'restaurantId': restaurantId, 'foodName' : foodName, 'isFoodParty' : isFoodParty}}
		).then( (response) => {this.getCart();})
        .catch((error) => {
            // if (error.response.status === 403) {
            //   return <Example test={<p>شما مجاز به سفارش غذا از رستوران‌های متفاوت نیستید.</p>}/>

            // } else {
                console.log(error);
            // }
          })    
    }

    handlePlusAddToCart(foodName, isFoodParty, foodCount) {
        console.log("hhhhhhhhhhhhhh", foodCount);
        this.addToCart(this.state.cart.restaurant.id, foodName, isFoodParty, foodCount);

    }

    handleMinusRemoveFromCart(foodName, isFoodParty) {
        console.log("on click minus: ", foodName, this.state);
        this.removeFromCart(this.state.cart.restaurant.id, foodName, isFoodParty);
    }

    handleFinalize() {
        console.log("finaliiiiiize");
        event.preventDefault();
		axios.put('http://localhost:8081/08_React_war_exploded/finalize', null,
			{params: {'userId': 1}}
		).then( (response) => {this.getCart()})
        .catch((error) => {
            if (error.response.status == 403) {
                if(error.response.data.errorMsg == "no credit") {
                    this.setState({msg:"اعتبار شما کافی نیست."});
                    this.handleShow();
                }
                else if(error.response.data.errorMsg == "count problem") {
                    this.setState({msg:"سفارش شما بیشتر از موجودی غذاست."});
                    this.handleShow();
                }
                else if(error.response.data.errorMsg == "time problem") {
                    this.getCart();
                    this.setState({msg:"مهلت جشن غذای سفارش شما به پایان رسید."});
                    this.handleShow();
                }
                else if(error.response.data.errorMsg == "both problem") {
                    this.setState({msg:"مهلت جشن غذای سفارش شما به پایان رسید."});
                    this.handleShow();
                }
            }
          })  
    }

    handleShow() {
		this.setState({showModal: true});
	}

	handleClose() {
		this.setState({showModal: false});
	}

    render() {
        console.log("cart", this.state.cart);
        var cartItems;
        if(this.state.cart){
            console.log("null nis");
            cartItems = this.state.cart.orders.map((order) =>
            <CartItem name={order.food.name} number={order.orderCount} price={order.food.price} onClickPlus={this.handlePlusAddToCart} onClickMinus={this.handleMinusRemoveFromCart} key={order.food.name}/>);
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
                        <p className="cart-total-price">جمع کل: <b><PersianNumber number={this.state.cart.totalPrice} /> تومان</b></p>
                    </div>
                    <div className="row cart-button-row">
                        <button type="button" className="cart-buy-btn" onClick={this.handleFinalize} >تأیید نهایی</button>
                    </div>   
                    </Fragment>         
                : <p id="empty-cart">سبد خرید شما خالی است.</p>
                       
                }
                <Modal show={this.state.showModal} onHide={this.handleClose} ><p id="error-msg">{this.state.msg}</p></Modal>

            </div>
        );
    }
}

export default Cart;
