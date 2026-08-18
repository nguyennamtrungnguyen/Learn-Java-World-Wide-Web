<%--
  Created by IntelliJ IDEA.
  User: TrungNguyen
  Date: 8/17/2026
  Time: 10:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String ctx = request.getContextPath();
%>
<html>
    <head>
        <title>Home</title>
        <link href="<%= ctx %>/resources/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="p-4">
        <h3>Home</h3>
        <a class="btn btn-primary me-2" href="LoginPage.jsp">Đến Login</a>
        <a class="btn btn-secondary" href="secure/SecurePage.jsp">Đến Secret</a>
        <p>${error}</p>

        <script src="<%= ctx %>/resources/bootstrap.bundle.min.js"></script>
    </body>
</html>
