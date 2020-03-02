<%@ page import="domain.Restaurant" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="domain.FoodPartyFood" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Food Party</title>
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
        .old-price {
            text-decoration: line-through;
        }
    </style>
</head>
<body>
<ul>
    <%
        ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) request.getAttribute("foodPartyRestaurants");
        System.out.println(restaurants.size());
        if(restaurants.size() == 0){%>
            <h2> There is no restaurant with food party near you </h2>
        <%}
        else{
            for(Restaurant restaurant: restaurants){%>
                <li>menu:
                    <%  System.out.println(restaurant.getMenu().getFoodPartyFoods().size());

                        for (FoodPartyFood foodPartyFood: restaurant.getMenu().getFoodPartyFoods()) {
                        if(foodPartyFood.getCount() > 0) {%>
                            <ul>
                                <li>
                                    <img src=<%=foodPartyFood.getImage()%>alt="logo">
                                    <div><%=restaurant.getName()%></div>
                                    <div><%=foodPartyFood.getName()%></div>
                                    <div><%=foodPartyFood.getDescription()%></div>
                                    <div class="old-price"><%=foodPartyFood.getOldPrice()%> Toman</div>
                                    <div><%=foodPartyFood.getPrice()%> Toman</div>
                                    <div>remaining count:<%=foodPartyFood.getCount()%></div>
                                    <div>popularity: <%=foodPartyFood.getPopularity()%></div>
                                    <form action=/restaurant method="POST">
                                        <input type="hidden" name="restaurantId" value=<%=restaurant.getId()%>>
                                        <input type="hidden" name="foodName" value="<%=foodPartyFood.getName()%>">
                                        <button type="submit">Add To Cart</button>
                                    </form>
                                </li>
                            </ul>
                        <%}
                    }%>
                </li>
            <%}
        }%>
</ul>
</body>
</html>