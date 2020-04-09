import React, { Component} from 'react';
import RestLogo from '../images/restpic.jpeg';
import './MenuJumbotron.css';

class MenuJumbotron extends Component {
    render() {
        return(
            <div className="menu-jumbotron">
                <div className="restaurant-logo">
                    <img src={RestLogo} className="restaurant-logo" alt="Restaurant-Logo"></img>
                </div>
                <div className="restaurant-name">
                    <b className="restaurant-name">رستوران خامس</b>
                </div>
            </div>
        );
    }
}

export default MenuJumbotron;
