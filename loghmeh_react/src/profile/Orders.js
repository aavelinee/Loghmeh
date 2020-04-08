import React, { Component } from 'react';
import './Orders.css'

class Orders extends Component {
	render() {
		return (
			<div class="orders-container container-sm border">
                <div class="container">
                    <div class="row order-item">
                        <div class="col col-1 right-col">۱</div>
                        <div class="col col-6"> رستوران خامس</div>
                        <div class="col col-5 left-col">
                            <button type="button" class="factor on-the-way btn btn-primary">پیک در مسیر</button>
                        </div>
                    </div>
                    <div class="row order-item">
                        <div class="col col-1 right-col">۲</div>
                        <div class="col col-6"> رستوران خامس</div>
                        <div class="col col-5 left-col">
                            <button type="button" class="factor delivery-search btn btn-primary">در جست‌و‌جوی پیک</button>
                        </div>
                    </div>
                    <div class="row order-item">
                        <div class="col col-1 right-col">۳</div>
                        <div class="col col-6"> رستوران خامس</div>
                        <div class="col col-5 left-col">
                            <button type="button" class="factor delivered btn btn-primary">مشاهده فاکتور</button>
                        </div>
                    </div>
                    <div class="row order-item">
                        <div class="col col-1 right-col">۴</div>
                        <div class="col col-6"> رستوران خامس</div>
                        <div class="col col-5 left-col">
                            <button type="button" class="factor delivered btn btn-primary">مشاهده فاکتور</button>
                        </div>
                    </div>
                    <div class="row order-item">
                        <div class="col col-1 right-col">۵</div>
                        <div class="col col-6"> رستوران خامس</div>
                        <div class="col col-5 left-col">
                            <button type="button" class="factor delivered btn btn-primary">مشاهده فاکتور</button>
                        </div>
                    </div>
                    <div class="row order-item">
                        <div class="col col-1 right-col">۶</div>
                        <div class="col col-6"> رستوران خامس</div>
                        <div class="col col-5 left-col">
                            <button type="button" class="factor delivered btn btn-primary">مشاهده فاکتور</button>
                        </div>
                    </div>
                    <div class="row order-item">
                        <div class="col col-1 right-col">۷</div>
                        <div class="col col-6"> رستوران خامس</div>
                        <div class="col col-5 left-col">
                            <button type="button" class="factor delivered btn btn-primary">مشاهده فاکتور</button>
                        </div>
                    </div>
                </div>
			</div>
		);
	}
}

export default Orders;