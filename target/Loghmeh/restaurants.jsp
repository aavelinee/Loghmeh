<%@ page import="java.util.ArrayList" %>
<%@ page import="domain.Restaurant" %>
<%@ page import="domain.Loghmeh" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Restaurants</title>
    <style>
        table {
            text-align: center;
            margin: auto;
        }
        th, td {
            padding: 5px;
            text-align: center;
        }
        .logo{
            width: 100px;
            height: 100px;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <th>id</th>
        <th>logo</th>
        <th>name</th>
    </tr>
    <%ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) request.getAttribute("restaurants");
    for(Restaurant restaurant: restaurants) {%>
        <tr>
            <td><%=Loghmeh.getInstance().getIndexFromRestaurantId(restaurant.getId())%></td>
            <td><img class=logo src=<%=restaurant.getLogoURL()%> alt=logo></td>
            <td><a href="/restaurant?restaurantId=<%=Loghmeh.getInstance().getIndexFromRestaurantId(restaurant.getId())%>"><%=restaurant.getName()%></a></td>
        </tr>
        <%}
    if(restaurants.size() == 0){%>
        <h2> There is no restaurant near you </h2>
    <%}%>

</table>
</body>
</html>