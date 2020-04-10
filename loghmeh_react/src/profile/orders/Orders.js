import React, { Component } from 'react';
import './Orders.css';
import OrderBill from './OrderBill';
import axios from 'axios';
import { Modal } from 'react-bootstrap';


class Orders extends Component {
    constructor(props) {
        super(props);
        this.state = {orders : [], showModal: false};
        this.getOrders = this.getOrders.bind(this);
        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);
    }

    handleShow() {
        this.setState({showModal: true});
    }

    handleClose() {
        this.setState({showModal: false});
    }

    componentDidMount() {
        this.getOrders();
    }

    getOrders() {
		axios.get("http://localhost:8081/Loghmeh_war_exploded/orders")
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
        console.log("in orders page");
        console.log(this.state.orders);
        const status = {
            Ordering: "در حال سفارش",
            DeliverySearch: "در جست‌و‌جوی پیک",
            OnTheWay: "پیک در مسیر",
            Delivered: "مشاهده فاکتور"
         };
        const orders = this.state.orders.map((order) =>
            <div class="container">
                <div class="row order-item">
                    <div class="col col-1 right-col">{order.id}</div>
                    <div class="col col-6"> {order.restaurant.name}</div>
                    <div class="col col-5 left-col">
                        <button type="button" class="factor on-the-way" onClick={this.handleShow}>{status[order.status]}</button>
                        <Modal show={this.state.showModal} onHide={this.handleClose}>
                            <OrderBill order={order.orders} restaurantName={order.restaurant.name}/>
                        </Modal>
                    </div>
                </div>
            </div>
        );




		return (
			<div id="orders" class="orders-container container-sm border">
                {orders}
			</div>
		);
	}
}

export default Orders;
