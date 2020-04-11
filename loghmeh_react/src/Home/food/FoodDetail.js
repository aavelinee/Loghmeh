import React, { Component } from 'react';
import FOODIMG from '../../images/foodpic.jpeg';
import './FoodDetail.css';
import PersianNumber from '../../common/PersianNumber';


class FoodDetail extends Component {
    constructor(props) {
        super(props);
        this.state = {cart: null};
    }

    render() {
        return(
            <div className="container fooddetail-container">
                <div className="row fooddetail-restname">
                    <p id="fooddetail-restname">{this.props.foodDetail.restaurantName}</p>
                </div>
                <div className="row fooddetail-foodinfo">
                    <div className="fooddetail-foodpic col-md-4">
                        <img  id="fooddetail-foodimg" src={this.props.foodDetail.image} className="rounded" alt="Food Picture"></img>
                    </div>
                    <div className="fooddetail-foodprop col-md-8">
                        <div className="row fooddetail-foodname">
                            <p id="fooddetail-foodname">{this.props.foodDetail.name}</p>
                            <p id="fooddetail-foodrate"><PersianNumber number={this.props.foodDetail.popularity} /></p>
                        </div>
                        <div className="row fooddetail-fooddisc">
                            <p id="fooddetail-fooddisc">{this.props.foodDetail.description}</p>
                        </div>
                        <div className="row fooddetail-foodprice">
                            { this.props.isFoodParty &&
                    
                                // document.getElementById(fooddetail-foodcurprice).style.margin = "4vmin";
                                <p id="fooddetail-foodprevprice"><PersianNumber number={this.props.foodDetail.price} /> تومان</p>
                            }
                                <p id="fooddetail-foodcurprice"><PersianNumber number={this.props.foodDetail.price} /> تومان</p>
                        </div>
                    </div>
                </div>
                <div className="row fooddetail-cart">
                    <div className="fooddetail-cart-countleft col-md-4">
                        { this.props.isFoodParty &&
                            <p id="fooddetail-cart-countleft">موجودی: <b id="fooddetail-countleft">۳</b></p>
                        }
                    </div>
                    <div className="fooddetail-cart-addcart col-md-8">
                        {/* <div className="plus"> */}
                            <a className="fooddetail-icon flaticon-plus" onClick={() => this.props.onClickPlus(this.props.foodDetail.name, false)}></a>
                        {/* </div> */}
                        {/* <div className="fooddetail-ord-num"> */}
                            <p id="fooddetail-ord-num">۲</p>
                        {/* </div> */}
                        {/* <div className="fooddetail-minus"> */}
                            <a className="fooddetail-icon flaticon-minus" onClick={() => this.props.onClickMinus(this.props.foodDetail.name, false)}></a>
                        {/* </div> */}
                        <button type="button" className="fooddetail-cart-buybtn">افزودن به سبد خرید</button>
                    </div>
                </div> 
            </div>

        );
    }
}

export default FoodDetail;