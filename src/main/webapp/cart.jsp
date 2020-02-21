<%@ page import="domain.OrderItem" %>
<%@ page import="domain.Order" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User</title>
    <style>
        li, div, form {
            padding: 5px
        }
    </style>
</head>
<body>
<div><%=request.getAttribute("restaurantName")%></div>
<% Order cart = (Order) request.getAttribute("cart");
if(cart == null){%>
    <h2> Your cart is empty </h2>
<%} else{%>
<ul>
    <%for(OrderItem orderItem: cart.getOrders()){%>
        <li><%=orderItem.getFood().getName()%>:‌<%=orderItem.getOrderCount()%></li>
    <%}%>
</ul>
<form action="/finalize" method="POST">
    <button type="submit">finalize</button>
</form>
<%}%>
</body>
</html>