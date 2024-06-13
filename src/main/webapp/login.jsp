<%--
  Created by IntelliJ IDEA.
  User: Estudiante
  Date: 30/5/2024
  Time: 8:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP Login Page</title>
    <style>
        body {
            background: linear-gradient(to right, #093aec, #fb2300);
            color: #fff;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        form {
            background: rgba(0, 0, 0, 0.5);
            padding: 20px;
            border-radius: 10px;
            width: 300px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
        }

        h1 {
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="password"],
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 5px;
            border: none;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background: #4CAF50;
            color: white;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background: #45a049;
        }
    </style>
</head>
<body><form action="Login" method="post">
    <div>
        <label for="username">Ingrese el nombre de usuario</label>
        <input type="text" name="username" id="username">
    </div>
    <div>
        <label for="password">Ingrese la contraseña</label>
        <input type="password" name="password" id="password">
    </div>
    <div>
        <input type="submit" value="Login">
        <!-- Botón para regresar al menú -->
        <a href="/login_war_exploded/index.html">Regresar al Menú</a>
    </div>
</form>

</body>
</html>
