import React, { Component, Fragment} from 'react';
import './Menu.css';  
import Navbar from '../common/Navbar';
import Footer from '../common/Footer';
import MenuJumbotron from '../common/MenuJumbotron';
import Cart from './cart/Cart';
import Food from './food/Food';

class Menu extends Component {
    constructor(props) {
        super(props)
        this.state = {restaurant: props.restaurant}
    }
    render() {
        console.log("in menu", this.state.restaurant);
        return(
            <Fragment>
                <Navbar logo={true} account={true} cart={true} quit={true} />
                <MenuJumbotron name={this.state.restaurant.name}/>
                <div className="menu-main-content container">
                    <div className="menu-name row">
                        <b id="menu-name">منوی غذا</b>
                    </div>
                    <div className="menu-container row">
                        <div className="menu-right col-md-4">
                            <Cart />
                        </div>
                        <div className="menu-left col-md-8">
                            <div className="menu-food-form container">
                                <Food menu={this.state.restaurant.menu} />
                            </div>
                        </div>
                    </div>  
                </div>
                <Footer />
        </Fragment>
        );
    }
}

export default Menu