import React, { Component } from 'react';
import './OrderBill.css';
import PersianNumber from '../../common/PersianNumber';

class OrderBill extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        const order = this.props.order.map((item) =>
            <tr>
                <td id="table-row" data-href="#"><PersianNumber number={this.props.order.indexOf(item)+1} /></td>
                <td id="table-food">{item.food.name}</td>
                <td id="table-count" data-href="#"><PersianNumber number={item.orderCount} /></td>
                <td id="table-price" data-href="#"><PersianNumber number={item.food.price} /></td>
            </tr>
        );
        return(
            <div className="container order-bill">
                <div className="row orderbill-restname">
                    <p id="orderbill-restname">{this.props.restaurantName}</p>
                </div>
                <div className="row order-table">
                <table id="order-table">
                    <tr>
                        <th id="table-row">ردیف</th>
                        <th id="table-food">نام غذا</th>
                        <th id="table-count">تعداد</th>
                        <th id="table-price">قیمت</th>
                    </tr>
                    {order}
                </table>
                </div>
                <div className="row total-cost">
                    <p id="total-cost">جمع کل: ۱۸۰۰۰۰ تومان</p>
                </div> 
            </div>
        );
    }
}

export default OrderBill;