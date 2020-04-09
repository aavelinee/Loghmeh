import React, { Component, Fragment } from 'react';
import './Food.css';
import FoodItem from './foodItem/FoodItem';

class Food extends Component {
    render() {
        console.log("in food: ", this.props.menu);
        // return(
        //     <div className="food-in-column">
        //         <div className="food-in-row">
        //             <FoodItem name={"پیتزا اعلا"} star={5} price={39000} isAvailable={true} />
        //             <FoodItem name={"پیتزا نیمه‌اعلا"} star={4} price={29000} isAvailable={false} />
        //             <FoodItem name={"پیتزا اعلا"} star={5} price={39000} isAvailable={true} />
        //         </div>
        //         <div className="food-in-row">
        //             <FoodItem name={"پیتزا اعلا"} star={5} price={39000} isAvailable={true} />
        //             <FoodItem name={"پیتزا اعلا"} star={5} price={39000} isAvailable={true} />
        //             <FoodItem name={"پیتزا اعلا"} star={5} price={39000} isAvailable={true} />
        //         </div>
        //         <div className="food-in-row">
        //             <FoodItem name={"پیتزا اعلا"} star={5} price={39000} isAvailable={true} />
        //             <FoodItem name={"پیتزا اعلا"} star={5} price={39000} isAvailable={true} />
        //         </div>
        //     </div>
        // );
        console.log(this.props.menu.foods.length);
        const rows = [...Array( Math.ceil(this.props.menu.foods.length / 3) )];
        // chunk the menu into the array of rows
        const menuRows = rows.map( (row, idx) => (this.props.menu.foods.slice(idx * 3, idx * 3 + 3) ));
        // map the rows as div.row
        const content = menuRows.map((row, idx) => (
            <div className="row food-in-column" key={idx}>    
            { row.map( food => <div key={food.name} className="food-in-row col-md-4">
                <FoodItem food={food} isAvailable={true} />
            </div> )}
        </div> )
        );
        return (
            <Fragment>
                {content}
            </Fragment>
        );
    }
}

export default Food;
