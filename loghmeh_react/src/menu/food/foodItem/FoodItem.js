import React, { Component } from 'react';
import './FoodItem.css';
import FoodPic from '../../../images/foodpic.jpeg';
import PersianNumber from '../../../common/PersianNumber';

class FoodItem extends Component {
    constructor(props) {
        super(props);
        this.state = {pic: props.pic, name: props.name, star: props.star, 
            price: props.price, isAvailable: props.isAvailable};
    }

    render() {
        const isAvailable = this.state.isAvailable;
        if(isAvailable) {
            return(
                <form className="food">
                    <div className="food-image">
                        <img src={FoodPic} className="rounded" alt="Food Pic"></img>
                    </div>
                    <div className="info">
                        <div className="food-name">
                            <b>{this.state.name}</b>
                        </div>
                        <div className="food-star">
                            <p className="food-star-p" data-href="#"><PersianNumber number={this.state.star} /></p>
                        </div>
                        <div className="food-star-icon">
                            <span className="fa fa-star checked"></span>
                        </div>
                    </div>
                    <div className="food-price">
                        <p className="food-price-p" data-href="#"><PersianNumber number={this.state.price} /> تومان</p>
                    </div>
                    <div className="buy-btn">
                        <input type="submit" value="افزودن به سبد خرید" className="btn"></input>
                    </div>
                </form>
            );
        } else {
            return(
                <form className="food">
                    <div className="food-image">
                        <img src={FoodPic} className="rounded" alt="Food Pic"></img>
                    </div>
                    <div className="info">
                        <div className="food-name">
                            <b>{this.state.name}</b>
                        </div>
                        <div className="food-star">
                        <p className="food-star-p" data-href="#"><PersianNumber number={this.state.star} /></p>
                        </div>
                        <div className="food-star-icon">
                            <span className="fa fa-star checked"></span>
                        </div>
                    </div>
                    <div className="food-price">
                        <p className="food-price" data-href="#"><PersianNumber number={this.state.price} /> تومان</p>
                    </div>
                    <div className="no-btn">
                        <input type="submit" value="ناموجود" className="btn"></input>
                    </div>
                </form>
            );
        }
    }
}

export default FoodItem;
