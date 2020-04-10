import React, { Component } from 'react';
import './FoodParty.css';
import FoodPartyFood from './FoodPartyFood';

class FoodParty extends Component {

    render() {
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