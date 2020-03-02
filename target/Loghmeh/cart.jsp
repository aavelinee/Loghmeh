<%@ page import="domain.OrderItem" %>
<%@ page import="domain.Order" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <style>
        li, div, form {
            padding: 5px
        }
    </style>
</head>
<body>
<% Order cart = (Order) request.getAttribute("cart");%>
<%if(cart == null){%>
    <h2> Your cart is empty </h2>
<%} else{%>
<div><%=cart.getRestaurant().getName()%></div>
<ul>
    <%for(OrderItem orderItem: cart.getOrders()){%>
        <li><%=orderItem.getFood().getName()%>:‌<%=orderItem.getOrderCount()%></li>
    <%}%>
</ul>
<form action="/order" method="POST">
    <button type="submit">finalize</button>
</form>
<%}%>
</body>
</html>