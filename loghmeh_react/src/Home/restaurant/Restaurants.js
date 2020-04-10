import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import Menu from '../../menu/Menu';
import './Restaurants.css';

class Restaurants extends Component {
    constructor(props) {
        super(props);
        this.getRestaurants = this.getRestaurants.bind(this);
        this.state = {restaurants : []};
    }

    componentDidMount() {
        this.getRestaurants();
    }

    getRestaurants() {
        axios.get("http://localhost:8081/Loghmeh_war_exploded/ordinary_restaurants")
        .then(res => {
            const data = res.data;
            this.setState({ 
                restaurants: data
                });
        })
    }

    renderRestaurantMenu(restaurant) {
        console.log("after click on show menu", restaurant);
		ReactDOM.render(
			<Menu restaurant={restaurant} />,
			document.getElementById('root')
		);
    }

    render() {
        console.log(this.state.restaurants);
        // array of N elements, where N is the number of rows needed
        const rows = [...Array( Math.ceil(this.state.restaurants.length / 4) )];
        // chunk the restaurants into the array of rows
        const restaurantRows = rows.map( (row, idx) => (this.state.restaurants.slice(idx * 4, idx * 4 + 4) ));
        // map the rows as div.row
        const content = restaurantRows.map((row, idx) => (
            <div className="row home-restaurants-row" key={idx}>    
            { row.map( restaurant => <div key={restaurant.id} className="home-restaurants-col col-md-3">
                <div className="container home-restaurant">
                    <div className="row home-restpic">
                        <img  id="home-restpic" src={restaurant.logo} className="rounded" alt="Restaurant"></img>
                    </div>
                    <div className="row home-restname">
                        <p id="home-restname">{restaurant.name}</p>
                    </div>
                    <div className="row home-restmenu-btn">
                        <button type="button" id="home-restmenu-btn" onClick={this.renderRestaurantMenu.bind(this, restaurant)}>نمایش منو</button>
                    </div>
                </div>

            </div> )}
            </div> )
        );
        return (
            <div className="container home-restaurants">
                <div className="row restaurants-heading">
                    <b id="restaurants-heading">رستوران‌ها</b>
                </div>
                {content}
            </div>
        );
    }
}

export default Restaurants;