package pl.coderslab.xenteros.web;

import pl.coderslab.xenteros.dao.AdminsDAO;
import pl.coderslab.xenteros.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        AdminsDAO adminsDAO = new AdminsDAO();
        if (adminsDAO.checking(email, password) == false) {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("login", true);
            Admins admin = new Admins();
            admin = adminsDAO.findAdmin(email);
            session.setAttribute("admin", admin);
            response.sendRedirect("/app/dashboard");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
