<%@ page import="domain.Order" %>
<%@ page import="domain.OrderItem" %>
<%@ page import="java.util.Date" %>
<%@ page import="domain.Loghmeh" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order</title>
    <style>
        li, div, form {
            padding: 5px
        }
    </style>
</head>
    <body>
        <% Order cart = (Order) request.getAttribute("order");%>
        <%--<%if(cart == null){%>--%>
        <%--<h2> Your cart is empty </h2>--%>
        <%--<%} else{%>--%>
        <div><%=cart.getRestaurant().getName()%></div>
        <ul>
            <%for(OrderItem orderItem: cart.getOrders()){%>
            <li><%=orderItem.getFood().getName()%>:‌<%=orderItem.getOrderCount()%></li>
            <%}%>
        </ul>
        <div>
            <%if(cart.getStatus() == Order.orderStatus.Ordering){%>
                status : ordering
            <%} else if(cart.getStatus() == Order.orderStatus.DeliverySearch){%>
                status : finding delivery

            <%}else if(cart.getStatus() == Order.orderStatus.OnTheWay){%>
                status : delivering
                remained time : <%=Loghmeh.getInstance().convertMillisToDateFormat(new Date((long) cart.getEstimatedDeliveryTime()*1000).getTime() - (new Date().getTime() - cart.getDeliveryDate().getTime()))%>
            <%}else if(cart.getStatus() == Order.orderStatus.Delivered){%>
                status : done
            <%}%>




        </div>
    </body>
</html>
