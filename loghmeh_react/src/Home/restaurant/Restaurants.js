import React, { Component } from 'react';
import axios from 'axios';
import RESTIMG from '../../images/restpic.png';
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
        // fetch("http://localhost:8080/08_React_war_exploded/hell")
		// 	.then(resp => resp.json())
        //     .then(data => this.setState({restaurants: data}));

            axios.get("http://localhost:8081/08_React_war_exploded/ordinary_restaurants")
            .then(res => {
                const data = res.data;
                this.setState({ 
                    restaurants: data
                 }).catch(error => {
                    console.log(error)
                });
            })
    }

    render() {
        console.log(this.state.restaurants);
            // <div className="container home-restaurants">
            //     <div className="row restaurants-heading">
            //         <b id="restaurants-heading">رستوران‌ها</b>
            //     </div>
            //     <div className="row home-restaurants-row">
            //         <div className="home-restaurants-col col-md-3">
            //             <div className="container home-restaurant">
            //                 <div className="row home-restpic">
            //                     <img  id="home-restpic" src={RESTIMG} className="rounded" alt="Restaurant"></img>
            //                 </div>
            //                 <div className="row home-restname">
            //                     <p id="home-restname">Khames Fried Chicken</p>
            //                 </div>
            //                 <div className="row home-restmenu-btn">
            //                     <button type="button" id="home-restmenu-btn">نمایش منو</button>
            //                 </div>
            //             </div>
            //         </div>
            //         <div className="home-restaurants-col col-md-3">
            //             <div className="container home-restaurant">
            //                 <div className="row home-restpic">
            //                     <img  id="home-restpic" src={RESTIMG} className="rounded" alt="Restaurant"></img>
            //                 </div>
            //                 <div className="row home-restname">
            //                     <p id="home-restname">Khames Fried Chicken</p>
            //                 </div>
            //                 <div className="row home-restmenu-btn">
            //                     <button type="button" id="home-restmenu-btn">نمایش منو</button>
            //                 </div>
            //             </div>
            //         </div>
            //         <div className="home-restaurants-col col-md-3">
            //             <div className="container home-restaurant">
            //                 <div className="row home-restpic">
            //                     <img  id="home-restpic" src={RESTIMG} className="rounded" alt="Restaurant"></img>
            //                 </div>
            //                 <div className="row home-restname">
            //                     <p id="home-restname">Khames Fried Chicken</p>
            //                 </div>
            //                 <div className="row home-restmenu-btn">
            //                     <button type="button" id="home-restmenu-btn">نمایش منو</button>
            //                 </div>
            //             </div>
            //         </div>
            //         <div className="home-restaurants-col col-md-3">
            //             <div className="container home-restaurant">
            //                 <div className="row home-restpic">
            //                     <img  id="home-restpic" src={RESTIMG} className="rounded" alt="Restaurant"></img>
            //                 </div>
            //                 <div className="row home-restname">
            //                     <p id="home-restname">Khames Fried Chicken</p>
            //                 </div>
            //                 <div className="row home-restmenu-btn">
            //                     <button type="button" id="home-restmenu-btn">نمایش منو</button>
            //                 </div>
            //             </div>
            //         </div>
            //     </div>


            //     <div className="row home-restaurants-row">
            //         <div className="home-restaurants-col col-md-3">
            //             <div className="container home-restaurant">
            //                 <div className="row home-restpic">
            //                     <img  id="home-restpic" src={RESTIMG} className="rounded" alt="Restaurant"></img>
            //                 </div>
            //                 <div className="row home-restname">
            //                     <p id="home-restname">Khames Fried Chicken</p>
            //                 </div>
            //                 <div className="row home-restmenu-btn">
            //                     <button type="button" id="home-restmenu-btn">نمایش منو</button>
            //                 </div>
            //             </div>
            //         </div>
            //         <div className="home-restaurants-col col-md-3">
            //             <div className="container home-restaurant">
            //                 <div className="row home-restpic">
            //                     <img  id="home-restpic" src={RESTIMG} className="rounded" alt="Restaurant"></img>
            //                 </div>
            //                 <div className="row home-restname">
            //                     <p id="home-restname">Khames Fried Chicken</p>
            //                 </div>
            //                 <div className="row home-restmenu-btn">
            //                     <button type="button" id="home-restmenu-btn">نمایش منو</button>
            //                 </div>
            //             </div>
            //         </div>
            //         <div className="home-restaurants-col col-md-3">
            //             <div className="container home-restaurant">
            //                 <div className="row home-restpic">
            //                     <img  id="home-restpic" src={RESTIMG} className="rounded" alt="Restaurant"></img>
            //                 </div>
            //                 <div className="row home-restname">
            //                     <p id="home-restname">Khames Fried Chicken</p>
            //                 </div>
            //                 <div className="row home-restmenu-btn">
            //                     <button type="button" id="home-restmenu-btn">نمایش منو</button>
            //                 </div>
            //             </div>
            //         </div>
            //         <div className="home-restaurants-col col-md-3">
            //             <div className="container home-restaurant">
            //                 <div className="row home-restpic">
            //                     <img  id="home-restpic" src={RESTIMG} className="rounded" alt="Restaurant"></img>
            //                 </div>
            //                 <div className="row home-restname">
            //                     <p id="home-restname">Khames Fried Chicken</p>
            //                 </div>
            //                 <div className="row home-restmenu-btn">
            //                     <button type="button" id="home-restmenu-btn">نمایش منو</button>
            //                 </div>
            //             </div>
            //         </div>
            //     </div>


            // </div>
            // array of N elements, where N is the number of rows needed
            const rows = [...Array( Math.ceil(this.state.restaurants.length / 4) )];
            // chunk the products into the array of rows
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
                            <button type="button" id="home-restmenu-btn">نمایش منو</button>
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

// // array of N elements, where N is the number of rows needed
// const rows = [...Array( Math.ceil(this.state.restaurants.length / 4) )];
// // chunk the products into the array of rows
// const restaurantRows = rows.map( (row, idx) => (this.state.restaurants.slice(idx * 4, idx * 4 + 4) ));
// // map the rows as div.row
// const content = restaurantRows.map((row, idx) => (
//     <div className="row home-restaurants-row" key={idx}>    
//     { row.map( restaurant => <div key={restaurant.id} className="home-restaurants-col col-md-3">
//         <div className="container home-restaurant">
//             <div className="row home-restpic">
//                 <img  id="home-restpic" src={RESTIMG} className="rounded" alt="Restaurant"></img>
//             </div>
//             <div className="row home-restname">
//                 <p id="home-restname">{restaurant.name}</p>
//             </div>
//             <div className="row home-restmenu-btn">
//                 <button type="button" id="home-restmenu-btn">نمایش منو</button>
//             </div>
//         </div>

//     </div> )}
//     </div> )
// );
// return (
//     <div className="container home-restaurants">
//         <div className="row restaurants-heading">
//             <b id="restaurants-heading">رستوران‌ها</b>
//         </div>
//         {content}
//     </div>
// );
