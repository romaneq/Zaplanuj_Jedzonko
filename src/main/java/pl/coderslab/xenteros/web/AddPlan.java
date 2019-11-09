package pl.coderslab.xenteros.web;

import pl.coderslab.xenteros.dao.AdminsDAO;
import pl.coderslab.xenteros.dao.PlanDao;
import pl.coderslab.xenteros.model.Admins;
import pl.coderslab.xenteros.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/app/plan/add")
public class AddPlan extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("planName");
        String description = request.getParameter("planDescription");

        DateFormat yt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String created =  yt.format(date);

        HttpSession session = request.getSession();
        Admins admin = (Admins) session.getAttribute("admin");
        int adminID = admin.getId();

        Plan plan = new Plan(name,description,created, adminID);
        PlanDao planDao = new PlanDao();
        planDao.create(plan);

        response.sendRedirect("/app/plan/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/plan.jsp").forward(request, response);
    }
}
