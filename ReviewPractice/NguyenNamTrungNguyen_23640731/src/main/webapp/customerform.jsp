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
    <title>Thêm Khách Hàng</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            width: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;

        }

        .container {
            width: 600px;
            border: 1px solid black;
        }

        h1 {
            text-align: center;
        }
        .customerform{
            display: flex;
            flex-direction: column;
            width: 500px;
            padding: 20px;
        }

        input , select{
            width: 250px;
            padding: 5px;
            margin-bottom: 15px;
        }

        select {
            width: 160px;
        }

        button {
            display: inline-block;
            width: 160px;
            color: white;
            background: blue;
            text-decoration: none;
            border-radius: 10px;
            padding: 10px;
            margin-bottom: 10px;
            text-align: center;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Form Thêm Khách Hàng</h1>

        <form class="customerform" action="${pageContext.request.contextPath}/customers" method="POST" enctype="multipart/form-data">
            <label>Mã khách hàng</label>
            <input type="text" name="id" disabled>

            <label>Avatar:</label>
            <input type="file" name="avatar" accept="image/*">

            <label>Tên khách hàng</label>
            <input type="text" name="name" required>

            <label>Email:</label>
            <input type="email" name="email" required>

            <label>Địa chỉ:</label>
            <select name="address">
                <option value="Hà Nội">Hà Nội</option>
                <option value="Hồ Chí Minh">Hồ Chí Minh</option>
                <option value="Đà Nẵng">Đà Nẵng</option>
                <option value="Đăk Lăk">Đăk Lăk</option>
            </select>

            <label>Sở thích:</label>

            <input type="checkbox" name="hobbies" value="Đọc sách">
            <label>Đọc sách</label>

            <input type="checkbox" name="hobbies" value="Du lịch">
            <label>Du lịch</label>

            <input type="checkbox" name="hobbies" value="Thể thao">
            <label>Thể thao</label>

            <input type="checkbox" name="hobbies" value="Âm nhạc">
            <label>Âm nhạc</label>

            <button type="submit">Thêm Khách Hàng</button>
        </form>
    </div>
</body>
</html>
