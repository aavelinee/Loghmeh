import React, { Component, Fragment } from 'react';
import './FoodParty.css';
import FoodPartyFood from './FoodPartyFood';

class FoodParty extends Component {

    render() {
        return (
            <div className="container-fluid food-party">
                <div className="row foodparty-row foodparty-title">
                    <h className="foodparty-heading">
                        <b id="foodparty-heading">جشن غذا!</b>
                    </h>
                </div>
                <div className="row foodparty-row foodparty-time">
                    <h className="remaining-time">زمان باقی‌مانده: </h>
                </div>
                <div className="row foodParyFoods">
                    <FoodPartyFood />
                </div>
            </div>
        );
    }

}

export default FoodParty;