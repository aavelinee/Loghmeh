import React, { Component } from 'react';
import './Orders.css';
import OrderItem from './OrderItem';
import axios from 'axios';

class Orders extends Component {
    constructor(props) {
        super(props);
        this.state = {orders : [], showModal: false, status: ""};
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
        const orders = this.state.orders.reverse().map((order) =>
            <div class="container">
                <OrderItem orders={this.state.orders} order={order} />
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
