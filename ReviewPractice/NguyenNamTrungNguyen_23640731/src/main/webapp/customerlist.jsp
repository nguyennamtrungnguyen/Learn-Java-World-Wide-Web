<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TrungNguyen
  Date: 9/14/2026
  Time: 6:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .customerlist{
            display: flex;
            flex-direction: column;
            width: 750px;
            margin: 50px;
        }
        table{
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid black;

            padding: 8px;
            text-align: left;
        }

        th{
            font-weight: bold;
        }

        a {
            display: inline-block;
            width: 160px;
            color: white;
            background: red;
            text-decoration: none;
            border-radius: 10px;
            padding: 10px;
            margin-bottom: 10px;
            text-align: center;
        }

    </style>
</head>
<body>
    <div class="customerlist">
        <h1>Danh Sách Khách Hàng</h1>

        <a href="${pageContext.request.contextPath}/customerform.jsp">Thêm Khách Hàng</a>

        <table>
            <thead>
            <tr>
                <th>Mã Khách Hàng</th>
                <th>Tên Khách Hàng</th>
                <th>Email</th>
                <th>Địa Chỉ</th>
            </tr>
            </thead>

            <tbody>
                <c:forEach var="customer" items="${customers}">
                    <tr>
                        <td>${customer.id}</td>
                        <td>${customer.name}</td>
                        <td>${customer.email}</td>
                        <td>${customer.address}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
