<%@ page import="domain.Customer" %>
<!DOCTYPE html>
<html lang=en> 
<head> 
    <meta charset=UTF-8> 
    <title>User</title> 
    <style> 
        li { 
        tpadding: 5px 
        } 
    </style> 
</head> 
<body>
    <% if(request.getAttribute("badCredit") != null){System.out.println("hereee");%>
    <script type="text/javascript">
        alert("you entered bad credit!");
    </script>
    <%}%>
    <ul>
        <% Customer customer = (Customer)request.getAttribute("customer"); %>
        <li>id: <%=customer.getCustomerId()%></li>
        <li>full name: <%=customer.getFirstName()%> <%=customer.getLastName()%></li>
        <li>phone number: <%=customer.getPhoneNumber()%></li>
        <li>email: <%=customer.getEmail()%></li>
        <li>credit: <%=customer.getCredit()%> Toman</li>
        <form action=/profile/increaseCredit method=POST>
            <button type=submit>increase</button>
            <input type="number" name=credit>
        </form>
    </ul>
</body> 
</html>