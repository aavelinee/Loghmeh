import React, { Component } from 'react';
import FOODIMG from '../../images/foodpic.jpeg';
import './FoodPartyFood.css';

class FoodPartyFood extends Component {
    render() {
        return (
            <div className="container foodparty-food">
                <div className="row foodparty-food-row foodparty-foodinfo">
                    <div className="right-foodparty-foodinfo col-md-6">
                        <img  id="foodparty-foodimg" src={FOODIMG} className="rounded" alt="Food"></img>
                    </div>
                    <div className="left-foodparty-foodinfo col-md-6">
                        <div className="row foodparty-foodname">
                            <p id="foodparty-foodname">پیتزا</p>
                        </div>
                        <div className="food-star-icon foodRate">

                        </div>
                    </div>
                </div>
                <div className="row foodparty-food-row foodparty-price">
                    <div className="right-foodparty-price col-md-6">
                        <p id="prevPrice">۳۹۰۰۰</p>
                    </div>
                    <div className="left-foodparty-price col-md-6">
                        <p id="curPrice">۲۹۰۰۰</p>
                    </div>
                </div>
                <div className="row foodparty-food-row foodparty-buy">
                    <div className="right-foodparty-countLeft col-md-6">
                        <p id="countLeft">موجودی: <b className="foodparty-count">۳</b></p>
                    </div>
                    <div className="left-foodparty-buy col-md-6">
                        <button type="button" className="foodparty-buy-btn">خرید</button>
                    </div>
                </div>
                <div className="row foodparty-food-row foodparty-restname">
                    <div className="foodparty-restaurantname">
                        <p id="foodparty-restaurantname">رستوران خامس</p>
                    </div>
                </div>
            </div>
        );
    }

}

export default FoodPartyFood;