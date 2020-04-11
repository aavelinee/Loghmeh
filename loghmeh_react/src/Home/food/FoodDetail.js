import React, { Component } from 'react';
import ReactStarsRating from 'react-awesome-stars-rating';
import './FoodDetail.css';
import PersianNumber from '../../common/PersianNumber';


class FoodDetail extends Component {
    constructor(props) {
        super(props);
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
                            <span id="fooddetail-foodrate"><PersianNumber number={this.props.foodDetail.popularity} />
                                    <ReactStarsRating isEdit={false} count={1} value={1} secondaryColor={'orange'} size={12}/>          
                            </span> 
                        </div>
                        <div className="row fooddetail-fooddisc">
                            <p id="fooddetail-fooddisc">{this.props.foodDetail.description}</p>
                        </div>
                        <div className="row fooddetail-foodprice">
                            { this.props.isFoodParty &&
                    
                                // document.getElementById(fooddetail-foodcurprice).style.margin = "4vmin";
                                <p id="fooddetail-foodprevprice"><PersianNumber number={this.props.foodDetail.oldPrice} /> تومان</p>
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
                        <a className="fooddetail-icon flaticon-plus" onClick={() => this.props.onClickPlus(this.props.foodDetail.name, false)}></a>
                        <p id="fooddetail-ord-num" data-href="#"><PersianNumber number={this.props.foodCount} /></p>
                        <a className="fooddetail-icon flaticon-minus" onClick={() => this.props.onClickMinus(this.props.foodDetail.name, false)}></a> 
                        {console.log("infoood detaaaaaail: ", this.props.foodCount)}
                        <button type="button" className="fooddetail-cart-buybtn" onClick={() => this.props.onClickAddToCart(this.props.foodDetail.name, false, this.props.foodCount)}>افزودن به سبد خرید</button>
                    </div>
                </div> 
            </div>

        );
    }
}

export default FoodDetail;