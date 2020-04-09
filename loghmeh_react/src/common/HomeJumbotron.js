import React, { Component } from 'react';
import LOGO from '../images/LOGO.png';

import './HomeJumbotron.css'

class HomeJumbotron extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return(
            <div className="container-fluid home-jumbotron">
                <div className="container-fluid home-red-jumbotron">
                    <div className="row  home-jumbotron-row home-loghmeh-logo">
                        <img className="home-loghmeh-img"  src={LOGO} alt="Loghmeh-Logo"></img>
                    </div>
                    <div className="row home-jumbotron-row homeTitle">
                        <h className="homeHeading">اولین و بزرگ‌ترین وب‌سایت سفارش آنلاین غذا در دانشگاه تهران</h>
                    </div>
                    {this.props.searchBox &&
                        <div className="row  home-jumbotron-row searchBox">
                            <form className="search-form form-inline">
                                <label className="sr-only" htmlFor="inlineFormInputName2">Credit</label>
                                <input type="text" readOnly className="foodname-input form-control mb-2 mr-sm-2" id="inlineFormInputName2"
                                    placeholder="نام غذا"></input>
                                <input type="text" readOnly className="restname-input form-control mb-2 mr-sm-2" id="inlineFormInputName2"
                                    placeholder="نام رستوران"></input>
                                <button type="submit" className="search-btn btn btn-primary mb-2">جست‌و‌جو</button>
                            </form>
                        </div>
                    }
                </div>

            </div>
        );
    }
}

export default HomeJumbotron;