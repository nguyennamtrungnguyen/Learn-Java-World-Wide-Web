<%--
  Created by IntelliJ IDEA.
  User: TrungNguyen
  Date: 8/17/2026
  Time: 9:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="<%= request.getContextPath() %>/resources/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container vh-100 d-flex justify-content-center align-items-center">
    <div class="card p-4 shadow" style="min-width:350px;max-width:400px;width:100%;">
        <h3 class="text-center mb-3">Đăng nhập</h3>

        <%
            String err = (String) request.getAttribute("error");
            if (err != null) {
        %>
        <div class="alert alert-danger"><%= err %>
        </div>
        <%
            }
        %>

        <form method="post" action="<%= request.getContextPath() %>/login">
            <div class="mb-3">
                <label class="form-label">Tên đăng nhập</label>
                <input class="form-control" name="username" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Mật khẩu</label>
                <input class="form-control" type="password" name="password" required>
            </div>
            <button class="btn btn-primary w-100">Đăng nhập</button>
        </form>
    </div>
</div>
<script src="<%= request.getContextPath() %>/resources/bootstrap.bundle.min.js"></script>
</body>
</html>
