package org.example.servlet.login.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.servlet.login.services.ServiceJdbcException;
import util.Conexion;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("")
public class ConexionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        try (Connection conn= Conexion.getConnection()){
            if (conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try {
                request.setAttribute("conn",conn);
                chain.doFilter(request, response);
                conn.commit();
            }catch (SQLException | ServiceJdbcException e){
                conn.rollback();
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }

        }catch (SQLException throwables){

        }

    }

}