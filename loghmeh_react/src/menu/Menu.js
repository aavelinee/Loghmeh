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
                <div className="main-content">
                    <div className="container-fluid">
                        <div className="menu">
                            <div className="row">
                                <div className="right col col-sm-3 col-md-6 col-lg-4">
                                    <Cart />
                                </div>
                                <div className="menu-items col col-sm-9 col-md-6 col-lg-8">
                                    <div className="menu-name">
                                        <b id="menu-name">{this.state.name}</b>
                                    </div>
                                    <div className="food-form">
                                        <Food />
                                    </div>
                                </div>
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