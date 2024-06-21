package org.example.servlet.login.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.login.models.Categoria;
import org.example.servlet.login.models.Producto;
import org.example.servlet.login.repositorio.ProductoRespositoryJdbcImpl;
import org.example.servlet.login.repositorio.Repositorios;
import org.example.servlet.login.services.ProductoService;
import org.example.servlet.login.services.ProductoServiceJdbcImplement;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/agregarProducto")
public class AgregarProductoServlet extends HttpServlet {

}
