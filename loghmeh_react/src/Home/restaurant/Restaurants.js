import React, { Component} from 'react';
import RESTIMG from '../../images/restpic.png';
import './Restaurants.css';

class Restaurants extends Component {

    render() {
        return (

            <div class="container home-restaurants">
                <div class="row restaurants-heading">
                    <b id="restaurants-heading">رستوران‌ها</b>
                </div>
                <div class="row home-restaurants-row">
                    <div className="home-restaurants-col col-md-3">
                        <div className="container home-restaurant">
                            <div className="row home-restpic">
                                <img  id="home-restpic" src={RESTIMG} class="rounded" alt="Restaurant Picture"></img>
                            </div>
                            <div className="row home-restname">
                                <p id="home-restname">Khames Fried Chicken</p>
                            </div>
                            <div className="row home-restmenu-btn">
                                <button type="button" id="home-restmenu-btn">نمایش منو</button>
                            </div>
                        </div>
                    </div>
                    <div className="home-restaurants-col col-md-3">
                        <div className="container home-restaurant">
                            <div className="row home-restpic">
                                <img  id="home-restpic" src={RESTIMG} class="rounded" alt="Restaurant Picture"></img>
                            </div>
                            <div className="row home-restname">
                                <p id="home-restname">Khames Fried Chicken</p>
                            </div>
                            <div className="row home-restmenu-btn">
                                <button type="button" id="home-restmenu-btn">نمایش منو</button>
                            </div>
                        </div>
                    </div>
                    <div className="home-restaurants-col col-md-3">
                        <div className="container home-restaurant">
                            <div className="row home-restpic">
                                <img  id="home-restpic" src={RESTIMG} class="rounded" alt="Restaurant Picture"></img>
                            </div>
                            <div className="row home-restname">
                                <p id="home-restname">Khames Fried Chicken</p>
                            </div>
                            <div className="row home-restmenu-btn">
                                <button type="button" id="home-restmenu-btn">نمایش منو</button>
                            </div>
                        </div>
                    </div>
                    <div className="home-restaurants-col col-md-3">
                        <div className="container home-restaurant">
                            <div className="row home-restpic">
                                <img  id="home-restpic" src={RESTIMG} class="rounded" alt="Restaurant Picture"></img>
                            </div>
                            <div className="row home-restname">
                                <p id="home-restname">Khames Fried Chicken</p>
                            </div>
                            <div className="row home-restmenu-btn">
                                <button type="button" id="home-restmenu-btn">نمایش منو</button>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row home-restaurants-row">
                    <div className="home-restaurants-col col-md-3">
                        <div className="container home-restaurant">
                            <div className="row home-restpic">
                                <img  id="home-restpic" src={RESTIMG} class="rounded" alt="Restaurant Picture"></img>
                            </div>
                            <div className="row home-restname">
                                <p id="home-restname">Khames Fried Chicken</p>
                            </div>
                            <div className="row home-restmenu-btn">
                                <button type="button" id="home-restmenu-btn">نمایش منو</button>
                            </div>
                        </div>
                    </div>
                    <div className="home-restaurants-col col-md-3">
                        <div className="container home-restaurant">
                            <div className="row home-restpic">
                                <img  id="home-restpic" src={RESTIMG} class="rounded" alt="Restaurant Picture"></img>
                            </div>
                            <div className="row home-restname">
                                <p id="home-restname">Khames Fried Chicken</p>
                            </div>
                            <div className="row home-restmenu-btn">
                                <button type="button" id="home-restmenu-btn">نمایش منو</button>
                            </div>
                        </div>
                    </div>
                    <div className="home-restaurants-col col-md-3">
                        <div className="container home-restaurant">
                            <div className="row home-restpic">
                                <img  id="home-restpic" src={RESTIMG} class="rounded" alt="Restaurant Picture"></img>
                            </div>
                            <div className="row home-restname">
                                <p id="home-restname">Khames Fried Chicken</p>
                            </div>
                            <div className="row home-restmenu-btn">
                                <button type="button" id="home-restmenu-btn">نمایش منو</button>
                            </div>
                        </div>
                    </div>
                    <div className="home-restaurants-col col-md-3">
                        <div className="container home-restaurant">
                            <div className="row home-restpic">
                                <img  id="home-restpic" src={RESTIMG} class="rounded" alt="Restaurant Picture"></img>
                            </div>
                            <div className="row home-restname">
                                <p id="home-restname">Khames Fried Chicken</p>
                            </div>
                            <div className="row home-restmenu-btn">
                                <button type="button" id="home-restmenu-btn">نمایش منو</button>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
            
        );
    }
}

export default Restaurants;