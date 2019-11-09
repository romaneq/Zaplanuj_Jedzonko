package pl.coderslab.xenteros.web;

import pl.coderslab.xenteros.model.Admins;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/app/tak")
public class Menu extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Admins admin = (Admins) session.getAttribute("admin");
        request.setAttribute("admin", admin);

        getServletContext().getRequestDispatcher("/menu.jsp").forward(request, response);
    }
}
