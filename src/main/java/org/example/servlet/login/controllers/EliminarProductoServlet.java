package org.example.servlet.login.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.login.services.ProductoService;
import org.example.servlet.login.services.ProductoServiceJdbcImplement;


import java.io.IOException;
import java.sql.Connection;

@WebServlet("/eliminarProducto")
public class EliminarProductoServlet extends HttpServlet {

}
