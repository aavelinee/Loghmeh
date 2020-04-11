import React, { Component, Fragment } from 'react';
import Navbar from '../common/Navbar'
import HomeJumbotron from '../common/HomeJumbotron';
import FoodParty from './foodparty/FoodParty'
import Restaurants from './restaurant/Restaurants'
import Footer from '../common/Footer'

import './Home.css'

class Home extends Component {


    render() {
        return(
            <Fragment>
                <Navbar logo={false} account={true} cart={true} quit={true} />
                <HomeJumbotron searchBox={true} />
                <div className="home-main-content">
                    <FoodParty />
                    <Restaurants />
                </div>
                <Footer />
            </Fragment>
        );
    }

}

export default Home;