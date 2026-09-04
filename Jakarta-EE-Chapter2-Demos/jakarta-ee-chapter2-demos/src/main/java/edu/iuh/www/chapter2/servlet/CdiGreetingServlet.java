package edu.iuh.www.chapter2.servlet;

import edu.iuh.www.chapter2.context.RequestMetadata;
import edu.iuh.www.chapter2.service.GreetingService;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/demo/cdi/greeting")
public class CdiGreetingServlet extends HttpServlet {
    @Inject
    private GreetingService greetingService;

    @Inject
    private RequestMetadata requestMetadata;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        if (name == null || name.isBlank()) {
            name = "sinh viên";
        }

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(Json.createObjectBuilder()
                .add("demo", "CDI injection và scope")
                .add("message", greetingService.greet(name.strip()))
                .add("serviceInstanceId", greetingService.instanceId())
                .add("requestId", requestMetadata.getRequestId())
                .add("note", "serviceInstanceId giữ nguyên vì bean dùng @ApplicationScoped; requestId thay đổi theo request.")
                .build().toString());
    }
}

