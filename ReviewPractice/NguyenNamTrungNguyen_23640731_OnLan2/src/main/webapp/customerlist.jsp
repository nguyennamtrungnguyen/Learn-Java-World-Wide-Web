<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TrungNguyen
  Date: 9/15/2026
  Time: 2:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh Sách Khách Hàng</title>
</head>
<body>

<div>
    <h1>Danh Sách Khách Hàng</h1>
    <a href="${pageContext.request.contextPath}/customerform.jsp">Thêm Khách Hàng</a>
    <table>
        <thead>
            <tr>
                <th>Mã Khách Hàng</th>
                <th>Ảnh Khách Hàng</th>
                <th>Tên Khách Hàng</th>
                <th>Email</th>
                <th>Địa Chỉ</th>
                <th>Sở thích</th>
            </tr>
        </thead>

        <tbody>
           <c:forEach var="customer" items="${customers}">
               <tr>
                   <td>${customer.id}</td>
                   <td><img src="${pageContext.request.contextPath}/uploads/${customer.avatar}" width="80" height="80"></td>
                   <td>${customer.name}</td>
                   <td>${customer.email}</td>
                   <td>${customer.address}</td>
                   <td><c:forEach var="hobby" items="${customer.hobbies}">${hobby} <br></c:forEach></td>
               </tr>
           </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
