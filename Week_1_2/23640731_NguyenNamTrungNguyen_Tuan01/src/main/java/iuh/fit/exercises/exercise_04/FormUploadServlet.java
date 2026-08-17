package iuh.fit.exercises.exercise_04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
//note
//    1. JSP
//      ↓
//    2. User gửi request
//      ↓
//    3. Servlet nhận request
//      ↓
//    4. Servlet gọi Service/Repository
//      ↓
//    5. Database
//      ↓
//    6. Servlet lấy dữ liệu
//      ↓
//    7. Forward về JSP
//      ↓
//    8. JSP hiển thị
@WebServlet("/processFormUpload")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 15
)
public class FormUploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        String[] hobbies = req.getParameterValues("hobbies");
        String country = req.getParameter("country");
        String birthDate = req.getParameter("birthDate");
        String about = req.getParameter("about");

        Part filePart = req.getPart("profilePic");
        String fileName = filePart.getSubmittedFileName();

        // 👉 lưu vào webapp/uploads để có thể truy cập bằng URL
        String uploadPath = req.getServletContext().getRealPath("/uploads");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        if (fileName != null && !fileName.isEmpty()) {
            filePart.write(uploadPath + File.separator + fileName);
        }

        String imageUrl = req.getContextPath() + "/uploads/" + fileName;

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<h2>Form Data Received</h2>");
        out.println("Name: " + name + "<br>");
        out.println("Password: " + password + "<br>");
        out.println("Gender: " + gender + "<br>");
        out.println("Hobbies: " + (hobbies != null ? String.join(", ", hobbies) : "None") + "<br>");
        out.println("Country: " + country + "<br>");
        out.println("Birth Date: " + birthDate + "<br>");
        out.println("About: " + about + "<br>");

        out.println("<br>Uploaded File: " +
                (fileName != null ? fileName : "No file") + "<br>");

        if (fileName != null && !fileName.isEmpty()) {
            out.println("<h3>Image Preview:</h3>");
            out.println("<img src='" + imageUrl + "' width='200'/>");
        }

        out.println("<br>Saved at: " + uploadPath);
    }
}