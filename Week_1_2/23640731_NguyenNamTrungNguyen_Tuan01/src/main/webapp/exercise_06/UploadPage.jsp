<%--
  Created by IntelliJ IDEA.
  User: TrungNguyen
  Date: 8/18/2026
  Time: 10:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String ctx = request.getContextPath();
%>
<html>
<head>
    <title>Title</title>
    <link href="<%= ctx %>/resources/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container d-flex align-items-center justify-content-center ">

        <form method="POST" action="<%= request.getContextPath() %>/upload" enctype="multipart/form-data">
            <h3 class="text-center">Upload File</h3>
            <div class="mb-2">
                <label id="file1">File #1:</label>
                <input type="file" name="file1" id="file1">
            </div>
            <div class="mb-2">
                <label id="file2">File #2:</label>
                <input type="file" name="file2" id="file2">
            </div>
            <div class="mb-2">
                <label id="file3">File #3:</label>
                <input type="file" name="file3" id="file3">
            </div>
            <br/>
            <button  class="btn btn-primary px-4 py-3 w-50">Submit</button>
        </form>
        <script src="<%= ctx %>/resources/bootstrap.bundle.min.js"></script>
</body>
</html>
