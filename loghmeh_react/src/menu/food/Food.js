import React, { Component } from 'react';
import './Food.css';
import FoodItem from './foodItem/FoodItem';

class Food extends Component {
    render() {
        return(
            <div className="food-in-column">
                <div className="food-in-row">
                    <FoodItem name={"پیتزا اعلا"} star={5} price={39000} isAvailable={true} />
                    <FoodItem name={"پیتزا نیمه‌اعلا"} star={4} price={29000} isAvailable={false} />
                    <FoodItem name={"پیتزا اعلا"} star={5} price={39000} isAvailable={true} />
                </div>
                <div className="food-in-row">
                    <FoodItem name={"پیتزا اعلا"} star={5} price={39000} isAvailable={true} />
                    <FoodItem name={"پیتزا اعلا"} star={5} price={39000} isAvailable={true} />
                    <FoodItem name={"پیتزا اعلا"} star={5} price={39000} isAvailable={true} />
                </div>
                <div className="food-in-row">
                    <FoodItem name={"پیتزا اعلا"} star={5} price={39000} isAvailable={true} />
                    <FoodItem name={"پیتزا اعلا"} star={5} price={39000} isAvailable={true} />
                </div>
            </div>
        );
    }
}

export default Food;
