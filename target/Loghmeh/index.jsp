<!DOCTYPE html>
<html lang=\en\>
<head>
    <meta charset=\UTF-8\>
    <title>Home</title>
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
<form action=/profile method=GET>
    <button type=submit>Show Profile</button>
    <br><br>
</form>
<form action=/restaurants method=GET>
    <button type=submit>Get Restaurants</button>
    <br><br>
</form>
<form action=/foodparty method=GET>
    <button type=submit>Food Party</button>
    <br><br>
</form>
<form action=/cart method=GET>
    <button type=submit>Get Cart</button>
</form>
<%--<form action=/restaurant method=POST>--%>
    <%--<input type=text id=\ name=restaurantId><br>--%>
    <%--<button type=submit>Get Restaurant</button>--%>
<%--</form>--%>

</body>
</html>