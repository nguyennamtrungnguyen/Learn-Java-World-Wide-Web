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
    <title>Thêm Khách Hàng</title>
</head>
<body>
    <div>
        <form action="${pageContext.request.contextPath}/customers" enctype="multipart/form-data" method="post">
            <h1>Form Thêm Khách Hàng</h1>

            <label>Mã Khách Hàng</label>
            <input name="id" disabled type="text" placeholder="Sinh tự động">
            <br>
            <label>Ảnh Khách Hàng</label>
            <input name="avatar" required type="file">
            <br>

            <label>Tên Khách Hàng</label>
            <input name="name" type="text">
<br>
            <label>Email:</label>
            <input name="email" type="email">
            <br>

            <label>Địa chỉ</label>
            <select name="address">
                <option value="Hà Nội">Hà Nội</option>
                <option value="Đà Nẵng">Đà Nẵng</option>
            </select>
            <br>

            <label>Sở thích</label>
            <br>
            <label>Đọc sách</label>
            <input value="Đọc sách" name="hobbies" type="checkbox">
            <label>Nghe nhạc</label>
            <input value="Nghe nhạc" name="hobbies" type="checkbox">
            <label>Chơi Game</label>
            <input value="Chơi Game" name="hobbies" type="checkbox">
            <br>

            <button type="submit">Thêm</button>
        </form>
    </div>
</body>
</html>
