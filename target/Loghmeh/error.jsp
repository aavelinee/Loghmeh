<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <%if(request.getAttribute("badFinalize") != null){
        if(request.getAttribute("noCart") != null) {%>
            <script type="text/javascript">
                alert("There is no order in progress");
            </script>
            <form action=/ method=GET>
                <button type=submit>Home</button>
                <br><br>
            </form>
        <%}
        if(request.getAttribute("noCredit") != null) {%>
            <script type="text/javascript">
                alert("Your credit is not enough");
            </script>
            <form action=/profile method=GET>
                <button type=submit>Increase Credit</button>
                <br><br>
            </form>
        <%}
    }%>
    <%if(request.getAttribute("badRestaurant") != null){
        if(request.getAttribute("nullRestaurantId") != null || request.getAttribute("restaurantIdNotFound") != null) {%>
            <script type="text/javascript">
                alert("Insert valid restaurant id");
            </script>
            <form action=/restaurants method=GET>
                <button type=submit>Restaurants</button>
                <br><br>
            </form>
        <%}
        if(request.getAttribute("restaurantNotClose") != null) {%>
            <script type="text/javascript">
                alert("This restaurant is out of your range");
            </script>
            <form action=/restaurants method=GET>
                <button type=submit>Restaurants</button>
                <br><br>
            </form>
        <%}
    }%>

    <%if(request.getAttribute("badAddToCart") != null){
        if(request.getAttribute("nullRestaurantId") != null || request.getAttribute("nullFoodName") != null) {%>
            <script type="text/javascript">
                alert("Insert valid parameters");
            </script>
            <form action=/restaurant?restaurantId=<%=request.getAttribute("restaurantId")%> method=GET>
                <button type=submit>Restaurants</button>
                <br><br>
            </form>
        <%}
        if(request.getAttribute("differentRestaurants") != null) {%>
            <script type="text/javascript">
                alert("You cannot order from different restaurants");
            </script>
            <form action=/restaurants method=GET>
                <button type=submit>Restaurants</button>
                <br><br>
            </form>
        <%}
    }%>
    <%if(request.getAttribute("badOrder") != null){
        if(request.getAttribute("noId") != null) {%>
            <script type="text/javascript">
                alert("Insert valid parameters");
            </script>
            <form action=/profile method=GET>
                <button type=submit>Orders List</button>
                <br><br>
            </form>
        <%}
    }%>

</body>
</html>
