package org.example.servlet.login.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.servlet.login.services.LoginService;
import org.example.servlet.login.services.LoginServiceimplment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet ({"/Login","/ServletLogin"})
public class ServletLogin extends HttpServlet {
    final static String ADMIN_USERNAME = "admin";
    final static String ADMIN_PASSWORD = "admin";
    final static String USER_USERNAME = "joel";
    final static String USER_PASSWORD = "joel123";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceimplment();
        Optional<String> usernameOptional = auth.getUserName(req);
        if (usernameOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>ServletLogin</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; }");
                out.println("h1 { color: #333; }");
                out.println("p { margin-bottom: 10px; }");
                out.println("button { background-color: #333; color: white; padding: 10px 20px; border: none; cursor: pointer; }");
                out.println("button:hover { background-color: #555; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet Login</h1>");
                out.println("<h1>Hola " + usernameOptional.get() + ", has iniciado sesión con éxito.</h1>");
                out.println("<p><a href='" + req.getContextPath() + "/index.html'>Volver</a></p>");
                out.println("<p><a href='" + req.getContextPath() + "/LogoutServlet'><button>Cerrar Sesión</button></a></p>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            session.setAttribute("username", username);
            session.setAttribute("role", "admin"); // Asignar rol de admin
        } else if (USER_USERNAME.equals(username) && USER_PASSWORD.equals(password)) {
            session.setAttribute("username", username);
            session.setAttribute("role", "user"); // Asignar rol de usuario
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos, no está autorizado para ingresar al sistema");
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/ServletLogin");
    }
}
