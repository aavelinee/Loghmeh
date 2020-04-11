import React, { Component } from "react";
import {Modal} from 'react-bootstrap';
import axios from 'axios';

import './OrderItem.css';

import OrderBill from './OrderBill';

class OrderItem extends Component {
    constructor(props) {
        super(props);
        this.state = {orders: this.props.orders, order: this.props.order, showModal: false};
        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.timeOut = this.timeOut.bind(this);
        this.getOrderStatus = this.getOrderStatus.bind(this);
    }

    handleShow() {
        this.setState({showModal: true});
    }

    handleClose() {
        this.setState({showModal: false});
    }

    timeOut() {
        this.getOrderStatus();
    }

    getOrderStatus() {
        axios.get("http://localhost:8081/Loghmeh_war_exploded/order/" + this.state.order.id)
        .then(res => {
            const data = res.data;
            this.setState({ 
                order: data,
                }).catch(error => {
                    console.log(error)
                });
        })
        console.log("new data");
        console.log(this.state.order);
        console.log(this.state.order.status);
    }

    render() {
        const status = {
            Ordering: "در حال سفارش",
            DeliverySearch: "در جست‌و‌جوی پیک",
            OnTheWay: "پیک در مسیر",
            Delivered: "مشاهده فاکتور"
         };
         if(this.state.order.status != "Delivered") {
            setTimeout(this.timeOut, 5000);
         }
         else{
            clearTimeout();
         }
        console.log("in orders page");
        console.log(this.state.orders);
        return (
            <div class="row order-item">
                <div class="col col-1 right-col">{this.state.orders.indexOf(this.props.order) + 1}</div>
                <div class="col col-6"> {this.props.order.restaurant.name}</div>
                <div class="col col-5 left-col">
                    <button type="button" class="factor on-the-way" onClick={this.handleShow}>{status[this.state.order.status]}</button>
                    <Modal show={this.state.showModal} onHide={this.handleClose}>
                        <OrderBill order={this.props.order} />
                    </Modal>
                </div>
            </div>
        );
    }
}

export default OrderItem;
