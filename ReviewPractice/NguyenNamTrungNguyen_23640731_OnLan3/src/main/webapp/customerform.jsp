<%--
  Created by IntelliJ IDEA.
  User: TrungNguyen
  Date: 9/15/2026
  Time: 7:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm Khách Hàng</title>
</head>
<body>
    <div>
        <h1>FORM Thêm Khách Hàng</h1>

        <form action="${pageContext.request.contextPath}/customers" method="post">
            <div>
                <label>Mã Khách Hàng</label>
                <input type="text" disabled name="id" placeholder="Mã được sinh tự động">
            </div>

            <div>
                <label>Tên Khách Hàng</label>
                <input type="text" required name="name">
            </div>

            <div>
                <label>Email</label>
                <input type="email" required name="email">
            </div>

            <div>
                <select name="address">
                    <option value="Hà Nội">Hà Nội</option>
                    <option value="Hồ Chí Minh">Hồ Chí Minh</option>
                    <option value="Đăk Lăk">Đăk Lăk</option>
                    <option value="Đà Nẵng">Đà Nẵng</option>
                </select>

            </div>

            <div>
                <button type="submit">Thêm</button>
            </div>
        </form>
    </div>
</body>
</html>
