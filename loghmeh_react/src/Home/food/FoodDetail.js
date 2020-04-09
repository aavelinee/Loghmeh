import React, { Component } from 'react';
import FOODIMG from '../../images/foodpic.jpeg';
import './FoodDetail.css';

class FoodDetail extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return(
            <div className="container fooddetail-container">
                <div className="row fooddetail-restname">
                    <p id="fooddetail-restname">رستوران خامس</p>
                </div>
                <div className="row fooddetail-foodinfo">
                    <div className="fooddetail-foodpic col-md-4">
                        <img  id="fooddetail-foodimg" src={FOODIMG} className="rounded" alt="Food Picture"></img>
                    </div>
                    <div className="fooddetail-foodprop col-md-8">
                        <div className="row fooddetail-foodname">
                            <p id="fooddetail-foodname">پیتزا</p>
                            <p id="fooddetail-foodrate">۵</p>
                        </div>
                        <div className="row fooddetail-fooddisc">
                            <p id="fooddetail-fooddisc">پخته شده با مرغوب‌ترین مواد اولیه</p>
                        </div>
                        <div className="row fooddetail-foodprice">
                            { this.props.isFoodParty &&
                    
                                // document.getElementById(fooddetail-foodcurprice).style.margin = "4vmin";
                                <p id="fooddetail-foodprevprice">۳۹۰۰۰</p>
                            }
                            <p id="fooddetail-foodcurprice">۲۹۰۰۰ تومان</p>
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
                            <a className="fooddetail-icon flaticon-plus" href="#"></a>
                        {/* </div> */}
                        {/* <div className="fooddetail-ord-num"> */}
                            <p id="fooddetail-ord-num">۲</p>
                        {/* </div> */}
                        {/* <div className="fooddetail-minus"> */}
                            <a className="fooddetail-icon flaticon-minus" href="#"></a> 
                        {/* </div> */}
                        <button type="button" className="fooddetail-cart-buybtn">افزودن به سبد خرید</button>
                    </div>
                </div> 
            </div>

        );
    }
}

export default FoodDetail;