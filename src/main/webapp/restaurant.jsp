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
<%
    String classValue = "form-group col-md-6 ";
    if(request.getParameter("badFirstName") != null) {
        classValue += "has-error";
    }
%>
<ul>
    <%Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");%>
    <li>ID: <%=Loghmeh.getInstance().getIndexFromRestaurantId(restaurant.getId())%></li>
    <li>name: <%=restaurant.getName()%></li>
    <li>location: (<%=restaurant.getLocation().getX()%>, <%=restaurant.getLocation().getY()%>)</li>
    <li>logo: <img src=<%=restaurant.getLogoURL()%> alt="logo"></li>

    <!-- IN CASE YOU WANT SOME BONUS : -->
    <!-- <li>estimated delivery time: 10 min 2 sec </li> -->

    <li>menu:
        <ul>
        <%
            for (Food food: restaurant.getMenu().getFoods()){%>
            <li>
                <img src=<%=food.getImage()%> alt="logo">
                <div><%=food.getName()%></div>
                <div><%=food.getPrice()%></div>
                <form action="" method="POST">
                    <input type="hidden" name="restaurantId" value=<%=Loghmeh.getInstance().getIndexFromRestaurantId(restaurant.getId())%>>
                    <button type="submit">addToCart</button>
                </form>
            </li>

            <%}%>
        </ul>
    </li>
    <form action=/getCart method=POST>
        <input type=hidden name=restaurantId value=<%=Loghmeh.getInstance().getIndexFromRestaurantId(restaurant.getId())%>>
        <button type=submit name=foodName value= >get Cart</button>
    </form>
</ul>
</body>
</html>