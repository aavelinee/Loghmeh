import React, { Component } from 'react';
import './Orders.css';
import OrderBill from './OrderBill';
import axios from 'axios';


class Orders extends Component {
    constructor(props) {
        super(props);
        this.state = {orders : []};
        this.getOrders = this.getOrders.bind(this);
    }

    componentDidMount() {
        this.getOrders();
    }

    getOrders() {
		axios.get("http://localhost:8081/08_React_war_exploded/orders")
        .then(res => {
            const data = res.data;
            this.setState({ 
                orders: data
                }).catch(error => {
                console.log(error)
            });
        })
    }
    
	render() {
        console.log(this.state.orders);

		return (
			<div id="orders" class="orders-container container-sm border">
                <div class="container">
                    <div class="row order-item">
                        <div class="col col-1 right-col">۱</div>
                        <div class="col col-6"> رستوران خامس</div>
                        <div class="col col-5 left-col">
                            <button type="button" class="factor on-the-way">پیک در مسیر</button>
                        </div>
                    </div>
                    <div className="row order-item">
                        <div className="col col-1 right-col">۲</div>
                        <div className="col col-6"> رستوران خامس</div>
                        <div className="col col-5 left-col">
                            <button type="button" className="factor delivery-search btn btn-primary">در جست‌و‌جوی پیک</button>
                        </div>
                    </div>
                    <div className="row order-item">
                        <div className="col col-1 right-col">۳</div>
                        <div className="col col-6"> رستوران خامس</div>
                        <div className="col col-5 left-col">
                            <button type="button" className="factor delivered btn btn-primary">مشاهده فاکتور</button>
                        </div>
                    </div>
                    <div className="row order-item">
                        <div className="col col-1 right-col">۴</div>
                        <div className="col col-6"> رستوران خامس</div>
                        <div className="col col-5 left-col">
                            <button type="button" className="factor delivered btn btn-primary">مشاهده فاکتور</button>
                        </div>
                    </div>
                    <div className="row order-item">
                        <div className="col col-1 right-col">۵</div>
                        <div className="col col-6"> رستوران خامس</div>
                        <div className="col col-5 left-col">
                            <button type="button" className="factor delivered btn btn-primary">مشاهده فاکتور</button>
                        </div>
                    </div>
                    <div className="row order-item">
                        <div className="col col-1 right-col">۶</div>
                        <div className="col col-6"> رستوران خامس</div>
                        <div className="col col-5 left-col">
                            <button type="button" className="factor delivered btn btn-primary">مشاهده فاکتور</button>
                        </div>
                    </div>
                    <div className="row order-item">
                        <div className="col col-1 right-col">۷</div>
                        <div className="col col-6"> رستوران خامس</div>
                        <div className="col col-5 left-col">
                            <button type="button" className="factor delivered btn btn-primary">مشاهده فاکتور</button>
                        </div>
                    </div>
                </div>
			</div>
		);
	}
}

export default Orders;
