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
    <title>Trang chủ</title>
    <link href="<%= ctx %>/resources/bootstrap.min.css" rel="stylesheet">
</head>
<body class="p-4">
    <h3>Trang chủ</h3>
    <a class="btn btn-primary me-2" href="LoginPage.jsp">Đến login</a>
    <a class="btn btn-secondary" href="secure/secret.jsp">Đến secret</a>
    <p>${error}</p>
    <link href="<%= ctx %>/resources/bootstrap.bundle.min.js">
</body>
</html>
