import React, { Component } from 'react';
import {Modal} from 'react-bootstrap';
import axios from 'axios';
import ReactStarsRating from 'react-awesome-stars-rating';
import './FoodPartyFood.css';
import FoodDetail from '../food/FoodDetail';
import PersianNumber from '../../common/PersianNumber';

class FoodPartyFood extends Component {
    constructor(props) {
        super(props);
        this.state = {showModal: false, food : props.food, foodCount: 0};
        this.handleClose = this.handleClose.bind(this);
        this.handleShow = this.handleShow.bind(this);
        this.addToCart = this.addToCart.bind(this);
        this.handleAddToCart = this.handleAddToCart.bind(this);
        this.getFoodPartyFood = this.getFoodPartyFood.bind(this);
        this.handlePlus = this.handlePlus.bind(this);
        this.handleMinus = this.handleMinus.bind(this);

    }


    componentDidMount(){
        this.getCountTimer = setInterval(
            () => this.getFoodPartyFood(this.props.food.restaurantId, this.props.food.name),
            4000
          );
    }


    componentWillUnmount() {
        clearInterval(this.getCountTimer);
    }


    handleShow() {
        this.setState({showModal: true});
    }

    handleClose() {
        this.setState({showModal: false});
    }

    handlePlus() {
        this.setState({foodCount: this.state.foodCount + 1});
    }

    handleMinus() {
        if(this.state.foodCount == 0){
            return;
        }
        this.setState({foodCount: this.state.foodCount - 1});
    }

    addToCart(restaurantId, foodName, isFoodParty, foodCount) {
        console.log("foodCount: ", foodCount);
        console.log("state foodCount: ", this.state.foodCount)
        event.preventDefault();
		axios.put('http://localhost:8081/Loghmeh_war_exploded/put_cart', null,
			{params: {
                'userId': 1,
                'restaurantId': restaurantId,
                'foodName' : foodName,
                'foodCount': foodCount,
                'isFoodParty' : isFoodParty}}
		).then( (response) => {this.getFoodPartyFood(restaurantId, foodName);})
        .catch((error) => {
            if (error.response.status === 403) {
            //   return <Example test={<p>شما مجاز به سفارش غذا از رستوران‌های متفاوت نیستید.</p>}/>

            // } else {
                console.log(error);
            // }
          }}); 
    }

    handleAddToCart() {
        this.addToCart(this.state.food.restaurantId, this.state.food.name, true, 1);
    }

    updateCount(count) {
        this.setState({ food: { ...this.state.food, count: count} });
        // if(count == 0) {
        //     document.getElementsByClassName("foodparty-buy-btn").style.background="gray";
        //     document.getElementsByClassName("foodparty-buy-btn").style.border="gray";
        // }
    }

    getFoodPartyFood(restaurantId, foodName) {
        console.log("getFoodPartyFood is called");
    	axios.get("http://localhost:8081/08_React_war_exploded/foodparty_food/" + restaurantId + "/" + foodName)
		.then(res => {
            const data = res.data;
			this.updateCount(data.count);
        }).catch(error => {console.log(error);})
    }

    render() {
        return (
            <div className="container foodparty-food">
                <div className="food-party-food-border">
                    <div className="row foodparty-food-row foodparty-foodinfo">
                        <div className="right-foodparty-foodinfo col-md-6" onClick={this.handleShow}>
                            <img  id="foodparty-foodimg" src={this.state.food.image} className="rounded" alt="Food"></img>
                        </div>
                        <div className="left-foodparty-foodinfo col-md-6">
                            <div className="row foodparty-foodname">
                                <p id="foodparty-foodname">{this.state.food.name}</p>
                            </div>
                            <div className="row food-star-icon foodRate">
                            <span id="foodpartyfood-popularity">{<PersianNumber number={this.state.food.popularity}>}</PersianNumber>}
                            <ReactStarsRating isEdit={false} count={1} value={1} secondaryColor={'orange'} size={12}/>
                            </span>
                            </div>
                        </div>
                    </div>
                    <div className="row foodparty-food-row foodparty-price">
                        <div className="right-foodparty-price col-md-6">
                            <p id="prevPrice"><PersianNumber number={this.state.food.oldPrice} /></p>
                        </div>
                        <div className="left-foodparty-price col-md-6">
                            <p id="curPrice"><PersianNumber number={this.state.food.price} /></p>
                        </div>
                    </div>
                    <div className="row foodparty-food-row foodparty-buy">
                        <div className="right-foodparty-countLeft col-md-6">
                            {this.state.food.count > 0 ?
                                <p id="countLeft">موجودی: <b className="foodparty-count"><PersianNumber number={this.state.food.count} /></b></p>
                                : 
                                <p id="countLeft">ناموجود</p>
                            }
                        </div>
                        <div className="left-foodparty-buy col-md-6">                        
                            {this.state.food.count > 0 ?
                                <button type="button" className="foodparty-buy-btn" onClick={this.handleAddToCart}>خرید</button>
                            : 
                                <button type="button" className="foodparty-cant-buy-btn">خرید</button>
                            }
                        </div>
                    </div>
                    <div className="row foodparty-food-row foodparty-restname">
                        <div className="foodparty-restaurantname">
                            <p id="foodparty-restaurantname">{this.state.food.restaurantName}</p>
                        </div>
                    </div>
                </div>
                <Modal show={this.state.showModal} onHide={this.handleClose}>
                    <FoodDetail foodDetail={this.state.food} isFoodParty={true} foodCount={this.state.foodCount} onClickPlus={this.handlePlus} onClickMinus={this.handleMinus} onClickAddToCart={this.addToCart} />
                </Modal>
            </div>
        );
    }
}

export default FoodPartyFood;
