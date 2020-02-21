<%@ page import="domain.Restaurant" %>
<%@ page import="domain.Food" %>
<%@ page import="domain.Loghmeh" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Restaurant</title>
    <style>
        img {
            width: 50px;
            height: 50px;
        }
        li {
            display: flex;
            flex-direction: row;
            padding: 0 0 5px;
        }
        div, form {
            padding: 0 5px
        }
    </style>
</head>
<body>
<ul>
    <%Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");%>
    <li>ID: <%=Loghmeh.getInstance().getIndexFromRestaurantId(restaurant.getId())%></li>
    <li>name: <%=restaurant.getName()%></li>
    <li>location: (<%=restaurant.getLocation().getX()%>, <%=restaurant.getLocation().getY()%>)</li>
    <li>logo: <img src=<%=restaurant.getLogoURL()%> alt="logo"></li>
    <li>menu:
        <ul>
        <%
            for (Food food: restaurant.getMenu().getFoods()){%>
            <li>
                <img src=<%=food.getImage()%> alt="logo">
                <div><%=food.getName()%></div>
                <div><%=food.getPrice()%></div>
                <form action=/restaurant method="POST">
                    <input type="hidden" name="restaurantId" value=<%=restaurant.getId()%>>
                    <input type="hidden" name="foodName" value="<%=food.getName()%>">
                    <button type="submit">Add To Cart</button>
                </form>
            </li>

            <%}%>
        </ul>
    </li>
    <form action=/cart method=GET>
        <button type=submit>Get Cart</button>
    </form>
</ul>
</body>
</html>