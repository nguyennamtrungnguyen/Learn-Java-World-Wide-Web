<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/8/2026
  Time: 10:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Home</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>

<body>

  <h1>Welcome to Home Page</h1>

  <p>Chào mừng bạn đến với website!</p>

  <a href="${pageContext.request.contextPath}/views/Login.jsp">
    <button>Đăng nhập</button>
  </a>
<%--  Tạo trang home.jsp hiển thị số người online như sau:--%>
  <div>
    <p>Số người đang online: <strong>${applicationScope.activeUsersCount}</strong></p>
  </div>

</body>
</html>