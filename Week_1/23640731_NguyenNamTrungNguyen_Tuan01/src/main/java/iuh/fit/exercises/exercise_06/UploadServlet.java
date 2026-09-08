package iuh.fit.exercises.exercise_06;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 8/18/2026
 */
@WebServlet("/upload")
@MultipartConfig(
        fileSizeThreshold = 1 * 1024 * 1024,
        maxFileSize = 10 * 1024 * 1024,
        maxRequestSize = 50 * 1024 * 1024
)
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        var filePart1 = req.getPart("file1");
        var filePart2 = req.getPart("file2");
        var filePart3 = req.getPart("file3");

        printPartInfo(filePart1, resp);
        printPartInfo(filePart2, resp);
        printPartInfo(filePart3, resp);

    }

    private void printPartInfo(Part part, HttpServletResponse resp) throws IOException {
        var writer = resp.getWriter();

        String fileName = part.getSubmittedFileName();

        writer.println("<h3>Thông tin file:</h3>");
        writer.println("<p>File name: " + fileName + "</p>");
        writer.println("<p>File size: " + part.getSize() + " bytes</p>");
        writer.println("<p>File content type: " + part.getContentType() + "</p>");

        // Hiển thị hình
        writer.println("<img src=\"data:" + part.getContentType()
                + ";base64,"
                + java.util.Base64.getEncoder().encodeToString(part.getInputStream().readAllBytes())
                + "\" width=\"300\">");
    }
}
