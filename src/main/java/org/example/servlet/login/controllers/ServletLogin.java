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

//path para la conexion al Servlet
@WebServlet ({"/Login","/ServletLogin"})
public class ServletLogin  extends HttpServlet {
    final static String USERNAME="admin";
    final static String PASSWORD="admin";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth= new LoginServiceimplment();
        Optional<String> usernameOptional = auth.getUserName(req);
        if (usernameOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>ServletLogin</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet Login</h1>");
                out.println("<h1>Hola</h1>"+usernameOptional.get()+"has Iniciado Sesion con exito"+"</body>");
                out.println("<p><a href='"+req.getContextPath()+"/index.html'>Volver</a></p>");
                out.println("<p><a href='"+req.getContextPath()+"/logout'><button>Cerrar Sesion</button></a></p>");
                out.println("</body>");
                out.println("</html>");
            }
        }
        else{
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);

        }
    }
    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(USERNAME.equals(username) && PASSWORD.equals(password)){
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect(req.getContextPath()+"/ServletLogin");

        }
        else {
            resp.sendError( HttpServletResponse.SC_UNAUTHORIZED,"Lo sentimos no esta autorizado para ingresar al sistema" );

        }

    }
}
