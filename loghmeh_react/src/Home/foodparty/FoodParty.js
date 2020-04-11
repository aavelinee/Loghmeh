import React, { Component } from 'react';
import axios from 'axios';
import moment from 'moment';
import './FoodParty.css';
import FoodPartyFood from './FoodPartyFood';
import PersianNumber from '../../common/PersianNumber';
import BaseCrousel from './Carousel';

class FoodParty extends Component {
    constructor(props) {
        super(props);
        this.getFoodPartyFoods = this.getFoodPartyFoods.bind(this);
        this.tick = this.tick.bind(this);
        this.state = {foodPartyFoods : [], remainingTime : 60 };
    }

    componentDidMount() {
        this.getFoodPartyFoods();
        this.getNextFoodPartyUpdateDelay();

        this.pageTime = setInterval(this.tick, 1000);
    }


    componentWillUnmount() {
        clearInterval(this.getFoodPartyFoodsTimer);
        clearInterval(this.pageTime);
    }

    tick() {
        if(this.state.remainingTime > 0)
            this.setState({
            remainingTime: this.state.remainingTime - 1
            });
      }

    firstFoodPartyGetHandler() {
        this.getFoodPartyFoods();
        clearInterval(this.getFoodPartyFoodsTimer);

        this.getFoodPartyFoodsTimer = setInterval(
            () => this.getFoodPartyFoods(),
            60 * 1000
        );
    }

    getNextFoodPartyUpdateDelay() {
        axios.get("http://localhost:8081/Loghmeh_war_exploded/next_time")
        .then(res => {
            const data = res.data;
            console.log("timeee:", data);
            this.setState({
                remainingTime : data
            });
            this.getFoodPartyFoodsTimer = setInterval(
                () => this.firstFoodPartyGetHandler(),
                (data+1) * 1000
                );
        }).catch(error => {console.log(error);});
    }

    getFoodPartyFoods() {
        this.setState(
            {
                remainingTime : 60
            });
        axios.get("http://localhost:8081/Loghmeh_war_exploded/foodparty_foods")
        .then(res => {
            const data = res.data;
            this.setState({ 
                foodPartyFoods: data
                });
        }).catch(error => {console.log(error);});
    }

    render() {

        var foodPartyItems;
        if(this.state.foodPartyFoods){
            foodPartyItems = this.state.foodPartyFoods.map((foodPartyFood) =>
            <FoodPartyFood food={foodPartyFood} key={foodPartyFood.name + foodPartyFood.restaurantName}/>);
        }
        
        return (
            <div className="container-fluid food-party">
                <div className="row foodparty-row foodparty-title">
                    <h1 className="foodparty-heading">
                        <b id="foodparty-heading">جشن غذا!</b>
                    </h1>
                </div>
                <div className="row foodparty-row foodparty-time">
                    <h1 className="remaining-time">زمان باقی‌مانده: {<PersianNumber number={moment().startOf('day')
                                                                    .seconds(this.state.remainingTime)
                                                                    .format('H:mm:ss')} />}</h1>
                </div>
                <div className="row foodParyFoods">
                    {/* <BaseCrousel>  */}
                    {foodPartyItems} 
                    {/* </BaseCrousel> */}


                </div>
            </div>
        );
    }

}

export default FoodParty;