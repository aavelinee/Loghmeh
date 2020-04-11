import React, { Component } from 'react';
import axios from 'axios';
import './FoodParty.css';
import FoodPartyFood from './FoodPartyFood';

class FoodParty extends Component {
    constructor(props) {
        super(props);
        this.getFoodPartyRestaurants = this.getFoodPartyRestaurants.bind(this);
        this.state = {foodPartyRestaurants : []};
    }

    componentDidMount() {
        this.getFoodPartyRestaurants();
    }

    getFoodPartyRestaurants() {
        axios.get("http://localhost:8081/Loghmeh_war_exploded/foodparty_restaurants")
        .then(res => {
            const data = res.data;
            this.setState({ 
                restaurants: data
                });
        }).catch(error => {console.log(error);});
    }

    render() {
        console.log("foodparty rests:", this.state.foodPartyRestaurants);
        return (
            <div className="container-fluid food-party">
                <div className="row foodparty-row foodparty-title">
                    <h1 className="foodparty-heading">
                        <b id="foodparty-heading">جشن غذا!</b>
                    </h1>
                </div>
                <div className="row foodparty-row foodparty-time">
                    <h1 className="remaining-time">زمان باقی‌مانده: </h1>
                </div>
                <div className="row foodParyFoods">
                    <FoodPartyFood />
                </div>
            </div>
        );
    }

}

export default FoodParty;