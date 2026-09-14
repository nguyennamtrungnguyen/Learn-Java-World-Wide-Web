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
        .customerlist{
            display: flex;
            flex-direction: column;
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
