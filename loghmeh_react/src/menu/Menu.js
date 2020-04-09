import React, { Component, Fragment, Modal, Button, useState } from 'react';
import './Menu.css';  
import Navbar from '../common/Navbar';
import Footer from '../common/Footer';
import MenuJumbotron from '../common/MenuJumbotron';
import Cart from './cart/Cart';
import Food from './food/Food';

class Menu extends Component {
    constructor(props) {
        super(props)
        this.state = {name: props.name}
    }
    render() {
        return(
            <Fragment>
                <Navbar logo={true} account={true} cart={true} quit={true} />
                <MenuJumbotron />
                <div className="menu-main-content container">
                    <div className="menu-name row">
                        <b id="menu-name">{this.state.name}</b>
                    </div>
                    <div className="menu-container row">
                        <div className="menu-right col-md-4">
                            <Cart />
                        </div>
                        <div className="menu-left col-md-8">
                            <div className="menu-food-form">
                                <Food />
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