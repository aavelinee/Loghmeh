import React, { Component } from 'react';
import './OrderBill.css';

class OrderBill extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return(
            <div className="container order-bill">
                <div className="row orderbill-restname">
                    <p id="orderbill-restname">رستوران خامس</p>
                </div>
                <div className="row order-table">
                <table id="order-table">
                    <tr>
                        <th id="table-row">ردیف</th>
                        <th id="table-food">نام غذا</th>
                        <th id="table-count">تعداد</th>
                        <th id="table-price">قیمت</th>
                    </tr>
                    <tr>
                        <td id="table-row">۱</td>
                        <td id="table-food">برگر گوشت</td>
                        <td id="table-count">۱</td>
                        <td id="table-price">۳۰۰۰۰</td>
                    </tr>
                    <tr>
                        <td id="table-row">۲</td>
                        <td id="table-food">برگر مرغ</td>
                        <td id="table-count">۱</td>
                        <td id="table-price">۳۰۰۰۰</td>
                    </tr>
                    <tr>
                        <td id="table-row">۳</td>
                        <td id="table-food">پیتزا مخصوص</td>
                        <td id="table-count">۱</td>
                        <td id="table-price">۳۰۰۰۰</td>
                    </tr>
                    <tr>
                        <td id="table-row">۴</td>
                        <td id="table-food">پیتزا گوشت</td>
                        <td id="table-count">۱</td>
                        <td id="table-price">۳۰۰۰۰</td>
                    </tr>
                    <tr>
                        <td id="table-row">۵</td>
                        <td id="table-food">پیتزا مرغ</td>
                        <td id="table-count">۱</td>
                        <td id="table-price">۳۰۰۰۰</td>
                    </tr>
                    <tr>
                        <td id="table-row">۶</td>
                        <td id="table-food">پیتزا پپرونی</td>
                        <td id="table-count">۱</td>
                        <td id="table-price">۳۰۰۰۰</td>
                    </tr>
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